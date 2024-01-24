package dev.goobar.composedemo.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AndroidVersionInfo(
    val apiVersion: Int,
    val publicName: String,
    val codename: String,
    val details: String
): Parcelable