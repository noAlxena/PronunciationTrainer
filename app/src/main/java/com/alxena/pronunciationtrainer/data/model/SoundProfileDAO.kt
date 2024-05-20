package com.alxena.pronunciationtrainer.data.model

import androidx.room.*

@Dao
interface SoundProfileDAO {
    @Query("SELECT DISTINCT category from sounds")
    abstract fun getCategories():List<String>
    @Query("SELECT * from sounds WHERE category = :category")
    abstract fun getSoundsByCategory(category: String):List<SoundProfileEntity>
    @Insert
    abstract fun insert(result: SoundProfileEntity)
    @Delete
    abstract fun delete(result: SoundProfileEntity)
}