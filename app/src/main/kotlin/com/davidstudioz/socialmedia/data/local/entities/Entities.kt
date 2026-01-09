package com.davidstudioz.socialmedia.data.local.entities

import androidx.room.*

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey
    val messageId: String,
    val senderId: String,
    val chatId: String,
    val content: String,
    val mediaUrls: List<String> = emptyList(),
    val timestamp: Long,
    val isRead: Boolean = false,
    val replyTo: String? = null,
    val reactions: Map<String, Int> = emptyMap(),
    val editedAt: Long? = null,
    val isDeleted: Boolean = false,
    val syncStatus: String = "synced" // synced, pending, failed
)

@Entity(tableName = "conversations")
data class ConversationEntity(
    @PrimaryKey
    val chatId: String,
    val chatType: String,
    val participantIds: List<String>,
    val name: String? = null,
    val avatar: String? = null,
    val lastMessageId: String? = null,
    val unreadCount: Int = 0,
    val lastActivity: Long = 0,
    val isMuted: Boolean = false,
    val isPinned: Boolean = false
)

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val userId: String,
    val username: String,
    val email: String,
    val profilePicture: String? = null,
    val bio: String? = null,
    val location: String? = null,
    val website: String? = null,
    val followersCount: Int = 0,
    val followingCount: Int = 0,
    val isVerified: Boolean = false,
    val isBlocked: Boolean = false,
    val isFollowing: Boolean = false,
    val lastActive: Long = 0,
    val lastSyncTime: Long = System.currentTimeMillis()
)

@Entity(tableName = "media")
data class MediaEntity(
    @PrimaryKey
    val mediaId: String,
    val type: String,
    val url: String,
    val thumbnail: String? = null,
    val size: Long,
    val duration: Long? = null,
    val width: Int? = null,
    val height: Int? = null,
    val localPath: String? = null,
    val uploadStatus: String = "not_uploaded" // not_uploaded, uploading, uploaded, failed
)

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey
    val notificationId: String,
    val type: String,
    val title: String,
    val body: String,
    val data: Map<String, String>,
    val isRead: Boolean = false,
    val createdAt: Long
)

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey
    val key: String,
    val value: String,
    val type: String = "string" // string, boolean, int, long
)
