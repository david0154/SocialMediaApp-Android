package com.davidstudioz.socialmedia.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// ==================== BASE RESPONSE MODELS ====================

data class BaseResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: Any? = null
)

data class AuthResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: AuthData
)

data class AuthData(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("profile_picture")
    val profilePicture: String?,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("token_expiry")
    val tokenExpiry: Long
)

data class TokenResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("token_expiry")
    val tokenExpiry: Long
)

// ==================== USER MODELS ====================

@Parcelize
data class User(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("profile_picture")
    val profilePicture: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("website")
    val website: String? = null,
    @SerializedName("followers_count")
    val followersCount: Int = 0,
    @SerializedName("following_count")
    val followingCount: Int = 0,
    @SerializedName("posts_count")
    val postsCount: Int = 0,
    @SerializedName("is_verified")
    val isVerified: Boolean = false,
    @SerializedName("is_blocked")
    val isBlocked: Boolean = false,
    @SerializedName("is_following")
    val isFollowing: Boolean = false,
    @SerializedName("last_active")
    val lastActive: Long = 0
) : Parcelable

data class UserProfileResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: UserProfileData
)

data class UserProfileData(
    @SerializedName("user")
    val user: User,
    @SerializedName("stats")
    val stats: UserStats?
)

data class UserStats(
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int,
    @SerializedName("posts")
    val posts: Int,
    @SerializedName("likes")
    val likes: Int
)

data class SearchUserResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: List<User>,
    @SerializedName("pagination")
    val pagination: PaginationInfo
)

data class UserListResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: List<User>,
    @SerializedName("pagination")
    val pagination: PaginationInfo
)

data class PaginationInfo(
    @SerializedName("page")
    val page: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("pages")
    val pages: Int
)

// ==================== MESSAGE MODELS ====================

@Parcelize
data class Message(
    @SerializedName("message_id")
    val messageId: String,
    @SerializedName("sender_id")
    val senderId: String,
    @SerializedName("chat_id")
    val chatId: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("media")
    val media: List<Media> = emptyList(),
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("is_read")
    val isRead: Boolean = false,
    @SerializedName("reply_to")
    val replyTo: String? = null,
    @SerializedName("reactions")
    val reactions: Map<String, Int> = emptyMap(),
    @SerializedName("edited_at")
    val editedAt: Long? = null,
    @SerializedName("is_deleted")
    val isDeleted: Boolean = false
) : Parcelable

data class MessageResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: Message
)

data class MessageListResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: List<Message>,
    @SerializedName("pagination")
    val pagination: PaginationInfo
)

data class SendMessageRequest(
    @SerializedName("chat_id")
    val chatId: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("media_ids")
    val mediaIds: List<String> = emptyList(),
    @SerializedName("reply_to")
    val replyTo: String? = null
)

data class UpdateMessageRequest(
    @SerializedName("content")
    val content: String
)

data class ReactionRequest(
    @SerializedName("emoji")
    val emoji: String
)

// ==================== CHAT/CONVERSATION MODELS ====================

@Parcelize
data class Conversation(
    @SerializedName("chat_id")
    val chatId: String,
    @SerializedName("chat_type")
    val chatType: String, // personal, group, page, broadcast
    @SerializedName("participants")
    val participants: List<User>,
    @SerializedName("name")
    val name: String?,
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("last_message")
    val lastMessage: Message?,
    @SerializedName("unread_count")
    val unreadCount: Int = 0,
    @SerializedName("last_activity")
    val lastActivity: Long = 0
) : Parcelable

data class ConversationListResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: List<Conversation>,
    @SerializedName("pagination")
    val pagination: PaginationInfo
)

data class CreateGroupRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("member_ids")
    val memberIds: List<String>
)

data class GroupResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: GroupData
)

data class GroupData(
    @SerializedName("chat_id")
    val chatId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("members")
    val members: List<User>,
    @SerializedName("admin_id")
    val adminId: String,
    @SerializedName("created_at")
    val createdAt: Long
)

data class AddMemberRequest(
    @SerializedName("member_id")
    val memberId: String
)

// ==================== MEDIA MODELS ====================

@Parcelize
data class Media(
    @SerializedName("media_id")
    val mediaId: String,
    @SerializedName("type")
    val type: String, // image, video, document, audio
    @SerializedName("url")
    val url: String,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("size")
    val size: Long,
    @SerializedName("duration")
    val duration: Long? = null,
    @SerializedName("width")
    val width: Int? = null,
    @SerializedName("height")
    val height: Int? = null
) : Parcelable

data class MediaResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: Media
)

// ==================== POST MODELS ====================

@Parcelize
data class Post(
    @SerializedName("post_id")
    val postId: String,
    @SerializedName("author")
    val author: User,
    @SerializedName("content")
    val content: String,
    @SerializedName("media")
    val media: List<Media> = emptyList(),
    @SerializedName("likes_count")
    val likesCount: Int = 0,
    @SerializedName("comments_count")
    val commentsCount: Int = 0,
    @SerializedName("shares_count")
    val sharesCount: Int = 0,
    @SerializedName("is_liked")
    val isLiked: Boolean = false,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("updated_at")
    val updatedAt: Long?
) : Parcelable

data class PostResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: Post
)

data class PostListResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: List<Post>,
    @SerializedName("pagination")
    val pagination: PaginationInfo
)

data class CreatePostRequest(
    @SerializedName("content")
    val content: String,
    @SerializedName("media_ids")
    val mediaIds: List<String> = emptyList(),
    @SerializedName("visibility")
    val visibility: String = "public" // public, friends, private
)

data class CommentRequest(
    @SerializedName("content")
    val content: String
)

data class CommentResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: Comment
)

data class Comment(
    @SerializedName("comment_id")
    val commentId: String,
    @SerializedName("author")
    val author: User,
    @SerializedName("content")
    val content: String,
    @SerializedName("likes_count")
    val likesCount: Int,
    @SerializedName("created_at")
    val createdAt: Long
)

// ==================== NOTIFICATION MODELS ====================

data class Notification(
    @SerializedName("notification_id")
    val notificationId: String,
    @SerializedName("type")
    val type: String, // message, like, comment, follow, etc
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("data")
    val data: Map<String, String>,
    @SerializedName("is_read")
    val isRead: Boolean = false,
    @SerializedName("created_at")
    val createdAt: Long
)

data class NotificationListResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: List<Notification>,
    @SerializedName("pagination")
    val pagination: PaginationInfo
)

// ==================== SETTINGS & MISC MODELS ====================

data class AppSettingsResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: AppSettingsData
)

data class AppSettingsData(
    @SerializedName("version")
    val version: String,
    @SerializedName("min_version")
    val minVersion: String,
    @SerializedName("features")
    val features: Map<String, Boolean>,
    @SerializedName("maintenance")
    val maintenance: Boolean = false
)

data class PrivacySettingsRequest(
    @SerializedName("allow_messages_from_strangers")
    val allowMessagesFromStrangers: Boolean,
    @SerializedName("allow_location_sharing")
    val allowLocationSharing: Boolean,
    @SerializedName("show_online_status")
    val showOnlineStatus: Boolean,
    @SerializedName("profile_visibility")
    val profileVisibility: String // public, friends, private
)

data class BlockUserRequest(
    @SerializedName("user_id")
    val userId: String
)
