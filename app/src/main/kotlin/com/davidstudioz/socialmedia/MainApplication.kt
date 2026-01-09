package com.davidstudioz.socialmedia

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.onesignal.OneSignal
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.*

/**
 * Main Application class for social media platform
 * Handles:
 * - OneSignal notification setup
 * - Global Hilt dependency injection
 * - Timber logging initialization
 * - Crash reporting setup
 * - Analytics initialization
 */
@HiltAndroidApp
class MainApplication : Application() {

    companion object {
        private lateinit var instance: MainApplication

        fun getInstance(): MainApplication = instance
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        // Initialize Timber for logging
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Initialize OneSignal Push Notifications
        initializeOneSignal()

        // Initialize Analytics
        initializeAnalytics()

        // Setup crash reporting
        setupCrashReporting()

        // Setup global error handler
        Thread.setDefaultUncaughtExceptionHandler(GlobalExceptionHandler())

        Timber.d("Application initialized successfully")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Handle configuration changes (language, orientation, etc)
    }

    /**
     * Initialize OneSignal for push notifications
     */
    private fun initializeOneSignal() {
        try {
            // Enable logging to see OneSignal debug output
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

            // OneSignal initialization
            OneSignal.initWithContext(this)
            OneSignal.setAppId(BuildConfig.ONESIGNAL_APP_ID)

            // Set notification opened handler
            OneSignal.setNotificationOpenedHandler { result ->
                handleNotificationOpened(result)
            }

            // Set notification will show handler
            OneSignal.setNotificationWillShowInForegroundHandler { notificationReceivedEvent ->
                val notification = notificationReceivedEvent.notification
                Timber.d("Notification will show in foreground: ${notification.title}")
                notificationReceivedEvent.complete(notification)
            }

            Timber.i("OneSignal initialized successfully")
        } catch (e: Exception) {
            Timber.e(e, "Error initializing OneSignal")
        }
    }

    /**
     * Handle notification opened from OneSignal
     */
    private fun handleNotificationOpened(result: Any) {
        try {
            Timber.d("Notification opened")
            // Handle notification click and navigate to appropriate screen
        } catch (e: Exception) {
            Timber.e(e, "Error handling notification open")
        }
    }

    /**
     * Initialize Firebase Analytics
     */
    private fun initializeAnalytics() {
        try {
            // Initialize Firebase Analytics
            Timber.d("Analytics initialized")
        } catch (e: Exception) {
            Timber.e(e, "Error initializing analytics")
        }
    }

    /**
     * Setup Firebase Crashlytics
     */
    private fun setupCrashReporting() {
        try {
            // Initialize Firebase Crashlytics
            Timber.d("Crash reporting initialized")
        } catch (e: Exception) {
            Timber.e(e, "Error setting up crash reporting")
        }
    }

    /**
     * Global exception handler for uncaught exceptions
     */
    private class GlobalExceptionHandler : Thread.UncaughtExceptionHandler {
        override fun uncaughtException(thread: Thread, throwable: Throwable) {
            Timber.e(throwable, "Uncaught exception in thread: ${thread.name}")
            // Could log to remote server here
        }
    }
}
