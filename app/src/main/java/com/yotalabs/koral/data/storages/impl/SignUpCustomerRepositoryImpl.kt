package com.yotalabs.koral.data.storages.impl

import android.graphics.Bitmap
import com.yotalabs.koral.data.storages.SignUpCustomerRepository
import com.yotalabs.koral.data.storages.SignUpRepository
import io.reactivex.subjects.BehaviorSubject

/**
 * @author SashaKhyzhun
 * Created on 5/5/19.
 */
class SignUpCustomerRepositoryImpl(
    private val signUpRepository: SignUpRepository
) : SignUpCustomerRepository {

    override fun firstName(): BehaviorSubject<CharSequence> = signUpRepository.firstName()

    override fun lastName(): BehaviorSubject<CharSequence> = signUpRepository.lastName()

    override fun phoneNumber(): BehaviorSubject<CharSequence> = signUpRepository.phoneNumber()

    override fun email(): BehaviorSubject<CharSequence> = signUpRepository.email()

    override fun password(): BehaviorSubject<CharSequence> = signUpRepository.password()

    override fun photo(): BehaviorSubject<Bitmap> = signUpRepository.photo()

    override fun accountType(): BehaviorSubject<CharSequence> = signUpRepository.accountType()

    override fun terms(): BehaviorSubject<Boolean> = signUpRepository.terms()

    override fun nextButton(): BehaviorSubject<Boolean> = signUpRepository.nextButton()


}
