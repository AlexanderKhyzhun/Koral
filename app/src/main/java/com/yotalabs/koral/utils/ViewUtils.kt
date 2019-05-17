package com.yotalabs.koral.utils

import android.content.Intent
import android.content.res.Resources
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.yotalabs.koral.App

fun TextView.underline() {
    val content = SpannableString(this.text)
    content.setSpan(UnderlineSpan(), 0, content.length, 0)
    this.text = content
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.smoothToShow() {
    startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in))
    this.setVisible()
}

fun View.smoothToHide() {
    startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_out))
    this.setGone()
}

fun View.setInvisible() {
    this.visibility = View.INVISIBLE
}

fun Int.dp(): Int {
    if (this == 0) {
        return 0
    }
    val metrics = App.context.resources?.displayMetrics
    require(metrics != null, { "Metrics is null" })
    return (this * metrics.density).toInt()
}

val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Float.dp: Float get() = (this / Resources.getSystem().displayMetrics.density)
val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()
val Float.px: Float get() = (this * Resources.getSystem().displayMetrics.density)

val Float.dpToPx: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
    )


