package com.yotalabs.koral.domain.impl

import com.yotalabs.koral.data.Api
import com.yotalabs.koral.domain.SignUpBusinessUseCase
import com.yotalabs.koral.data.storages.SignUpRepository
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.ResponseBody

/**
 * @author SashaKhyzhun
 * Created on 5/9/19.
 */
class SignUpBusinessUseCaseImpl(
    private val signUpRepository: SignUpRepository,
    private val api: Api
) : SignUpBusinessUseCase {

    override fun signUpBusinessAccount(): Observable<ResponseBody> {
        return Observable.just(ResponseBody.create(MediaType.parse(""), ""))
    }

}