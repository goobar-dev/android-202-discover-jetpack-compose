# Demos Outline

## Lesson 1 - What is Jetpack Compose
Make sure the demo project can be built and deployed

- Open up the `Compose Demo`
- Explore the project structure and contrast with an XML-based project
- Deploy the project

### Demo
- Pass different values to `Greeting` Composable
- Add a `greeting: String` parameter to `Greeting`
- Experiment with calling `Greeting` with different combinations of parameters


## Lesson 2 - Building a User Interface with Jetpack Compose

### Explore Common Composables
- How is surface being used in the initial project?
- How do `Box`, `Row` and `Column` change how Composables are positioned if we display multiple `Greeting` Composables?
- How do we use a `Button` to collect user interactions?

### Build An Interactive List Of Android Version Info
- Create a composable function named `MainActivityContent` to display a list of Android version details
- Use `AndroidVersionsRepository.data` to provide the data for the list
- Use a `LazyColumn` to display a vertically scrolling list of `Card`s displaying the version info
- Each `AndroidVersionInfo` `Card` Composable should display an icon using the `Image` Composable and should display the `AndroidVersionInfo.publicName` property
- Clicking a `Card` should display a `Toast` displaying the `publicName` property of the clicked `AndroidVersionInfo` value
- Use a `Scaffold` to provide the basic container for the screen composable
- Configure the `Scaffold` to include a `TopAppBar` that displays `"Compose Demo"`


## Lesson 3 - State Hoisting

### Explore State Hoisting APIs and Patterns

### Change Screen Content Based On Mutable State
- Refactor the code by creating an `AndroidVersionsList` Composable the displays the list of `AndroidVersionInfo`
- Create a new Composable function named `AndroidVersionDetails` that displays the details for a passed `AndroidVersionInfo`
- Create a variable name `selectedItem` to track whether or not an `AndroidVersionInfo` has been clicked.  This is our "state" that tells us whether to show `AndroidVersionsList` or `AndroidVersionDetails`
    - What happens now when we actually click a list item?
- Update that variable to use the `remember{}` delegate along with the `mutableStateOf()` function to respond to state changes and trigger recomposition
    - What happens now when we rotate the device?
- Update that variable to use `rememberSaveable{}` rather than `remember{}`
    - What happens when we try rotating the device again?
- Add the `Parcelize` plugin to the project
- Update `AndroidVersionInfo` to implement `Parcelable` and annotate it with `@Parcelize`
- Redeploy the app
    - What happens now when we try rotating the device?

### Change TopBar Based On Mutable State
- Move the `selectedItem` variable to the top of `MainActivityContent` so it can be used to control `TopBar` state
- Update the `TopBar` content so it changes based on the value of `selectedItem`
- If `selectedItem` is `null`, display the same `TopAppBar` Composable we were previously using
- If `selectedItem` is not `null` display a `TopAppBar` with the following content:
    - Display the `publicName` property of the selected `AndroidVersionInfo` 
    - Display a navigation icon for a "Back" action using `IconButton` wrapping an `Icon`
        - Create a new vector drawable for the "Back" arrow
    - Clicking the back icon should set `selectedItem` to `null`
- Redeploy the app
    - What happens if we click the back icon in the app bar?
    - What happens if we use a back gesture or click the hardware back button?
- Use the `BackHandler` Composable to respond to back clicks/gestures when viewing the `AndroidVersionDetails` Composable


## Lesson 4 - Composable Previews

### Create and Interact With a Composable Preview
- Create a Composable preview for `AndroidVersionsListScreen`
- Open the "Split" or "Design" view for the file to view the Composable preview
- Start "Interactive Mode" to examine how we can interact with Composable previews
- Click "Run Preview" to deploy the preview to a device or emulator
    - How does this compare to deploying the application?

### Customize the Preview
- View the preview we just created
    - Notice it doesn't look quite like it would on a device.  How can we change the way the preview is rendered?
- Click into the implementation of `@Preview` and examine the available configuration properties
- Make each of the following preview config changes and observe how that changes the rendered preview
    - Set the preview device to `Devices.PIXEL_7_PRO`
    - Set `showBackground = true`
    - Set `showSystemUi = true`
    - Set `uiMode = UI_MODE_NIGHT_YES`

