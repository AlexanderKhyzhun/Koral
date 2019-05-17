package com.yotalabs.koral.domain

import io.reactivex.Observable
import okhttp3.ResponseBody

/**
 * @author SashaKhyzhun
 * Created on 5/9/19.
 */
interface SignUpBusinessUseCase {

    fun signUpBusinessAccount(): Observable<ResponseBody>

}