package com.yotalabs.koral.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.yotalabs.koral.R
import com.yotalabs.koral.ui.mvp.BaseActivity

class AccountActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
    }

    companion  object {
        const val TAG = "AccountActivity"

        fun getIntent(context: Context?) = Intent(context, AccountActivity::class.java)
    }

}