### Generate Multiple Previews from a Single Composable Preview
- Duplicate the `@Preview` config
- Change the `device` parameter to `Devices.PIXEL_TABLET`
    - How did this impact the preview rendering?
- Duplicate the 2 `@Preview` configs and set `uiMode = UI_MODE_NIGHT_NO`
    - How did this impact the preview rendering?
- Give each `@Preview` config a `group` value
    - For each phone preview, set `group = "pixel7 pro"`
    - For each tablet preview, set `group = "pixel tablet"`
    - Notice how this changes the available options in group dropdown within the preview render view?
- Create a new file `ui/tooling/StandardPreview.kt`
- Within that file, create a new `annotation class StandardPreview`
- Remove the `@Preview` annotations from `Preview_AndroidVersionsListScreen` and apply to `StandardPreview`
- Apply the newly created `@StandardPreview` annotation to `Preview_AndroidVersionsListScreen`
    - How does this impact the rendered previews?

### Generate Previews for Different Data
- Refactor `AndroidVersionsListScreen` Composable to take a `List<AndroidVersionInfo>` as a parameter
- In `VersionListScreen.kt` create a new class `AndroidVersionInfoProvider` that implements `PreviewParameterProvider<List<AndroidVersionInfo>>`
- Implement the `values` property of `AndroidVersionInfoProvider` to provide the following sequence values
    - an empty list
    - a list with a single item
    - a list with two items
    - the full list of available data
- Update `Preview_AndroidVersionsListScreen` Composable to take a parameter `infos: List<AndroidVersionInfo>`
- Annotate `infos` with `@PreviewParameter(AndroidVersionInfoProvider::class)`
- Pass `infos` parameter value to `AndroidVersionsListScreen`
    - How does this impact the rendered previews?


## Lesson 5 - Customizing the Material Theme
- Examine the implementation of `ComposeDemoTheme`
    - What sticks out to you?
- Define a new set of colors in `Colors.kt`
- Within `Theme.kt` update `DarkColorScheme` and `LightColorScheme` using the newly defined colors
- Redeploy the app
    - How did the new colors impact your app?
    - Did you notice a change?
- Change the wallpaper of your device
    - Did that impact the theme of your app?
- Disable the dynamic theme in `Theme.kt` and reexamine your app theme
- Switch your device from Light and Dark mode
    - How is the app responding to these configuration changes?
- Ensure that some `Text` Composable in your `AndroidVersionInfo` `Card` is using `style = MaterialTheme.typography.headlineMedium`
- Open `Type.kt`
- Customize the `headlineMedium` parameter of the `Typography` class
    - How did this impact the look of your app?
- Create a new file `ui/theme/Shapes.kt`
- Create an instance of `Shapes` as a top-level property
- Customize the `medium` parameter of `Shapes` using the `RoundedCornerShape` function
- Pass the newly created `Shapes` property to the `shapes` parameter when calling `MaterialTheme` in `Theme.kt`
    - What impact does this have on our `Card`s?


## Lesson 6 - Common UI Patterns

### Navigation
- Add the `androidx.navigation:navigation-compose` dependency to the project
- Create a new file `NavigationGraph.kt`
- Create a Composable named `ComposeDemoNavigationGraph`
- Within the Composable, create a remembered `NavController` using `rememberNavController()`
- Call the `NavHost` Composable; passing in the `NavController` and `"versionsList"` as the `startDestination`
- Define our first target destination within `NavHost`
    - One destination should have a `route` of `"versionsList"`.  This is our `VersionsList` route.
- The `VersionsList` route should call `AndroidVersionsListScreen`
    - We will leave the click handler empty for now
- In `MainActivity.kt`, replace the call of `MainContent` with `ComposeDemoNavigationGraph`
    - Notice what this does to the app when deployed?
- Return to `NavigationGraph.kt` and create a new route `"versionDetails/{apiVersion}"`.  This is our `VersionDetails` route.
    - The `{apiVersion}` placeholder will enable us to pass an argument to our route to specify which `AndroidVersionInfo` to display
