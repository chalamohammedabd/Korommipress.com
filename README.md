# Korommi Press

A modern Android social information and news publishing platform built with Kotlin, Jetpack Compose, and Firebase.

## Features

- **User Authentication**: Secure registration and login with Firebase Authentication
- **News Publishing**: Publish text, images, and multimedia content
- **Social Engagement**: Like, comment, reply, and share posts
- **User Profiles**: Customizable profiles with bio and follower counts
- **Real-time Feed**: Live updates using Firestore
- **Push Notifications**: Firebase Cloud Messaging (FCM) for real-time notifications
- **Search**: Search users, posts, and hashtags
- **Admin Dashboard**: Manage inappropriate content and user reports
- **Dark Mode**: Full Material 3 dark mode support

## Technology Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material 3
- **Architecture**: MVVM + Clean Architecture
- **Navigation**: Jetpack Navigation Compose
- **Backend**: Firebase (Auth, Firestore, Storage, Messaging, Analytics)
- **Build System**: Gradle
- **Minimum SDK**: 24
- **Target SDK**: 34

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── kotlin/com/korommipress/
│   │   │   ├── ui/
│   │   │   │   ├── screens/
│   │   │   │   ├── components/
│   │   │   │   ├── theme/
│   │   │   │   └── navigation/
│   │   │   ├── viewmodel/
│   │   │   ├── repository/
│   │   │   ├── data/
│   │   │   ├── domain/
│   │   │   ├── firebase/
│   │   │   ├── utils/
│   │   │   └── MainActivity.kt
│   │   ├── res/
│   │   └── AndroidManifest.xml
│   └── google-services.json
├── build.gradle.kts
└── proguard-rules.pro
```

## Firebase Configuration

### Project Details
- **Project ID**: korommipress
- **Project Number**: 608182979127
- **Storage Bucket**: korommipress.firebasestorage.app
- **Package Name**: com.korommipress.app

### Firestore Collections
1. **users** - User profiles and authentication data
2. **posts** - Published posts and content
3. **comments** - Post comments and replies
4. **likes** - Post and comment likes
5. **notifications** - User notifications
6. **followers** - Follow relationships
7. **reports** - User-reported content

## Setup Instructions

### Prerequisites
- Android Studio (latest version)
- JDK 11 or higher
- Android SDK 24 and above
- Firebase account with korommipress project

### Installation

1. Clone the repository:
```bash
git clone https://github.com/chalamohammedabd/Korommipress.com.git
cd Korommipress.com
```

2. Update `google-services.json`:
   - Place the `google-services.json` file in the `app/` directory
   - Ensure the package name matches `com.korommipress.app`

3. Build the project:
```bash
./gradlew build
```

4. Run on emulator or device:
```bash
./gradlew installDebug
```

## Firebase Security Rules

See `firestore-security-rules.txt` and `storage-security-rules.txt` for Firestore and Storage security configuration.

## Development Guidelines

### Code Style
- Follow Kotlin style guide
- Use MVVM pattern for all screens
- Implement proper error handling
- Use dependency injection where applicable

### Testing
- Unit tests for ViewModels and Repositories
- Integration tests for Firebase operations
- UI tests for critical user flows

## Deployment

### Play Store
1. Build release APK/AAB:
```bash
./gradlew bundleRelease
```

2. Sign the bundle with your release keystore

3. Upload to Play Store Console

## Contributing

Please follow the existing code structure and patterns. Create feature branches and submit pull requests for review.

## License

All rights reserved © 2024 Korommi Press

## Support

For issues and questions, please contact the development team.
