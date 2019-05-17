package com.yotalabs.koral.data.storages.impl

import android.content.SharedPreferences
import com.yotalabs.koral.data.storages.StorageRepository
import com.yotalabs.koral.enums.Account

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
class StorageRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : StorageRepository {

    /**
     * SET
     */

    override fun setUUID(uuid: String) {
        sharedPreferences.edit().putString(KEY_UUID, uuid).apply()
    }

    override fun setFirstName(first: String) {
        sharedPreferences.edit().putString(KEY_FIRST_NAME, first).apply()
    }

    override fun setLastName(second: String) {
        sharedPreferences.edit().putString(KEY_LAST_NAME, second).apply()
    }

    override fun setPhoneNumber(phone: String) {
        sharedPreferences.edit().putString(KEY_PHONE_NUMBER, phone).apply()
    }

    override fun setEmail(email: String) {
        sharedPreferences.edit().putString(KEY_EMAIL, email).apply()
    }

    override fun setPassword(pw: String) {
        sharedPreferences.edit().putString(KEY_PASSWORD, pw).apply()
    }

    override fun setAuthStatus(auth: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_AUTH_STATUS, auth).apply()
    }

    override fun setBusinessName(name: String) {
        sharedPreferences.edit().putString(KEY_BUSINESS_NAME, name).apply()
    }

    override fun setBusinessType(type: String) {
        sharedPreferences.edit().putString(KEY_BUSINESS_TYPE, type).apply()
    }

    override fun setAccountType(account: Account) {
        sharedPreferences.edit().putString(KEY_ACCOUNT_TYPE, account.type).apply()
    }

    /**
     * GET
     */

    override fun getUUID(): String? = sharedPreferences.getString(KEY_UUID, null)

    override fun getFirstName(): String? = sharedPreferences.getString(KEY_FIRST_NAME, null)

    override fun getLastName(): String? = sharedPreferences.getString(KEY_LAST_NAME, null)

    override fun getPhoneNumber(): String? = sharedPreferences.getString(KEY_PHONE_NUMBER, null)

    override fun getEmail(): String? = sharedPreferences.getString(KEY_EMAIL, null)

    override fun getPassword(): String? = sharedPreferences.getString(KEY_PASSWORD, null)

    override fun getAuthStatus(): Boolean = sharedPreferences.getBoolean(KEY_AUTH_STATUS, false)

    override fun getBusinessName(): String? = sharedPreferences.getString(KEY_BUSINESS_NAME, null)

    override fun getBusinessType(): String? = sharedPreferences.getString(KEY_BUSINESS_TYPE, null)

    override fun getAccountType(): String? = sharedPreferences.getString(KEY_ACCOUNT_TYPE, null)

    /**
     * Other
     */

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }


    companion object {
        private const val KEY_UUID = "UUID"
        private const val KEY_FIRST_NAME = "FirstName"
        private const val KEY_LAST_NAME = "LastName"
        private const val KEY_PHONE_NUMBER = "PhoneNumber"
        private const val KEY_EMAIL = "Email"
        private const val KEY_PASSWORD = "Password"
        private const val KEY_AUTH_STATUS = "AuthStatus"
        private const val KEY_BUSINESS_NAME = "BusinessName"
        private const val KEY_BUSINESS_TYPE = "BusinessType"
        private const val KEY_ACCOUNT_TYPE = "AccountType"
    }

}