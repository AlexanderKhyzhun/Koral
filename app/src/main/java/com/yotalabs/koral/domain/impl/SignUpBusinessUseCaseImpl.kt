package com.yotalabs.koral.domain.impl

import android.graphics.Bitmap
import com.yotalabs.koral.data.Api
import com.yotalabs.koral.domain.SignUpBusinessUseCase
import com.yotalabs.koral.data.storages.SignUpRepository
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import okhttp3.MediaType
import okhttp3.ResponseBody

/**
 * @author SashaKhyzhun
 * Created on 5/9/19.
 */
class SignUpBusinessUseCaseImpl(
    private val signUpRepository: SignUpRepository,
    private val api: Api
) : SignUpBusinessUseCase {

    override fun signUpBusinessAccount(): Observable<ResponseBody> {
        return Observable.just(ResponseBody.create(MediaType.parse(""), ""))
    }

    override fun businessName(): BehaviorSubject<CharSequence> = signUpRepository.businessName()

    override fun businessType(): BehaviorSubject<CharSequence> = signUpRepository.businessType()

    override fun phoneNumber(): BehaviorSubject<CharSequence> = signUpRepository.phoneNumber()

    override fun email(): BehaviorSubject<CharSequence> = signUpRepository.email()

    override fun password(): BehaviorSubject<CharSequence> = signUpRepository.password()

    override fun terms(): BehaviorSubject<Boolean> = signUpRepository.terms()

    override fun photo(): BehaviorSubject<Bitmap> = signUpRepository.photo()

    override fun nextButton(): BehaviorSubject<Boolean> = signUpRepository.nextButton()
}