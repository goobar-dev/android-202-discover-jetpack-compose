package dev.goobar.composedemo

import android.os.Bundle
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

interface NavigationArgs

interface NavigationDestination {
    val name: String
        get() = this::class.simpleName!!

    val route: String
        get() = name
}

interface ArgumentDestination<T: NavigationArgs> : NavigationDestination {
    val arg: String
        get() = "${name}_ARG"

    override val route: String
        get() = "$name/{$arg}"
}

/**
 * Will build a route string that includes serialized args to then be retrieved
 * using [retrieveArgs] to be passed to a target composable
 */
inline fun <reified T: NavigationArgs> ArgumentDestination<T>.createRouteWithArgs(arg: T): String {
    val argsJson = Json.encodeToString(arg)
    val encodedJson = URLEncoder.encode(argsJson)
    return "$name/${encodedJson}"
}

/**
 * Takes a bundle from a nav graph destination, and attempts to parse the specified args for the
 * given [ArgumentDestination]
 */
inline fun <reified T: NavigationArgs> ArgumentDestination<T>.retrieveArgs(args: Bundle?): T {
    val encodedJson = args?.getString(arg) ?: throw IllegalStateException("arg with key $arg was not found")
    val argsJson = URLDecoder.decode(encodedJson)
    return Json.decodeFromString(argsJson)
}