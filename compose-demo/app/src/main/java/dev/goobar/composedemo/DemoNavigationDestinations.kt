package dev.goobar.composedemo

import dev.goobar.composedemo.data.AndroidVersionInfo

object DemoNavigationDestinations {
    object VersionsList : NavigationDestination
    object VersionDetails : ArgumentDestination<AndroidVersionInfo>
}