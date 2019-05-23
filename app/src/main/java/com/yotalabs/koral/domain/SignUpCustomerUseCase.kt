package com.yotalabs.koral.domain

import android.graphics.Bitmap
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import okhttp3.ResponseBody

/**
 * @author SashaKhyzhun
 * Created on 5/9/19.
 */
interface SignUpCustomerUseCase {

    fun firstName(): BehaviorSubject<CharSequence>
    fun lastName(): BehaviorSubject<CharSequence>
    fun phoneNumber(): BehaviorSubject<CharSequence>
    fun email(): BehaviorSubject<CharSequence>
    fun password(): BehaviorSubject<CharSequence>
    fun terms(): BehaviorSubject<Boolean>
    fun nextButton(): BehaviorSubject<Boolean>
    fun photo(): BehaviorSubject<Bitmap>

    fun signUpCustomerAccount(): Observable<ResponseBody>

}