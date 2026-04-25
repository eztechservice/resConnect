# PH Emergency Numbers App

This is a simple Android application that lists important emergency hotlines in the Philippines.

## Features
- Categorized emergency numbers (National, Disaster, Local Government, etc.)
- Click-to-dial functionality using the system dialer.
- Built with Jetpack Compose and Material 3.

## How to build
1. Open this project in Android Studio.
2. Sync the project with Gradle files.
3. Run the `app` module on an emulator or physical device.

## Permission
The app uses the `CALL_PHONE` permission in the manifest, but uses `Intent.ACTION_DIAL` for a better user experience (opens the dialer with the number pre-filled).
