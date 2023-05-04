# Lab 4 - Debug Compose Performance Issues

- Examine your app using Android Studio's Layout Inspector

💡 Do you see any unexpected recompositions?

- Integrate Compose Compiler reports for your project and generate a report

💡 Do you see any unexpected results?

💡 Are any of our UI-related classes marked as `unstable`?

💡 Are any of your composables not skippable?

- Fix up any issues you find by using tools such as 
    - kotlinx.immutable collections
    - `@Stable` or `@Immutable` annotations
    - moving UI data holders into the same module as the Compose compiler
    - using method references or remembered lambdas for click handlers