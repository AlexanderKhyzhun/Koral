package com.yotalabs.koral.data.storages

import com.yotalabs.koral.ui.adapters.models.ProfessionItem
import com.yotalabs.koral.ui.adapters.models.ServiceItem
import com.yotalabs.koral.ui.adapters.models.SubServiceItem
import io.reactivex.subjects.BehaviorSubject

interface SignUpRepository {

    fun firstName(): BehaviorSubject<CharSequence>
    fun lastName(): BehaviorSubject<CharSequence>
    fun phoneNumber(): BehaviorSubject<CharSequence>
    fun email(): BehaviorSubject<CharSequence>
    fun password(): BehaviorSubject<CharSequence>
    fun photo(): BehaviorSubject<CharSequence>
    fun businessName(): BehaviorSubject<CharSequence>
    fun businessType(): BehaviorSubject<CharSequence>
    fun accountType(): BehaviorSubject<CharSequence>
    fun terms(): BehaviorSubject<Boolean>
    fun nextButton(): BehaviorSubject<Boolean>
    fun profession(): BehaviorSubject<ProfessionItem>
    fun subServices(): BehaviorSubject<SubServiceItem>
    fun selectedService(): BehaviorSubject<ServiceItem>
    fun selectedSubServices(): BehaviorSubject<MutableList<SubServiceItem>>

}