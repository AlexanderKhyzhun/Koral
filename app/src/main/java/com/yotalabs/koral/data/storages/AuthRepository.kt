package com.yotalabs.koral.data.storages

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
interface AuthRepository {

    fun signIn()
    fun signUp()
    fun logout()
    fun isUserAuthorized(): Boolean

}