- Update the `arguments` parameter for the `VersionDetails` route to take a single `NavArgument` named `apiVersion` of type `NavType.IntType`
- The `VersionDetails` route should call `AndroidVersionDetailsScreen()`
- Parse the `apiVersion` nav argument from the `versionDetails` backstack entry and pass to the composable
- Update `AndroidVersionDetailsScreen` to take a parameter `apiVersion: Int` rather than `info: AndroidVersionIfno`
- Within `AndroidVersionDetailsScreen`, use the `apiVersion` parameter to load the desired `AndroidVersionInfo`
- Update the `VersionsList` destination to navigate to the `VersionDetails` destination when a list item is clicked
- Deploy the app and test the navigation is working
- Update `AndroidVersionDetailsScreen` and `AndroidVersionDetailsScreen` so they show their own `Scaffold` and `TopBar`

### Type-Safe Navigation
- Add the `org.jetbrains.kotlin.plugin.serialization` plugin to the project
- Add the `org.jetbrains.kotlin:kotlin-serialization` library to the project
- Create a new file `NavigationDestination`
- In that file, create the following
    - `interface NavigationArgs`
    - `interface NavigationDestination` with properties `name: String` and `route: String`
    - `interface ArgumentDestination<T: NavigationArgs> : NavigationDestination` with properties `arg: String` and overriding `route: String`
- Create a new file `DemoNavigationDestinations` and create an object class `DemoNavigationDestinations`
- Within that object class, create the following
    - `object VersionsList : NavigationDestination`
    - `object VersionDetails : ArgumentDestination<AndroidVersionInfo>`
