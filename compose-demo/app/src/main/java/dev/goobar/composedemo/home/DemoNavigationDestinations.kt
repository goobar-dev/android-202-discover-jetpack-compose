package dev.goobar.composedemo.home

import dev.goobar.composedemo.data.AndroidVersionInfo

object DemoNavigationDestinations {
    object VersionsList : NavigationDestination
    object VersionDetails : ArgumentDestination<AndroidVersionInfo>
}