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