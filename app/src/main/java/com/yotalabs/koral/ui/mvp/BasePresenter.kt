package com.yotalabs.koral.ui.mvp

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import io.reactivex.subjects.BehaviorSubject

open class BasePresenter<V : MvpView> : MvpPresenter<V>() {

    private val subject: BehaviorSubject<Event> = BehaviorSubject.create()

    private enum class Event {
        CREATE, DESTROY
    }

    init {
        subject.onNext(Event.CREATE)
    }

    fun <T> bindUntilDestroy(): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent(subject, Event.DESTROY)
    }

    fun bindCompletableUntilDestroy(): LifecycleTransformer<Void> {
        return RxLifecycle.bindUntilEvent(subject, Event.DESTROY)
    }

    override fun onDestroy() {
        super.onDestroy()
        subject.onNext(Event.DESTROY)
    }

}