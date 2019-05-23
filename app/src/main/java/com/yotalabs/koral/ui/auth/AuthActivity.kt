package com.yotalabs.koral.ui.auth

import android.Manifest.*
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.yotalabs.koral.R
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.ui.auth.login.LoginFragment
import com.yotalabs.koral.ui.auth.choose.ChooseAccountFragment
import com.yotalabs.koral.ui.auth.photo.PhotoFragment
import com.yotalabs.koral.ui.auth.registration.corporate.CreateCorporateFragment
import com.yotalabs.koral.ui.auth.registration.customer.CreateCustomerFragment
import com.yotalabs.koral.ui.auth.registration.personal.CreatePersonalFragment
import com.yotalabs.koral.ui.auth.registration.personal.confirmation.ConfirmationFragment
import com.yotalabs.koral.ui.auth.registration.personal.other.OtherFragment
import com.yotalabs.koral.ui.auth.registration.personal.profession.ProfessionFragment
import com.yotalabs.koral.ui.auth.registration.personal.services.ServicesFragment
import com.yotalabs.koral.ui.mvp.BaseActivity
import com.yotalabs.koral.utils.ChangeAnimation
import com.yotalabs.koral.utils.replaceFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ext.android.bindScope
import org.koin.androidx.scope.ext.android.getOrCreateScope
import timber.log.Timber
import java.util.*
import android.provider.MediaStore
import android.graphics.BitmapFactory
import com.yotalabs.koral.ui.auth.congratulations.CongratulationsFragment


/**
 * @author SashaKhyzhun
 * Created on 5/3/19.
 */
