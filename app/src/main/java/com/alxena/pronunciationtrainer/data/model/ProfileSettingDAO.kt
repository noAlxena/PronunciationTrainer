package com.alxena.pronunciationtrainer.data.model

import androidx.room.*

@Dao
interface ProfileSettingDAO {
    @Query("SELECT * from settings")
    fun select():ProfileSettingsEntity?
    @Insert
    fun insert(result: ProfileSettingsEntity)
    @Delete
    fun delete(result: ProfileSettingsEntity)
}