package com.yotalabs.koral.domain.impl

import com.yotalabs.koral.data.Api
import com.yotalabs.koral.domain.SignUpCustomerUseCase
import com.yotalabs.koral.data.storages.SignUpRepository
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.ResponseBody

/**
 * @author SashaKhyzhun
 * Created on 5/9/19.
 */
class SignUpCustomerUseCaseImpl(
    private val signUpRepository: SignUpRepository,
    private val api: Api
) : SignUpCustomerUseCase {


    override fun signUpCustomerAccount(): Observable<ResponseBody> {
        return Observable.just(ResponseBody.create(MediaType.parse(""), ""))
    }

}