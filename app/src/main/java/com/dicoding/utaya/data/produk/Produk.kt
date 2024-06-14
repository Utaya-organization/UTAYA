package com.dicoding.utaya.data.produk

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Produk(
    val merk: String,
    val harga: String,
    val link: String,
    val artikel: String,
    val foto: Int
) : Parcelable
