# Shilpa-Kala Showcase Android App

This is a Kotlin Android Studio project generated from `readme.md.txt`.

## What is implemented

- Kotlin Android app package: `com.shilpakala.showcase`
- Splash screen with heritage branding
- Buyer home screen with featured sculptures, verified Shilpis, and heritage stories
- Gallery screen with style/material chips
- Product detail screen with seller card, specs, save action, and WhatsApp enquiry intent
- Saved/bookmark screen backed by in-memory state for the first runnable version
- Seller dashboard with portfolio metrics and portfolio grid
- English and Kannada string resources
- Firebase dependencies are present; the Google Services plugin is commented until `google-services.json` is added

## Open in Android Studio

1. Open `C:\Users\sbabu\OneDrive\Desktop\project` in Android Studio.
2. Let Gradle sync download the Android Gradle Plugin and Kotlin plugin.
3. If Android Studio asks for SDK 34, install it from SDK Manager, or update `compileSdk` to an installed SDK.
4. Add Firebase later by placing `google-services.json` in `app/` and uncommenting `id("com.google.gms.google-services")` in `app/build.gradle.kts`.

## Next production steps

- Replace `ShowcaseRepository` sample data with Firebase repositories.
- Add Room for persistent bookmarks.
- Split the current single-activity prototype into the full 23-screen MVVM package structure from the SOP.
- Add image upload, OTP auth, Firestore security rules, and Storage rules before real deployment.
