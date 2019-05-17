package com.yotalabs.koral.ui.mvp

import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import com.yotalabs.koral.enums.Event
import com.yotalabs.koral.ui.AccountActivity
import com.yotalabs.koral.ui.mvp.moxy.MvpAppCompatFragment
import io.reactivex.subjects.BehaviorSubject


open class BaseFragment : MvpAppCompatFragment() {

    private val subject: BehaviorSubject<Event> = BehaviorSubject.createDefault(Event.CREATE)

    override fun onDestroy() {
        super.onDestroy()
        subject.onNext(Event.DESTROY)
    }

    fun <T> bindUntilDestroy(): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent(subject, Event.DESTROY)
    }

    fun showSnack(msg: String?) {
        msg?.let { (activity as? BaseActivity)?.showSnack(it) }
    }


    fun toSignIn() {
        startActivity(AccountActivity.getIntent(activity))
        activity?.finish()
    }
}
