package com.yotalabs.koral.domain.impl

import com.yotalabs.koral.data.Api
import com.yotalabs.koral.domain.ConfirmationUseCase
import io.reactivex.Observable

/**
 * @author SashaKhyzhun
 * Created on 2019-05-22.
 */
class ConfirmationUseCaseImpl(
    private val api: Api
) : ConfirmationUseCase {

    override fun uploadConfirmationStatuses(
        forAll: Boolean,
        forNew: Boolean,
        underThree: Boolean,
        without: Boolean
    ) : Observable<Boolean> {
        // api.sendStatuses()

        return Observable.just(true)
    }
}