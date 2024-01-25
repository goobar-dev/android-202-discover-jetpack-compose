package dev.goobar.composedemo.data

import android.os.Parcelable
import dev.goobar.composedemo.NavigationArgs
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class AndroidVersionInfo(
    val apiVersion: Int,
    val publicName: String,
    val codename: String,
    val details: String
): Parcelable, NavigationArgs