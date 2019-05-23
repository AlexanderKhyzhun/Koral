package com.yotalabs.koral.domain

import android.graphics.Bitmap
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import okhttp3.ResponseBody

/**
 * @author SashaKhyzhun
 * Created on 5/9/19.
 */
interface SignUpBusinessUseCase {

    fun businessName(): BehaviorSubject<CharSequence>
    fun businessType(): BehaviorSubject<CharSequence>
    fun phoneNumber(): BehaviorSubject<CharSequence>
    fun email(): BehaviorSubject<CharSequence>
    fun password(): BehaviorSubject<CharSequence>
    fun terms(): BehaviorSubject<Boolean>
    fun photo(): BehaviorSubject<Bitmap>

    fun nextButton(): BehaviorSubject<Boolean>

    fun signUpBusinessAccount(): Observable<ResponseBody>

}