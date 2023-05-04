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