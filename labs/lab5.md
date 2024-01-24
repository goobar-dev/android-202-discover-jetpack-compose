# Lab 5 - Optimize Compose Performance

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

## Dev Notes

### Where can I find starter code for this lab?
If something comes up during the training and you have missed any labs up to this point, no worries.

To help get you started, check out the `lab5-start` tag. This will provide you a bootstrapped project that you can start building off of.

If you've want to continue building out of your own project, feel free to continue doing so.
