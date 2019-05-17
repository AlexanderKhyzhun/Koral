package com.yotalabs.koral.domain.impl

import com.yotalabs.koral.data.storages.AuthRepository
import com.yotalabs.koral.domain.SplashUseCase
import io.reactivex.Single

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
class SplashUseCaseImpl(
    private val auth: AuthRepository
) : SplashUseCase {

    override fun isLoggedIn(): Boolean {
        return auth.isUserAuthorized()
    }

}