package com.alxena.pronunciationtrainer.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(SoundProfileEntity::class), version = 1)
abstract class SoundDatabase:RoomDatabase() {
    abstract fun SoundProfileDAO(): SoundProfileDAO
    companion object {
        private var INSTANCE: SoundDatabase? = null
        fun getDatabase(context: Context):SoundDatabase{

            if(INSTANCE == null)
            {
                INSTANCE = Room.databaseBuilder(
                    context,
                    SoundDatabase::class.java,
                    "sounds.db").build()
            }
            return INSTANCE!!//переработать
        }
    }
}