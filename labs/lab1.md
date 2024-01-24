# Lab 1 - Explore A New Compose Project

## Observe the project dependencies
- Open the `compose-labs` project in Android Studio
- Sync the project in Android Studio
- Open the `gradle/libs.versions.toml` file and review the declared dependencies
- Open the root-level `build.gradle` file and observe specified plugins

**💡 What version of the Android Gradle Plugin is the project using?**

**💡 What version of Kotlin is the project using?**

- Open `app/build.gradle` file and observe the project configuration

**💡 What version of Compose is the project using?**
- Check out the [Compose to Kotlin Compatibility Map](https://developer.android.com/jetpack/androidx/releases/compose-kotlin) to learn more about what Compose/Kotlin combinations work together.

**💡 What version(s) of Compose libraries is the project using?**

**💡 How are those versions kept in sync?**
- Check out the guide on the [Compose Bill of Materials (BOM)](https://developer.android.com/jetpack/compose/bom)

- Observe the Compose-related `debugImplementation` dependencies

**💡 Why do you think those dependencies are only needed for the debug classpath?**

## Build and deploy the project
- Build the project and deploy to a device or emulator

**💡 What output do you see on the screen?**

## Explore a Compose Activity
- Open `MainActivity.kt` and observe the `onCreate()` method

**💡 How does this method differ from what you might be used to in a
non-Compose app?**

## Explore a simple Composable
- Observe the `Greeting` Composable function

**💡 In what ways does the declaration of this function differ from a
normal function?**

## Modify a Composable
- Change the text based to the `Text` Composable, and redeploy to observe the new output
- Change the `Greeting` Composable to take a `greeting: String` parameter with a default value of `"Hello"`
- Experiment with calling `Greeting` multiple times with different `greeting` and `name` parameters

- Explore the rest of the project

**💡 What other aspects of this project stick out to you?**

**💡 What questions, if any, does the initial project setup raise?**