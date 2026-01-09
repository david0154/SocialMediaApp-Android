package com.davidstudioz.socialmedia.data.api

import com.davidstudioz.socialmedia.data.models.*
import retrofit2.Response
import retrofit2.http.*

/**
 * Retrofit API Service for all backend API calls
 * Handles:
 * - Authentication (Login, Register, Logout)
 * - User Management (Profile, Settings)
 * - Chat Operations (Message Send/Receive, Chat History)
 * - Media Upload (Images, Videos, Files)
 * - Social Operations (Posts, Comments, Likes)
 * - Location Services
 * - Real-time Updates
 */
interface ApiService {

    // ==================== AUTH ENDPOINTS ====================

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_id") deviceId: String,
        @Field("device_token") deviceToken: String
    ): Response<AuthResponse>

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_id") deviceId: String,
        @Field("device_token") deviceToken: String
    ): Response<AuthResponse>

    @POST("auth/logout")
    suspend fun logout(): Response<BaseResponse>

    @FormUrlEncoded
    @POST("auth/refresh-token")
    suspend fun refreshToken(
        @Field("refresh_token") refreshToken: String
    ): Response<TokenResponse>

    // ==================== USER ENDPOINTS ====================

    @GET("user/profile")
    suspend fun getUserProfile(): Response<UserProfileResponse>

    @GET("user/{userId}")
    suspend fun getUserDetails(
        @Path("userId") userId: String
    ): Response<UserProfileResponse>

    @Multipart
    @PUT("user/profile")
    suspend fun updateUserProfile(
        @Part("username") username: String? = null,
        @Part("bio") bio: String? = null,
        @Part("location") location: String? = null,
        @Part("website") website: String? = null
    ): Response<UserProfileResponse>

    @GET("user/search")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<SearchUserResponse>

    @GET("user/followers")
    suspend fun getFollowers(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<UserListResponse>

    @GET("user/following")
    suspend fun getFollowing(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<UserListResponse>

    // ==================== CHAT ENDPOINTS ====================

    @POST("chat/send-message")
    suspend fun sendMessage(
        @Body request: SendMessageRequest
    ): Response<MessageResponse>

    @GET("chat/messages/{chatId}")
    suspend fun getMessages(
        @Path("chatId") chatId: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50
    ): Response<MessageListResponse>

    @GET("chat/conversations")
    suspend fun getConversations(
        @Query("type") type: String = "personal", // personal, group, page, broadcast
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<ConversationListResponse>

    @POST("chat/group/create")
    suspend fun createGroup(
        @Body request: CreateGroupRequest
    ): Response<GroupResponse>

    @POST("chat/group/{groupId}/add-member")
    suspend fun addGroupMember(
        @Path("groupId") groupId: String,
        @Body request: AddMemberRequest
    ): Response<GroupResponse>

    @DELETE("chat/group/{groupId}/remove-member/{memberId}")
    suspend fun removeGroupMember(
        @Path("groupId") groupId: String,
        @Path("memberId") memberId: String
    ): Response<BaseResponse>

    @PUT("chat/message/{messageId}")
    suspend fun updateMessage(
        @Path("messageId") messageId: String,
        @Body request: UpdateMessageRequest
    ): Response<MessageResponse>

    @DELETE("chat/message/{messageId}")
    suspend fun deleteMessage(
        @Path("messageId") messageId: String
    ): Response<BaseResponse>

    @POST("chat/message/{messageId}/reaction")
    suspend fun addReaction(
        @Path("messageId") messageId: String,
        @Body request: ReactionRequest
    ): Response<MessageResponse>

    @GET("chat/pin/{chatId}")
    suspend fun getPinnedMessages(
        @Path("chatId") chatId: String
    ): Response<MessageListResponse>

    // ==================== MEDIA ENDPOINTS ====================

    @Multipart
    @POST("media/upload")
    suspend fun uploadMedia(
        @Part("type") type: String, // image, video, document, audio
        @Part file: okhttp3.MultipartBody.Part,
        @Part("metadata") metadata: String? = null
    ): Response<MediaResponse>

    @Multipart
    @POST("media/upload-profile")
    suspend fun uploadProfileImage(
        @Part file: okhttp3.MultipartBody.Part
    ): Response<MediaResponse>

    @DELETE("media/{mediaId}")
    suspend fun deleteMedia(
        @Path("mediaId") mediaId: String
    ): Response<BaseResponse>

    // ==================== POST ENDPOINTS ====================

    @POST("posts/create")
    suspend fun createPost(
        @Body request: CreatePostRequest
    ): Response<PostResponse>

    @GET("posts/feed")
    suspend fun getFeed(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<PostListResponse>

    @GET("posts/{postId}")
    suspend fun getPostDetails(
        @Path("postId") postId: String
    ): Response<PostResponse>

    @POST("posts/{postId}/like")
    suspend fun likePost(
        @Path("postId") postId: String
    ): Response<PostResponse>

    @DELETE("posts/{postId}/unlike")
    suspend fun unlikePost(
        @Path("postId") postId: String
    ): Response<PostResponse>

    @POST("posts/{postId}/comment")
    suspend fun addComment(
        @Path("postId") postId: String,
        @Body request: CommentRequest
    ): Response<CommentResponse>

    @DELETE("posts/{postId}")
    suspend fun deletePost(
        @Path("postId") postId: String
    ): Response<BaseResponse>

    // ==================== NOTIFICATION ENDPOINTS ====================

    @GET("notifications")
    suspend fun getNotifications(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50
    ): Response<NotificationListResponse>

    @PUT("notifications/{notificationId}/read")
    suspend fun markNotificationAsRead(
        @Path("notificationId") notificationId: String
    ): Response<BaseResponse>

    @PUT("notifications/read-all")
    suspend fun markAllNotificationsAsRead(): Response<BaseResponse>

    // ==================== LOCATION ENDPOINTS ====================

    @FormUrlEncoded
    @POST("location/update")
    suspend fun updateLocation(
        @Field("latitude") latitude: Double,
        @Field("longitude") longitude: Double,
        @Field("accuracy") accuracy: Float? = null
    ): Response<BaseResponse>

    @GET("location/nearby-users")
    suspend fun getNearbyUsers(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("radius") radiusMeters: Int = 5000
    ): Response<UserListResponse>

    // ==================== SETTINGS ENDPOINTS ====================

    @GET("settings/app")
    suspend fun getAppSettings(): Response<AppSettingsResponse>

    @PUT("settings/privacy")
    suspend fun updatePrivacySettings(
        @Body request: PrivacySettingsRequest
    ): Response<BaseResponse>

    @POST("settings/block-user")
    suspend fun blockUser(
        @Body request: BlockUserRequest
    ): Response<BaseResponse>

    @GET("settings/blocked-users")
    suspend fun getBlockedUsers(): Response<UserListResponse>
}
