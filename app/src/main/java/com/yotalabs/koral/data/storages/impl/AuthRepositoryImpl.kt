package com.yotalabs.koral.data.storages.impl

import com.yotalabs.koral.data.Api
import com.yotalabs.koral.data.storages.AuthRepository
import com.yotalabs.koral.data.storages.StorageRepository

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
class AuthRepositoryImpl(
    private val storage: StorageRepository,
    private val api: Api
): AuthRepository {

    override fun signIn() {

    }

    override fun signUp() {

    }

    override fun logout() {

    }

    override fun isUserAuthorized(): Boolean = storage.getAuthStatus()

}