# Demos Outline

## Lesson 1 - What is Jetpack Compose
Make sure the demo project can be built and deployed

- Open up the `Compose Demo` app and explore the project setup
- Deploy the project

## Lesson 2 - Building a User Interface with Jetpack Compose
- Create a composable function named `MainActivityContent` to display a list of Android version details
- Use `AndroidVersionsRepository.data` to provide the list of data
- Use a `Scaffold` to provide the basic container for the screen composable
- Configure the `Scaffold` to include a `TopAppBar` that displays the app title
- Use a `LazyColumn` to display a vertically scrolling list of `Cards` displaying the version info
- Clicking a card should display a `Snackbar`
- Change the look of the app by customizing the `MaterialTheme`

## Lesson 3 - State Hoisting
- Use `by rembmer { ... }` to create a variable that holds the currently selected `AndroidVersionInfo`
- If an item is selected, update the `TopAppBar` to show a back button, and the display name of the selected info
- If an item is selected, update the content of `Scaffold` to show an `AndroidVersionDetails` composable
- Refactor the list of `AndroidVersionInfo` into a new composable named `AndroidVersionsList` to display when no info is selected
- Update the `AndroidVersionInfoCard` click handler to set the selected info state
- Set the `onClick` handler for the back icon so clicking back clears the currently selected item
- Update `AndroidVersionInfo` to implement `Parcelable`
- Update the declaration of `selectedItem` to use `rememberSaveable{}` to preserve state across configuration change

## Lesson 4 - Navigation
Start by setting up a basic navigation graph:

- Create a new file `DemoNavigationGraph` and create a composable `DemoNavigationGraph`
- Create a remembered `NavController` by calling `rememberNavController()`
- Setup a `NavHost` with 2 composable destinations
    - One route named `"versionsList"`
    - One route named `"versionDetails/{apiVersion}"`
    - The `versionDetails` route should configure the `apiVersion` argument to be of type `Int`
- Update the `AndroidVersionListScreen` click handler to navigate to the details screen using the `NavController`
- Parse the `apiVersion` nav argument from the `versionDetails` backstack entry and pass to the composable
- Update the `AndroidVersionDetailsScreen` back click handler to pop the `NavController` backstack
- Update `MainActivity` to call `DemoNavigationGraph` instead of `MainActivityContent`
- Remove `HomeScreen.kt` and update `AndroidVersionListScreen` and `AndroidVersionDetailsScreen` to include their own `Scaffold`

Refactor the navigation graph configuration to use a custom type-safe routing implementation:
- Add `kotlinx.serialization` to the project and add `@Serializable` annotation to `AndroidVersionInfo`
- Create a file named `NavigationDestination.kt`
- Create a `NavigationArgs` interface to apply to classes that will be passed along with navigation routes
- Create a `NavigationDestination` interface with `name` and `route` properties
- Create an `ArgumentDestination` interface extending `NavigationDestination`
- Create extension methods on `ArgumentDestination` named `createRouteWithArgs()` and `retrieveArgs()`
- Define object classes to represent our `VersionsList` and `VersionDetails` destinations
- Refactor `DemoNavigationGraph` to use these new type-safe destinations

Refactor the navigation graph implementation to use the animated navigation apis from the Accompanist libraries.
- Add the latest stable `com.google.accompanist:accompanist-navigation-animation` version to the project's `app/build.gradle` file
- Replace `rememberNavController()` and `NavHost` with `rememberAnimatedNavController()` and `AnimatedNavHost()`
- Configure `enterTransition` and `exitTransition` for the version details route
- Use `AnimatedContentScope.SlideDirection.Left` for the enter `towards` value and `AnimatedContentScope.SlideDirection.Right` for the exit animation
- Customize the `animationSpec` using the `tween()` function

## Lesson 5 - MVVM & Compose
- Create an `AndroidVersionsListViewModel`
- Within `AndroidVersionsListViewModel` create a `State` data class that holds a `List<AndroidVersionViewItem>`
- Create an `AndroidVersionDetailsViewModel`
- Within `AndroidVersionDetailsViewModel` create a `State` data class that holds the display details
- Update `AndoridVersionsListScreen` to take a `AndroidVersionsListViewModel` as a parameter
- Add a `StateFlow` to `AndroidVersionsListViewModel` to expose an instance of `State`
- Add a `StateFlow` to `AndroidVersionDetailsViewModel` to expose an instance of `State`
- Update composables to observe data from the `ViewModel`s
- Implement a sort capability in `AndroidVersionsListScreen` using the `ViewModel` to track sort state and sort the items

## Lesson 6 - Testing Composables
- Create a new test class `AndroidVersionsListTest` in the `androidTest` source set
- Add a new test rule using `createComposeRule()`
- Create new test method named `versionsListDisplayedOnHomeScreenLoad()`
    - Validate that a composable with `testTag = "Versions List"` exists in the tree
- Create new test method named `versionsListDisplaysFirstVersionInfo()`
    - Validate that the first item in the list matches what is expected from the repository
- Create a new test method named `printTreeToLog()`
    - Use the `printToLog()` method to view the semantics tree
- Create a new test method named `versionInfoClickHandlerCalledWhenCardIsClicked()`
    - Validate that clicks on a list item are propagated to the click handler

## Lesson 7 - Improving Compose Experience
- Refactor `AndroidVersionsListScreen` to include a `AndroidVersionsList` composable that takes a `List<AndroidVersionsListViewModel.State.AndroidVersionViewItem>`
- Write a preview composable for `AndroidVersionsListScreen`
- Write a preview composable for `PreviewAndroidVersionsList` that uses a `PreviewParameterProvider` to generate previews for different sets of data
- Create a multi-preview annotation and apply it to `PreviewAndroidVersionsListScreen()` to generate a multitude of previews across different device configurations

## Lesson 8 - Compose Performance
- Use Android Studio's Layout Inspector to examine recomposition counts for the app
- Modify app to simulate a "heartbeat" the generates more frequent recompositions
- Integrate Compose Compiler metrics
- Generate a Compose Compiler metrics report
- Examine the report to understand what could be causing recompositions
- Update the `State` classes and `AndroidVersionsListScreen` composables to be stable and skippable