- Make `AndroidVersionInfo` implement `NavigationArgs`
- Apply the `@Serializable` annotation to `AndroidVersionInfo
- Update `AndroidVersionDetailsScreen` to take a parameter `info: AndroidVersionInfo` rather than `apiVersion: Int`
- Within `NavigationDestination.kt` implement the `createRouteWithArgs()` and `retrieveArgs()` functions
- Update `ComposeDemoNavigationGraph` to use the `createRouteWithArgs()` and `retrieveArgs()` functions to serialize and deserialize the clicked `AndroidVersionInfo`

### Animated Navigation
- Update the `VersionDetails` destination `composable()` call by passing an `enterTransition` and `exitTransition`
    - How does this impact the look and feel of the navigation?


## Lesson 7 - MVVM with Compose

### Expose Data Using MutableState Type
- Create an `AndroidVersionsListViewModel` class that implements `ViewModel`
- Expose a property on that class of type `val versionsListState: MutableState<List<AndroidVersionInfo>>`
- Update `AndroidVersionsListScreen` to take a parameter `viewModel: AndroidVersionsListViewModel` with a default value using the `viewModel()` function
- Observe changes to `AndroidVersionListViewModel.versionsListState` using a variable `versionsListState`
- Extract the `Scaffold`, and its content, from `AndroidVersionsListScreen` into a new Composable `AndroidVersionsListContent`

### Expose Data Using StateFlow
- Add the `androidx.lifecycle:lifecycle-runtime-compose` library dependency
- Create an `AndroidVersionDetailsViewModel` class that implements `ViewModel`
- Within that class, create a data class `State` with properties for `title: String`, `subtitle: String`, `description: String`
- Expose a property on that class of type `val state: StateFlow<State> = MutableStateFlow`
- Update `AndroidVersionDetailsScreen` to take a parameter `viewModel: AndroidVersionDetailsViewModel` rather than `info: AndroidVersionInfo`
- Observe changes to `AndroidVersionDetailsViewModel.state` using a variable `state` and the `collectAsStateWithLifecycle` function
- Update the implementation of `AndroidVersionDetailsScreen` Composable to pull date from `state`
- Update `ComposeDemoNavigationGraph` Composable to pass an instance of `AndroidVersionDetailsViewModel` to `AndroidVersionDetailsScreen` Composable

### Implement Version List Sorting
- Create a `Sort` enum class within `AndroidVersionsListViewModel.kt`
- Add values `ASCENDING` and `DESCENDING`
- Add `State` data class to `AndroidVersionsListViewModel` 
- Add inner data class to `State` named `AndroidVersionViewItem` with properties for `title: String`, `subtitle: String`, `description: String` and `info: AndroidVersionInfo`
- Add property to `State` `val versionsList: List<AndroidVersionViewItem>`
- Create two private `MutableStateFlow`
    - One for `Sort`
    - One for `List<AndroidVersionInfo>`
- Create a public `StateFlow` named `state` that does the following
    - Sorts the items based on the current `Sort` value
    - Converts each `AndroidVersionInfo` into `AndroidVersionViewItem`
- Update `VersionsListScreen` to pull data from the updated `state` property on `AndroidVersionsListViewModel`
- Add "Sort" icon to `TopBar` in `VersionsListScreen`
- Call `AndroidVersionsListViewModel.onSortClicked` whenever the "Sort" icon is clicked
- Deploy the app and observe how the list content changes
    - What do you notice about the ordering and scroll state when the icon is clicked?
- Update the `LazyColumn` to use `itemsIndexed()` so that when the sort state changes, the list remains where it was

### Animate Content
- Within `AndroidVersionInfoCard` create a variable `isDetailExpanded` using `rememberSaveable()`
    - Use `viewItem.info.apiVersion` as the savedable key
    - Initialize with `mutableStaetOf(false)`
- Wrap the description text in a call to `AnimatedVisibility` Composable and pass `isDetailExpanded` as the key
- Add an "Info" icon to the end of the title text
- When the icon is clicked, change the value of `isDetailExpanded`
    - How does this change the app behavior?
- Check that the `viewItem.description` is not blank before showing the "Info" icon
    - How does this change the app behavior?


## Lesson 8 - Adaptive Design

### Adapting to Screen Size
- Add the `androidx.compose.material3:material3-window-size-class` library depdendency
- In `MainActivity.kt` get the current `WindowSizeClass` using `calculateWindowSizeClass()`
- Update `ComposeDemoNavigationGraph` to take a parameter `windowSizeClass: WindowSizeClass` and pass the instance to the Composable
- Within `ComposeDemoNavigationGraph.kt`, refactor the existing implementation into a new Composable named `PhoneNavigationGraph`
- Within `ComposeDemoNavigationGraph.kt`, create a new Composable named `TabletNavigationGraph`
- Within `ComposeDemoNavigationGraph` Composable, check if the passed `WindowSizeClass` has a "compact" width
    - If it does, show `PhoneNavigationGraph`
    - If not, show `TabletNavigationGraph`
- For the tablet layout, show the `AndroidVersionsListScreen` Composable on the left and the `AndroidVersionDetailsScreen` on the right
- Allow for customizing whether or not the `TopBar` is shown in `AndroidVersionDetailsScreen`

### Adapting to Screen Orientation
- Create a new file `ui/config/ScreenConfiguration.kt`
- In this file, create a new data class `ScreenConfiguration`
- In this class, define an enum class `Orientation` was values `UNDEFINED`, `VERTICAL` and `HORIZONTAL`
- Add properties `isCompact: Boolean` and `orientation: Orientation` to `ScreenConfiguration`
- Create a method named `from` inside a companion object on `ScreenConfiguration` that takes parameters `windowsSizeClass: WindowSizeClass` and `configuration: Configuration`
- Implement `from` to construct and return an instance of `ScreenConfiguration`
- Update `ComposeNavigationDemo` Composable to take a parameter `screenConfiguration: ScreenConfiguration` rather than `windowSizeClass`
- In `MainActivity`, create an instance of `ScreenConfiguration` and pass to `ComposeDemoNavigationGraph`
- Update the implementation of `ComposeDemoNavigationGraph` to only show the tablet layout if the screen is not a phone and not vertical
- Update `VersionsListScreen` to control the vertical or horizontal content based off a passed `screenConfiguration: ScreenConfiguration` parameter


## Lesson 9 - Testing Composables
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


## Lesson 10 - Compose Performance
- Use Android Studio's Layout Inspector to examine recomposition counts for the app
- Modify app to simulate a "heartbeat" the generates more frequent recompositions
- Integrate Compose Compiler metrics
- Generate a Compose Compiler metrics report
- Examine the report to understand what could be causing recompositions, or why recompositions are not happening
- Modify `AndroidVersionViewItem` to make it unstable
    - How does this impact the recomposition count?
- Update the `State` classes and `AndroidVersionsListScreen` Composables to be stable and skippable