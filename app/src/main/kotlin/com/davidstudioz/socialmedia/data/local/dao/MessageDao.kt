package com.davidstudioz.socialmedia.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.davidstudioz.socialmedia.data.local.entities.MessageEntity

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: MessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(messages: List<MessageEntity>)

    @Update
    suspend fun update(message: MessageEntity)

    @Delete
    suspend fun delete(message: MessageEntity)

    @Query("DELETE FROM messages WHERE chatId = :chatId")
    suspend fun deleteMessagesForChat(chatId: String)

    @Query("SELECT * FROM messages WHERE messageId = :messageId")
    fun getMessageById(messageId: String): LiveData<MessageEntity?>

    @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp DESC LIMIT :limit")
    fun getMessagesByChatId(chatId: String, limit: Int = 100): LiveData<List<MessageEntity>>

    @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp DESC LIMIT :limit OFFSET :offset")
    fun getMessagesWithPagination(
        chatId: String,
        limit: Int = 50,
        offset: Int = 0
    ): LiveData<List<MessageEntity>>

    @Query("SELECT * FROM messages WHERE chatId = :chatId AND isRead = 0 ORDER BY timestamp DESC")
    fun getUnreadMessages(chatId: String): LiveData<List<MessageEntity>>

    @Query("SELECT COUNT(*) FROM messages WHERE chatId = :chatId AND isRead = 0")
    fun getUnreadCount(chatId: String): LiveData<Int>

    @Query("UPDATE messages SET isRead = 1 WHERE chatId = :chatId AND isRead = 0")
    suspend fun markAllAsRead(chatId: String)

    @Query("UPDATE messages SET isRead = 1 WHERE messageId = :messageId")
    suspend fun markAsRead(messageId: String)

    @Query("SELECT * FROM messages WHERE replyTo = :messageId")
    fun getReplies(messageId: String): LiveData<List<MessageEntity>>

    @Query("SELECT * FROM messages WHERE chatId = :chatId AND timestamp >= :startTime ORDER BY timestamp DESC")
    fun getMessagesAfter(
        chatId: String,
        startTime: Long
    ): LiveData<List<MessageEntity>>
}
