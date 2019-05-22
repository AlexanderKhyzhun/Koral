package com.yotalabs.koral.ui.auth.photo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.view.clicks
import com.yotalabs.koral.R
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.ui.mvp.BaseActivity
import com.yotalabs.koral.ui.mvp.BaseFragment
import com.yotalabs.koral.utils.setVisible
import kotlinx.android.synthetic.main.fragment_photo.*
import kotlinx.android.synthetic.main.item_toolbar_purple.*
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author SashaKhyzhun
 * Created on 2019-05-22.
 */
class PhotoFragment : BaseFragment(), PhotoView {

    interface Callback {
        fun redirectFromPhotoToConfirmation()
        fun redirectFromPhotoToCongratulations()
        fun takePhoto()
        fun importPhoto()
    }

    val schedulers: Schedulers by inject()
    val glideManager: RequestManager by inject()


    @InjectPresenter
    lateinit var presenter: PhotoPresenter

    private var callback: Callback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Callback) {
            callback = context
        } else {
            throw RuntimeException("$context must implement Callback")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        item_toolbar_title.text = TITLE

        item_toolbar_right_button.setVisible()
        item_toolbar_right_button.text = TOOLBAR_BUTTON


        item_toolbar_right_button.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { presenter.onClickSave() }

        item_toolbar_back_button.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { callback?.redirectFromPhotoToConfirmation() }


        fragment_photo_layout_take.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { presenter.onClickTakePhoto() }

        fragment_photo_layout_import.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { presenter.onClickImport() }

    }

    override fun onClickedTakePhoto() {
        Timber.d("Take photo")
        callback?.takePhoto()
    }

    override fun onClickedImport() {
        Timber.d("Import photo")
        callback?.importPhoto()
    }

    override fun onClickedSave() {
        Timber.d("Save")
        callback?.redirectFromPhotoToCongratulations()
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    override fun renderName(userName: String) {
        fragment_photo_tv_name.text = userName
    }

    override fun renderImage(image: Bitmap) {
        glideManager
            .load(image)
            .apply(RequestOptions().circleCrop())
            .into(fragment_photo_iv_profile)
    }

    override fun renderError(throwable: Throwable) {
        showSnack(throwable.message)
    }

    override fun renderMessage(text: String) {
        showSnack(text)
    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    companion object {
        const val TAG = "PhotoFragment"

        private const val TITLE = "Add Profile Photo"
        private const val TOOLBAR_BUTTON = "Save"

        fun newInstance() = PhotoFragment()
    }
}