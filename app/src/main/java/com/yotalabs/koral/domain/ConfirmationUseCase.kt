package com.yotalabs.koral.domain

import io.reactivex.Observable

interface ConfirmationUseCase {

    fun uploadConfirmationStatuses(
        forAll: Boolean,
        forNew: Boolean,
        underThree: Boolean,
        without: Boolean
    ) : Observable<Boolean>

}
