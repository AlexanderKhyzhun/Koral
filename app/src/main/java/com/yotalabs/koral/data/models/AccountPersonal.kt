package com.yotalabs.koral.data.models

import android.graphics.Bitmap
import com.yotalabs.koral.ui.adapters.DisplayableItem

/**
 * @author SashaKhyzhun
 * Created on 5/6/19.
 */
data class AccountPersonal(
    var uuid: String,
    var firstName: String,
    var lastName: String,
    var phoneNumber: String,
    var email: String,
    var photo: Bitmap
) : DisplayableItem
