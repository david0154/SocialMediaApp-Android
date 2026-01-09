package com.davidstudioz.socialmedia.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.davidstudioz.socialmedia.data.local.entities.MediaEntity

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(media: MediaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(medias: List<MediaEntity>)

    @Update
    suspend fun update(media: MediaEntity)

    @Delete
    suspend fun delete(media: MediaEntity)

    @Query("SELECT * FROM media WHERE mediaId = :mediaId")
    fun getMediaById(mediaId: String): LiveData<MediaEntity?>

    @Query("SELECT * FROM media WHERE uploadStatus = 'not_uploaded' OR uploadStatus = 'failed'")
    fun getPendingUploads(): LiveData<List<MediaEntity>>

    @Query("SELECT * FROM media WHERE type = :type ORDER BY size DESC")
    fun getMediaByType(type: String): LiveData<List<MediaEntity>>
}
