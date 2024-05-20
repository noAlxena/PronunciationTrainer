package com.alxena.pronunciationtrainer.data.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(SoundProfileEntity::class), version = 1)
abstract class SoundDatabase:RoomDatabase() {
    abstract fun SoundProfileDAO(): SoundProfileDAO
}