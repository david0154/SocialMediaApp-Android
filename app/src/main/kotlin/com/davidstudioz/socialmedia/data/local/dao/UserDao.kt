package com.davidstudioz.socialmedia.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.davidstudioz.socialmedia.data.local.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntity>)

    @Update
    suspend fun update(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

    @Query("SELECT * FROM users WHERE userId = :userId")
    fun getUserById(userId: String): LiveData<UserEntity?>

    @Query("SELECT * FROM users WHERE username LIKE '%' || :query || '%'")
    fun searchUsers(query: String): LiveData<List<UserEntity>>

    @Query("SELECT * FROM users ORDER BY lastSyncTime DESC LIMIT :limit")
    fun getRecentUsers(limit: Int = 50): LiveData<List<UserEntity>>
}
