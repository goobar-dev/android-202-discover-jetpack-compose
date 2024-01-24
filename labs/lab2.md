# Lab 2 - Build An Interactive UI With Compose
Build an app that displays a list of Star Wars planets and allows a user to select a planet to view details for the selected planet.

## Deliverables
- Display a top app bar that includes the title of the application
- When the app is opened, display a vertically scrolling list of Star Wars planets
- Clicking a planet list item should display a `Toast` that displays the planet name
- Customize your theme with custom colors, shapes, or typography

## Challenges
- Snow a `Snackbar` instead of a `Toast` when a list item is clicked

## Dev Notes

### Where can you get Star Wars planet data?
Star Wars planet data can be pulled from [SWAPI](https://swapi.dev/)

A `SWAPINetworkClient` has been provided for you in the lab 2 starter code.
You can call `SWAPINetworkClient.getPlanets()` to get a list of planet info.
The `PlanetDTO` object includes a partial set of the available planet data. Feel free to add to it if you want based on the full set of api data.

### Where can I find starter code for this lab?
If something comes up during the training and you have missed any labs up to this point, no worries.

To help get you started, check out the `lab2-start` tag. This will provide you a bootstrapped project that you can start building off of.

If you've want to continue building out of your own project, feel free to continue doing so.
