package com.yotalabs.koral.ui.calendar

import com.arellomobile.mvp.InjectViewState
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.domain.ProfileUseCase
import com.yotalabs.koral.ui.mvp.BasePresenter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

@InjectViewState
class CalendarPresenter : BasePresenter<CalendarView>(), KoinComponent {

    val schedulers: Schedulers by inject()

    private val useCase: ProfileUseCase by inject()

}
