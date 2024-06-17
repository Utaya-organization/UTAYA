package com.dicoding.utaya.ui.Bottom.history

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class History(
    val skin: String,
    val foto: Int
) : Parcelable
