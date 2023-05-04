package dev.goobar.composedemo.data

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelable
import androidx.versionedparcelable.VersionedParcelize
import dev.goobar.composedemo.home.NavigationArgs
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@VersionedParcelize
data class AndroidVersionInfo(
    val apiVersion: Int,
    val publicName: String,
    val codename: String,
    val details: String
): VersionedParcelable, Parcelable, NavigationArgs