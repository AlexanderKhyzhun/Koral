package com.yotalabs.koral.data.storages

import com.yotalabs.koral.enums.Account

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
interface StorageRepository {

    fun setUUID(uuid: String)
    fun setFirstName(first: String)
    fun setLastName(second: String)
    fun setPhoneNumber(phone: String)
    fun setEmail(email: String)
    fun setPassword(pw: String)
    fun setAuthStatus(auth: Boolean)
    fun setBusinessName(name: String)
    fun setBusinessType(type: String)
    fun setAccountType(account: Account)

    fun getUUID(): String?
    fun getFirstName(): String?
    fun getLastName(): String?
    fun getPhoneNumber(): String?
    fun getEmail(): String?
    fun getPassword(): String?
    fun getAuthStatus(): Boolean
    fun getBusinessName(): String?
    fun getBusinessType(): String?
    fun getAccountType(): String?


    fun clear()

}