package com.yotalabs.koral.utils

import timber.log.Timber

/**
 * @author SashaKhyzhun
 * Created on 11/27/18.
 */
object ThreadAwareTree : Timber.DebugTree() {

    private const val tagFormat = "[%s] %s"

    override fun createStackElementTag(element: StackTraceElement): String? {
        val tag = super.createStackElementTag(element)
        return String.format(tagFormat, Thread.currentThread().name, tag)
    }

}