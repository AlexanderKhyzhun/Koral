package com.yotalabs.koral.domain.models

sealed class ValidationView {
    data class FirstNameCell(
        val firstName: String,
        val focused: Boolean
    )

    data class LastNameCell(
        val lastName: String,
        val focused: Boolean
    )

    data class PhoneNumberCell(
        val phoneNumber: String,
        val focused: Boolean
    )

    data class EmailCell(
        val email: String,
        val focused: Boolean
    )

    data class PasswordCell(
        val password: String,
        val focused: Boolean
    )

    data class TermsCell(
        val terms: Boolean
    )

    data class Form(
        val firstNameValid: Boolean = false,
        val firstNameError: Boolean = false,
        val lastNameValid: Boolean = false,
        val lastNameError: Boolean = false,
        val phoneNumberValid: Boolean = false,
        val phoneNumberError: Boolean = false,
        val emailValid: Boolean = false,
        val emailError: Boolean = false,
        val passwordValid: Boolean = false,
        val passwordError: Boolean = false,

        val termsValid: Boolean = true,
        val termsError: Boolean = false

    )
}
