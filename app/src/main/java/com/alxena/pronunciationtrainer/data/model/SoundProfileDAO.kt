package com.alxena.pronunciationtrainer.data.model

import androidx.room.*

@Dao
interface SoundProfileDAO {
    @Query("SELECT DISTINCT categoryId from sounds")
    abstract fun getCategories():List<Int>
    @Query("SELECT * from sounds WHERE categoryId = :categoryId")
    abstract fun getSoundsByCategory(categoryId: Int):List<SoundProfileEntity>
    @Insert
    abstract fun insert(result: SoundProfileEntity)
    @Update
    abstract fun update(result: SoundProfileEntity)
    @Delete
    abstract fun delete(result: SoundProfileEntity)
}