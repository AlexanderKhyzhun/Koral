package com.yotalabs.koral.data.models

/**
 * @author SashaKhyzhun
 * Created on 5/9/19.
 */
data class AccountBusiness(
    var uuid: String,
    var businessName: String,
    var businessType: String,
    var phoneNumber: String,
    var email: String,
    var photo: String
)