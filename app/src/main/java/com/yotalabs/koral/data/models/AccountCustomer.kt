package com.yotalabs.koral.data.models

/**
 * @author SashaKhyzhun
 * Created on 5/9/19.
 */
data class AccountCustomer(
    var uuid: String,
    var firstName: String,
    var lastName: String,
    var phoneNumber: String,
    var email: String,
    var photo: String
)