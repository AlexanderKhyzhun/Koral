package com.yotalabs.koral.data.storages

import com.yotalabs.koral.ui.adapters.DisplayableItem
import com.yotalabs.koral.ui.adapters.models.ProfessionItem
import io.reactivex.subjects.BehaviorSubject

interface SignUpPersonalRepository {

    fun firstName(): BehaviorSubject<CharSequence>
    fun lastName(): BehaviorSubject<CharSequence>
    fun phoneNumber(): BehaviorSubject<CharSequence>
    fun email(): BehaviorSubject<CharSequence>
    fun password(): BehaviorSubject<CharSequence>
    fun photo(): BehaviorSubject<CharSequence>
    fun accountType(): BehaviorSubject<CharSequence>
    fun terms(): BehaviorSubject<Boolean>
    fun nextButton(): BehaviorSubject<Boolean>

    fun profession(): BehaviorSubject<ProfessionItem>

}