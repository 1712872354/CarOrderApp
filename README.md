# CarOrderApp

An Android application that automatically sends an HTTP POST request to fetch car order details.

## Features

- Automatically sends HTTP POST request on app launch
- Displays HTTP status code and response data
- Supports loading indicator and status updates

## Requirements

- Android Studio
- Android SDK 24+
- JDK 11+

## Getting Started

1. Clone the repository
2. Open in Android Studio
3. Build and run on an Android device or emulator

## Build

### Local Build

```bash
./gradlew build
```

### GitHub Actions

This project is configured with GitHub Actions for automated builds. On every push to the main branch, the app will be automatically built and the APK will be uploaded as an artifact.

## Project Structure

```
CarOrderApp/
├── app/
│   ├── src/main/java/com/example/carorderapp/MainActivity.java
│   ├── src/main/res/layout/activity_main.xml
│   ├── src/main/res/values/strings.xml
│   ├── src/main/res/values/themes.xml
│   └── src/main/AndroidManifest.xml
├── .github/workflows/android-build.yml
├── build.gradle
├── settings.gradle
└── README.md
```

## License

MIT