package com.yotalabs.koral.domain

import io.reactivex.Single

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
interface SplashUseCase {

    fun isLoggedIn(): Boolean

}