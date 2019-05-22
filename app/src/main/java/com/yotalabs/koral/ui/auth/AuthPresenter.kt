package com.yotalabs.koral.ui.auth

import android.graphics.Bitmap
import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.data.storages.SignUpRepository
import com.yotalabs.koral.ui.mvp.BasePresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * @author SashaKhyzhun
 * Created on 5/3/19.
 */

@InjectViewState
class AuthPresenter : BasePresenter<AuthView>(), KoinComponent {

    val signUpRepository: SignUpRepository by inject()

    fun savePhotoFromCamera(photo: Bitmap) {
        signUpRepository.photo().onNext(photo)
    }

    fun savePhotoFromStorage(photo: Bitmap) {
        signUpRepository.photo().onNext(photo)
    }


}