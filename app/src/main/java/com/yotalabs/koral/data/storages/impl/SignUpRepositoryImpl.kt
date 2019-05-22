package com.yotalabs.koral.data.storages.impl

import com.yotalabs.koral.data.storages.SignUpRepository
import com.yotalabs.koral.ui.adapters.models.ProfessionItem
import com.yotalabs.koral.ui.adapters.models.ServiceItem
import com.yotalabs.koral.ui.adapters.models.SubServiceItem
import io.reactivex.subjects.BehaviorSubject

/**
 * @author SashaKhyzhun
 * Created on 5/5/19.
 */
class SignUpRepositoryImpl : SignUpRepository {

    private val firstNameChangesSubject = BehaviorSubject.create<CharSequence>()
    private val lastNameChangesSubject = BehaviorSubject.create<CharSequence>()
    private val phoneNumberChangesSubject = BehaviorSubject.create<CharSequence>()
    private val emailChangesSubject = BehaviorSubject.create<CharSequence>()
    private val passwordChangesSubject = BehaviorSubject.create<CharSequence>()
    private val photoSubject = BehaviorSubject.create<CharSequence>()
    private val businessNameSubject = BehaviorSubject.create<CharSequence>()
    private val businessTypeSubject = BehaviorSubject.create<CharSequence>()
    private val accountTypeSubject = BehaviorSubject.create<CharSequence>()
    private val termsSubject = BehaviorSubject.create<Boolean>()
    private val nextButtonSubject = BehaviorSubject.create<Boolean>()
    private val profession = BehaviorSubject.create<ProfessionItem>()
    private val subServices = BehaviorSubject.create<SubServiceItem>()
    private val selectedService = BehaviorSubject.create<ServiceItem>()
    private val selectedSubService = BehaviorSubject.create<MutableList<SubServiceItem>>()



    override fun firstName(): BehaviorSubject<CharSequence> = firstNameChangesSubject

    override fun lastName(): BehaviorSubject<CharSequence> = lastNameChangesSubject

    override fun phoneNumber(): BehaviorSubject<CharSequence> = phoneNumberChangesSubject

    override fun email(): BehaviorSubject<CharSequence> = emailChangesSubject

    override fun password(): BehaviorSubject<CharSequence> = passwordChangesSubject

    override fun photo(): BehaviorSubject<CharSequence> = photoSubject

    override fun businessName(): BehaviorSubject<CharSequence> = businessNameSubject

    override fun businessType(): BehaviorSubject<CharSequence> = businessTypeSubject

    override fun accountType(): BehaviorSubject<CharSequence> = accountTypeSubject

    override fun terms(): BehaviorSubject<Boolean> = termsSubject

    override fun nextButton(): BehaviorSubject<Boolean> = nextButtonSubject

    override fun profession(): BehaviorSubject<ProfessionItem> = profession

    override fun subServices(): BehaviorSubject<SubServiceItem> = subServices

    override fun selectedService(): BehaviorSubject<ServiceItem> = selectedService

    override fun selectedSubServices(): BehaviorSubject<MutableList<SubServiceItem>> = selectedSubService


}