class AuthActivity : BaseActivity(), AuthView,
    LoginFragment.Callback,
    ChooseAccountFragment.Callback,
    CreateCustomerFragment.Callback,
    CreatePersonalFragment.Callback,
    CreateCorporateFragment.Callback,
    ProfessionFragment.Callback,
    OtherFragment.Callback,
    ServicesFragment.Callback,
    ConfirmationFragment.Callback,
    PhotoFragment.Callback {

    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: AuthPresenter

    private val stack = Stack<Fragment>()


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")

        bindScope(getOrCreateScope("SignUp"))

        setContentView(R.layout.activity_login)

        attachFirstFragment()
    }

    override fun onBackPressed() {
        when (stack.pop()) {
            is ChooseAccountFragment -> attachFragment(
                LoginFragment.newInstance(),
                LoginFragment.TAG,
                ChangeAnimation.BACK
            )
            is CreateCustomerFragment -> attachFragment(
                ChooseAccountFragment.newInstance(),
                ChooseAccountFragment.TAG,
                ChangeAnimation.BACK
            )
            is CreateCorporateFragment -> attachFragment(
                ChooseAccountFragment.newInstance(),
                ChooseAccountFragment.TAG,
                ChangeAnimation.BACK
            )
            /* Personal Account Flow */
            is CreatePersonalFragment -> attachFragment(
                ChooseAccountFragment.newInstance(),
                ChooseAccountFragment.TAG,
                ChangeAnimation.BACK
            )
            is ProfessionFragment -> attachFragment(
                CreatePersonalFragment.newInstance(),
                CreatePersonalFragment.TAG,
                ChangeAnimation.BACK
            )
            is OtherFragment -> attachFragment(
                ProfessionFragment.newInstance(),
                ProfessionFragment.TAG,
                ChangeAnimation.BACK
            )
            is ServicesFragment -> attachFragment(
                ProfessionFragment.newInstance(),
                ProfessionFragment.TAG,
                ChangeAnimation.BACK
            )

            is ConfirmationFragment -> attachFragment(
                ServicesFragment.newInstance(),
                ServicesFragment.TAG,
                ChangeAnimation.BACK
            )

            is PhotoFragment -> {
                when (stack.peek()) {
                    is ConfirmationFragment -> attachFragment(
                        ConfirmationFragment.newInstance(),
                        ConfirmationFragment.TAG,
                        ChangeAnimation.BACK
                    )
                    is CreateCustomerFragment -> attachFragment(
                        CreateCustomerFragment.newInstance(),
                        CreateCustomerFragment.TAG,
                        ChangeAnimation.BACK
                    )
                    is CreateCorporateFragment -> attachFragment(
                        CreateCorporateFragment.newInstance(),
                        CreateCorporateFragment.TAG,
                        ChangeAnimation.BACK
                    )
                }
                /*
                 * if previous confirmation -> confirmations
                 * if previous ............ -> .............
                 */
            }
            is CongratulationsFragment -> attachFragment(
                    PhotoFragment.newInstance(),
                    PhotoFragment.TAG,
                    ChangeAnimation.BACK
            )

            else -> super.onBackPressed()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
        }
        if (requestCode == MY_STORAGE_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(
                    Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    ),
                    STORAGE_REQUEST)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CAMERA_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    val photo = data?.extras?.get("data") as Bitmap
                    presenter.savePhotoFromCamera(photo)
                }
            }
            STORAGE_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    val selectedImage = data?.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

                    val cursor = contentResolver.query(selectedImage, filePathColumn,
                        null,
                        null,
                        null
                    )
                    cursor!!.moveToFirst()

                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                    val picturePath = cursor.getString(columnIndex)
                    val photo = BitmapFactory.decodeFile(picturePath)
                    cursor.close()

                    presenter.savePhotoFromStorage(photo)
                }
            }
        }
    }


    private fun attachFirstFragment() {
        stack.push(LoginFragment.newInstance())
        replaceFragment(
            R.id.activity_login_container,
            supportFragmentManager,
            LoginFragment.newInstance(),
            LoginFragment.TAG
        )
//        stack.push(PhotoFragment.newInstance())
//        replaceFragment(
//            R.id.activity_login_container,
//            supportFragmentManager,
//            PhotoFragment.newInstance(),
//            PhotoFragment.TAG
//        )
    }

    private fun attachFragment(
        fragment: Fragment = LoginFragment.newInstance(),
        tag: String = LoginFragment.TAG,
        animation: ChangeAnimation
    ) {
        stack.push(fragment)
        replaceFragment(
            R.id.activity_login_container,
            supportFragmentManager,
            fragment,
            animation,
            tag
        )
    }


    /**
     * Login Fragment Callbacks
     */

    override fun redirectToChooseAccountPage() {
        attachFragment(
            ChooseAccountFragment.newInstance(),
            ChooseAccountFragment.TAG,
            ChangeAnimation.FORWARD
        )
    }

    /**
     * Create Account Fragment Callbacks
     */


    override fun selectedInfoPage() {

    }

    override fun selectedCustomer() {
        attachFragment(
            CreateCustomerFragment.newInstance(),
            CreateCustomerFragment.TAG,
            ChangeAnimation.FORWARD
        )
    }

    override fun selectedPersonalBusiness() {
        attachFragment(
            CreatePersonalFragment.newInstance(),
            CreatePersonalFragment.TAG,
            ChangeAnimation.FORWARD
        )
    }

    override fun selectedCorporateBusiness() {
        attachFragment(
            CreateCorporateFragment.newInstance(),
            CreateCorporateFragment.TAG,
            ChangeAnimation.FORWARD
        )
    }

    override fun redirectFromOtherToProfessions() {
        attachFragment(
            ProfessionFragment.newInstance(),
            ProfessionFragment.TAG,
            ChangeAnimation.BACK
        )
    }

    override fun selectedSkipAndExplore() {

    }

    override fun redirectToFromCreatePersonalToChoosePage() {
        attachFragment(
            ChooseAccountFragment.newInstance(),
            ChooseAccountFragment.TAG,
            ChangeAnimation.BACK
        )
    }

    override fun onSignedUpPersonalAccount() {
        attachFragment(
            ProfessionFragment.newInstance(),
            ProfessionFragment.TAG,
            ChangeAnimation.FORWARD
        )
    }

    override fun redirectFromChooseProfessionToChooseService() {
        attachFragment(
            ServicesFragment.newInstance(),
            ServicesFragment.TAG,
            ChangeAnimation.FORWARD
        )
    }

    override fun redirectFromChooseProfessionToOther() {
        attachFragment(
            OtherFragment.newInstance(),
            OtherFragment.TAG,
            ChangeAnimation.FORWARD
        )
    }

    override fun redirectFromServicesToProfessions() {
        attachFragment(
            ProfessionFragment.newInstance(),
            ProfessionFragment.TAG,
            ChangeAnimation.BACK
        )
    }

    override fun redirectFromServicesToConfirmation() {
        attachFragment(
            ConfirmationFragment.newInstance(),
            ConfirmationFragment.TAG,
            ChangeAnimation.FORWARD
        )
    }

    override fun fromConfirmationToServices() {
        attachFragment(
            ServicesFragment.newInstance(),
            ServicesFragment.TAG,
            ChangeAnimation.BACK
        )
    }

    override fun fromConfirmationToPhoto() {
        attachFragment(
            PhotoFragment.newInstance(),
            PhotoFragment.TAG,
            ChangeAnimation.FORWARD
        )
    }

    override fun redirectFromPhotoToConfirmation() {
        attachFragment(
            ConfirmationFragment.newInstance(),
            ConfirmationFragment.TAG,
            ChangeAnimation.BACK
        )
    }

    override fun redirectFromPhotoToCongratulations() {
        attachFragment(
            CongratulationsFragment.newInstance(),
            CongratulationsFragment.TAG,
            ChangeAnimation.FORWARD
        )
    }


    override fun redirectFromCustomerToChoosePage() {
        attachFragment(
            ChooseAccountFragment.newInstance(),
            ChooseAccountFragment.TAG,
            ChangeAnimation.BACK
        )
    }

    override fun redirectFromCorporateToChoosePage() {
        attachFragment(
            ChooseAccountFragment.newInstance(),
            ChooseAccountFragment.TAG,
            ChangeAnimation.BACK
        )
    }

    override fun onSignedUpCustomerAccount() {
        attachFragment(
            PhotoFragment.newInstance(),
            PhotoFragment.TAG,
            ChangeAnimation.FORWARD
        )
    }

    override fun onSignedUpCorporateAccount() {
        attachFragment(
            PhotoFragment.newInstance(),
            PhotoFragment.TAG,
            ChangeAnimation.FORWARD
        )
    }

    override fun takePhoto() {
        if (checkSelfPermission(permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
        } else {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }
    }

    override fun importPhoto() {
        Timber.d("Auth | importPhoto")
        if (checkSelfPermission(permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(permission.WRITE_EXTERNAL_STORAGE), MY_STORAGE_PERMISSION_CODE)
        } else {

            startActivityForResult(
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), STORAGE_REQUEST)
        }
    }

    /**
     * Error and messages
     */

    override fun renderMessage(text: String) {

    }

    override fun renderError(throwable: Throwable) {

    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    companion object {
        const val TAG = "AuthActivity"
        const val TITLE = "Log In"

        private const val CAMERA_REQUEST = 1888
        private const val STORAGE_REQUEST = 1889
        private const val MY_CAMERA_PERMISSION_CODE = 100
        private const val MY_STORAGE_PERMISSION_CODE = 101

        fun getIntent(context: Context?) = Intent(context, AuthActivity::class.java)
    }


}