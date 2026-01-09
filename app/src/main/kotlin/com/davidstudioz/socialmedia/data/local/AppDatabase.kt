package com.davidstudioz.socialmedia.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.davidstudioz.socialmedia.data.local.converters.DataConverters
import com.davidstudioz.socialmedia.data.local.dao.*
import com.davidstudioz.socialmedia.data.local.entities.*

/**
 * Room Database configuration
 * Manages local SQLite database with all entities and DAOs
 * Handles:
 * - Message caching and offline support
 * - User profile caching
 * - Conversation history
 * - Media metadata
 * - User preferences and settings
 */
@Database(
    entities = [
        MessageEntity::class,
        ConversationEntity::class,
        UserEntity::class,
        MediaEntity::class,
        NotificationEntity::class,
        SettingsEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDao
    abstract fun conversationDao(): ConversationDao
    abstract fun userDao(): UserDao
    abstract fun mediaDao(): MediaDao
    abstract fun notificationDao(): NotificationDao
    abstract fun settingsDao(): SettingsDao

    companion object {
        private const val DB_NAME = "socialmedia_db"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}
