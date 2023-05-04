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