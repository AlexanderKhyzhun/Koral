package com.yotalabs.koral.domain.impl

import android.graphics.Bitmap
import com.yotalabs.koral.data.Api
import com.yotalabs.koral.domain.SignUpPersonalUseCase
import com.yotalabs.koral.data.storages.SignUpRepository
import com.yotalabs.koral.data.models.AccountPersonal
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject
import okhttp3.ResponseBody
import timber.log.Timber
import java.util.*

/**
 * @author SashaKhyzhun
 * Created on 5/5/19.
 */
class SignUpPersonalUseCaseImpl(
    private val signUpRepository: SignUpRepository,
    private val api: Api
) : SignUpPersonalUseCase {


    override fun firstName(): BehaviorSubject<CharSequence> = signUpRepository.firstName()

    override fun lastName(): BehaviorSubject<CharSequence> = signUpRepository.lastName()

    override fun phoneNumber(): BehaviorSubject<CharSequence> = signUpRepository.phoneNumber()

    override fun email(): BehaviorSubject<CharSequence> = signUpRepository.email()

    override fun password(): BehaviorSubject<CharSequence> = signUpRepository.password()

    override fun terms(): BehaviorSubject<Boolean> = signUpRepository.terms()

    override fun nextButton(): BehaviorSubject<Boolean> = signUpRepository.nextButton()

    override fun photo(): BehaviorSubject<Bitmap> = signUpRepository.photo()


    override fun createPersonalAccount(): Observable<Boolean> {
        val firstNameObs = signUpRepository.firstName().map(CharSequence::toString)
        val lastNameObs = signUpRepository.lastName().map(CharSequence::toString)
        val phoneNumberObs = signUpRepository.phoneNumber().map(CharSequence::toString)
        val emailObs = signUpRepository.email().map(CharSequence::toString)
        val photoObs = signUpRepository.photo()

        return Observables.zip(
            firstNameObs,
            lastNameObs,
            phoneNumberObs,
            emailObs,
            photoObs
        ) { firstName, lastName, phone, email, photo ->

            AccountPersonal(
                UUID.randomUUID().toString(),
                firstName,
                lastName,
                phone,
                email,
                photo
            )
        }
            //.flatMap { api.signUp() }
            .map {
                true
            }
            .doOnError(Timber::e)
    }


}