package com.alxena.pronunciationtrainer.data.model

import androidx.room.*

@Dao
interface SoundProfileDAO {
    @Query("SELECT DISTINCT categoryId from sounds")
    fun getCategories():List<Int>
    @Query("SELECT * from sounds WHERE categoryId = :categoryId")
    fun getSoundsByCategory(categoryId: Int):List<SoundProfileEntity>
    @Insert
    fun insert(result: SoundProfileEntity)
    @Query("UPDATE sounds SET completion=true WHERE soundNameId=:soundId")
    fun completeSound(soundId: Int)
    @Delete
    fun delete(result: SoundProfileEntity)
}