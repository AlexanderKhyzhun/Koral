package com.yotalabs.koral.ui.auth.photo

import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.domain.SignUpPersonalUseCase
import com.yotalabs.koral.ui.mvp.BasePresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * @author SashaKhyzhun
 * Created on 2019-05-22.
 */
@InjectViewState
class PhotoPresenter : BasePresenter<PhotoView>(), KoinComponent {

    val schedulers: Schedulers by inject()
    val useCase: SignUpPersonalUseCase by inject()

    init {

    }

    fun onClickTakePhoto() {

    }

    fun onClickImport() {

    }

    fun onClickSave() {

    }


}