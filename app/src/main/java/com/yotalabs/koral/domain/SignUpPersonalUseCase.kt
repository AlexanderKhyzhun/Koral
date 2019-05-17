package com.yotalabs.koral.domain

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import okhttp3.ResponseBody

/**
 * @author SashaKhyzhun
 * Created on 5/5/19.
 */
interface SignUpPersonalUseCase {

    fun firstName(): BehaviorSubject<CharSequence>
    fun lastName(): BehaviorSubject<CharSequence>
    fun phoneNumber(): BehaviorSubject<CharSequence>
    fun email(): BehaviorSubject<CharSequence>
    fun password(): BehaviorSubject<CharSequence>
    fun terms(): BehaviorSubject<Boolean>
    fun nextButton(): BehaviorSubject<Boolean>

    fun createPersonalAccount(): Observable<Boolean>

}