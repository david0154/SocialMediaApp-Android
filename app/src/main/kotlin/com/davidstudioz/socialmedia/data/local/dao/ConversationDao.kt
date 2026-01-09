package com.davidstudioz.socialmedia.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.davidstudioz.socialmedia.data.local.entities.ConversationEntity

@Dao
interface ConversationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(conversation: ConversationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(conversations: List<ConversationEntity>)

    @Update
    suspend fun update(conversation: ConversationEntity)

    @Delete
    suspend fun delete(conversation: ConversationEntity)

    @Query("SELECT * FROM conversations WHERE chatId = :chatId")
    fun getConversationById(chatId: String): LiveData<ConversationEntity?>

    @Query("SELECT * FROM conversations ORDER BY lastActivity DESC")
    fun getAllConversations(): LiveData<List<ConversationEntity>>

    @Query("SELECT * FROM conversations WHERE chatType = :type ORDER BY lastActivity DESC")
    fun getConversationsByType(type: String): LiveData<List<ConversationEntity>>

    @Query("SELECT * FROM conversations WHERE isPinned = 1 ORDER BY lastActivity DESC")
    fun getPinnedConversations(): LiveData<List<ConversationEntity>>

    @Query("UPDATE conversations SET unreadCount = :count WHERE chatId = :chatId")
    suspend fun updateUnreadCount(chatId: String, count: Int)

    @Query("UPDATE conversations SET isMuted = :isMuted WHERE chatId = :chatId")
    suspend fun muteConversation(chatId: String, isMuted: Boolean = true)

    @Query("UPDATE conversations SET isPinned = :isPinned WHERE chatId = :chatId")
    suspend fun pinConversation(chatId: String, isPinned: Boolean = true)
}
