# 🚀 Quick Setup Guide - SocialMediaApp Android

## Step-by-Step Installation

### 1. Clone and Import
```bash
# Clone repository
git clone https://github.com/david0154/SocialMediaApp-Android.git
cd SocialMediaApp-Android

# Open in Android Studio
# File > Open > Select the project folder
```

### 2. Configure API Endpoints

**Edit `app/build.gradle`:**
```gradle
defaultConfig {
    // Replace with your actual API domain
    buildConfigField "String", "API_BASE_URL", '"https://your-api.com/api/"'
    
    // Replace with your OneSignal App ID
    buildConfigField "String", "ONESIGNAL_APP_ID", '"your-onesignal-id"'
}
```

### 3. Install Dependencies

Android Studio will automatically sync Gradle. If not:
```bash
./gradlew build
```

### 4. Create a Virtual Device (if needed)

1. Open Android Virtual Device Manager
2. Create a new device with:
   - Device: Pixel 5 (or any device)
   - Android Version: 7.0 or higher (API 24+)
   - RAM: 2GB minimum (4GB recommended)
   - Storage: 2GB minimum

### 5. Run the App

**Option A: From Android Studio**
- Click Run menu > Run 'app'
- Or press `Shift + F10`

**Option B: From Terminal**
```bash
./gradlew installDebug
```

### 6. Test on Different Android Versions

| Android Version | Test on |
|-----------------|----------|
| Android 7.0 | API 24 |
| Android 8.0 | API 26 |
| Android 9.0 | API 28 |
| Android 10.0 | API 29 |
| Android 11.0 | API 30 |
| Android 12.0 | API 31 |
| Android 13.0 | API 33 |
| Android 14.0 | API 34 |
| Android 15.0 | API 35 |

---

## 🔐 Configuration Details

### API Configuration

**Create file: `app/src/main/kotlin/com/davidstudioz/socialmedia/di/ApiModule.kt`**

```kotlin
package com.davidstudioz.socialmedia.di

import android.content.Context
import com.davidstudioz.socialmedia.BuildConfig
import com.davidstudioz.socialmedia.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Timber.d(message)
        }.apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
```

### Database Module

**Create file: `app/src/main/kotlin/com/davidstudioz/socialmedia/di/DatabaseModule.kt`**

```kotlin
package com.davidstudioz.socialmedia.di

import android.content.Context
import com.davidstudioz.socialmedia.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideMessageDao(db: AppDatabase) = db.messageDao()

    @Provides
    @Singleton
    fun provideConversationDao(db: AppDatabase) = db.conversationDao()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Provides
    @Singleton
    fun provideMediaDao(db: AppDatabase) = db.mediaDao()

    @Provides
    @Singleton
    fun provideNotificationDao(db: AppDatabase) = db.notificationDao()

    @Provides
    @Singleton
    fun provideSettingsDao(db: AppDatabase) = db.settingsDao()
}
```

---

## 📋 Directory Structure for New Files

When adding new files, follow this structure:

```
app/src/main/kotlin/com/davidstudioz/socialmedia/
├── activities/
│   ├── base/              # Base activity classes
│   ├── auth/              # Login, Register, Forgot Password
│   ├── chat/              # All chat activities
│   ├── media/             # Image/Video/Location editing
│   ├── profile/           # User/Page profiles
│   ├── viewer/            # Image/Video viewers
│   └── social/            # Posts, comments, etc
├── data/
│   ├── api/               # Retrofit API service
│   ├── models/            # Data classes
│   ├── local/             # Room database
│   └── repository/        # Data access layer
├── di/                 # Dependency injection modules
├── ui/
│   ├── viewmodels/        # MVVM ViewModels
│   ├── adapters/          # RecyclerView adapters
│   ├── fragments/         # Fragment classes
│   └── utils/             # UI utilities
├── services/           # Background services
├── utils/              # Common utilities
└── MainApplication.kt
```

---

## 🔍 Troubleshooting

### Issue: "Failed to resolve: com.onesignal:OneSignal"
**Solution:**
1. Sync Gradle again
2. Check internet connection
3. Update build cache: `./gradlew clean`

### Issue: "API_BASE_URL not defined"
**Solution:**
- Make sure you updated `app/build.gradle` with your API endpoint
- Rebuild the project

### Issue: "Cannot resolve symbol MainApplication"
**Solution:**
1. Check package name matches `com.davidstudioz.socialmedia`
2. Clean project: `Build > Clean Project`
3. Rebuild: `Build > Rebuild Project`

### Issue: "Compilation failed: Unresolved reference"
**Solution:**
1. Sync Gradle again
2. Invalidate caches: `File > Invalidate Caches > Clear index`
3. Restart Android Studio

### Issue: "App crashes on launch"
**Solution:**
1. Check logcat for error messages
2. Ensure all required permissions are in `AndroidManifest.xml`
3. Verify API endpoint is correct
4. Check OneSignal App ID is valid

---

## 🌟 Running on Physical Device

1. Enable Developer Mode on device:
   - Settings > About Phone > Tap "Build Number" 7 times

2. Enable USB Debugging:
   - Settings > Developer Options > USB Debugging

3. Connect device via USB

4. Grant USB permission when prompted on device

5. Run app from Android Studio

---

## 📄 Additional Resources

- [Android Studio Documentation](https://developer.android.com/studio)
- [Kotlin Documentation](https://kotlinlang.org/docs)
- [Retrofit Documentation](https://square.github.io/retrofit/)
- [Room Database Guide](https://developer.android.com/training/data-storage/room)
- [Coroutines Guide](https://developer.android.com/kotlin/coroutines)
- [Hilt Dependency Injection](https://dagger.dev/hilt/)

---

**Need more help? Check the full [README.md](README.md) or open an issue on GitHub.**
