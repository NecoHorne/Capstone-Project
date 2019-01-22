# Capstone Project
## Android Developer Nanodegree Capstone Project

## Capstone Stage-1
In Stage 1, you will design and plan your app, and receive feedback prior to building it in Stage 2.
### Required Tasks
1. Review the requirements for the app in this [rubric](https://review.udacity.com/#!/rubrics/64/view)

2. Make a copy of this [template](https://docs.google.com/document/d/1gKP6RxykeekNk5bYxXIKjEitKDPdxpRyIaa9t50bLSA/edit)

3. Rename the copy: "Capstone_Stage1"
4. Fill out each section:
 * App Description
 * UI Mocks
 * Key Considerations
 * Next Steps: Required Tasks

## Capstone Stage-2
### Breakdown
This project is broken down into 2 parts, the parser program that creates the initial database and the Android project.

### Parser
For information on the Parser program read the Parser's individual README.

### Android App
Code for the Android app is in the `GymAppAndroid` directory.

### Required Tasks
1. Successfully complete Stage 1.
2. Build your app using your Stage 1 document as a guide.
3. Submit your app source code through the Nanodegree project portal.

Your project will be evaluated by a Udacity Code Reviewer according to this [rubric](https://review.udacity.com/#!/rubrics/1883/view).

#### Common Project Requirements
- [X] App conforms to common standards found in the Android Nanodegree General Project Guidelines
- [X] App is written solely in the Java Programming Language
- [X] App utilizes stable release versions of all libraries, Gradle, and Android Studio.

#### Core Platform Development
- [X] App integrates a third-party library.
- [X] App validates all input from servers and users. If data does not exist or is in the wrong format, the app logs this fact and does not crash.
- [X] App includes support for accessibility. That includes content descriptions, navigation using a D-pad, and, if applicable, non-audio versions of audio cues.
- [X] App keeps all strings in a `strings.xml` file and enables RTL layout switching on all layouts.
- [] App provides a widget to provide relevant information to the user on the home screen.

#### Google Play Services
- [X] App integrates two or more Google services. Google service integrations can be a part of Google Play Services or Firebase.
- [] Each service imported in the build.gradle is used in the app.
- [X] If Location is used, the app customizes the user’s experience by using the device's location. **N/A**
- [X] If Admob is used, the app displays test ads. If Admob was not used, student meets specifications.
- [X] If Analytics is used, the app creates only one analytics instance. If Analytics was not used, student meets specifications.
- [X] If Maps is used, the map provides relevant information to the user. If Maps was not used, student meets specifications. **N/A**
- [X] If Identity is used, the user’s identity influences some portion of the app. If Identity was not used, student meets specifications.

#### Material Design
- [X] App theme extends `AppCompat`.
- [X] App uses an app bar and associated toolbars.
- [X] App uses standard and simple transitions between activities.

#### Building
- [X] App builds from a clean repository checkout with no additional configuration.
- [] App builds and deploys using the installRelease Gradle task.
- [] App is equipped with a signing configuration, and the keystore and passwords are included in the repository. Keystore is referred to by a relative path.
- [X] All app dependencies are managed by Gradle.

#### Data Persistence
- [X] App stores data locally using Room OR Firebase Realtime Database. No third party frameworks may be used.
- [X] It it performs short duration, on-demand requests(such as search), app uses an AsyncTask.
- [X] If Room is used then LiveData and ViewModel are used when required and no unnecessary calls to the database are made.

