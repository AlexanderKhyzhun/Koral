package com.yotalabs.koral.ui.mvp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.jakewharton.rxbinding2.view.clicks
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import com.yotalabs.koral.R
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.enums.Event
import com.yotalabs.koral.ui.AccountActivity
import com.yotalabs.koral.ui.mvp.moxy.MvpAppCompatFragment
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit


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

    @SuppressLint("CheckResult")
    fun Context.createAlertDialog(
        schedulers: Schedulers,
        title: String? = null,
        message: String? = null,
        buttonPos: String,
        buttonNeg: String,
        actionNeg: () -> Unit,
        actionPos: () -> Unit
    ): Dialog {
        val d = Dialog(this)
        d.requestWindowFeature(Window.FEATURE_NO_TITLE)
        d.setContentView(R.layout.dialog_default)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            d.window.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            d.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        d.findViewById<TextView>(R.id.dialog_title).text = title
        d.findViewById<TextView>(R.id.dialog_description).text = message
        d.findViewById<Button>(R.id.dialog_btn_positive).text = buttonPos
        d.findViewById<Button>(R.id.dialog_btn_negative).text = buttonNeg

        d.findViewById<Button>(R.id.dialog_btn_positive).clicks()
            .debounce(500, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                actionPos()
                d.dismiss()
            }

        d.findViewById<Button>(R.id.dialog_btn_negative).clicks()
            .debounce(500, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe {
                actionNeg()
                d.dismiss()
            }

        d.setCancelable(false)
        return d
    }


    fun toSignIn() {
        startActivity(AccountActivity.getIntent(activity))
        activity?.finish()
    }
}
