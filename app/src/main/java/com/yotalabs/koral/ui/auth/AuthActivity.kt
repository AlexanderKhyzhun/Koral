package com.yotalabs.koral.ui.auth

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.yotalabs.koral.R
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.ui.auth.login.LoginFragment
import com.yotalabs.koral.ui.auth.choose.ChooseAccountFragment
import com.yotalabs.koral.ui.auth.registration.corporate.CreateCorporateFragment
import com.yotalabs.koral.ui.auth.registration.customer.CreateCustomerFragment
import com.yotalabs.koral.ui.auth.registration.personal.CreatePersonalFragment
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
    ServicesFragment.Callback {

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

            else -> super.onBackPressed()
        }
    }

    private fun attachFirstFragment() {
        stack.push(LoginFragment.newInstance())
        replaceFragment(
            R.id.activity_login_container,
            supportFragmentManager,
            ServicesFragment.newInstance(),
            ServicesFragment.TAG
        )
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
        fun getIntent(context: Context?) = Intent(context, AuthActivity::class.java)
    }


}