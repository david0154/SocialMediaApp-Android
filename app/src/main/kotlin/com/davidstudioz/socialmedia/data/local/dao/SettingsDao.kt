package com.davidstudioz.socialmedia.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.davidstudioz.socialmedia.data.local.entities.SettingsEntity

@Dao
interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(setting: SettingsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(settings: List<SettingsEntity>)

    @Update
    suspend fun update(setting: SettingsEntity)

    @Delete
    suspend fun delete(setting: SettingsEntity)

    @Query("SELECT * FROM settings WHERE key = :key")
    fun getSetting(key: String): LiveData<SettingsEntity?>

    @Query("SELECT * FROM settings")
    fun getAllSettings(): LiveData<List<SettingsEntity>>

    @Query("DELETE FROM settings WHERE key = :key")
    suspend fun deleteSetting(key: String)
}
