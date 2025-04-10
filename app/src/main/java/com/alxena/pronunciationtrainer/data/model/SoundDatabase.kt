package com.alxena.pronunciationtrainer.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(SoundProfileEntity::class, ProfileSettingsEntity::class), version = 1)
abstract class SoundDatabase:RoomDatabase() {
    abstract fun SoundProfileDAO(): SoundProfileDAO
    abstract fun ProfileSettingDAO(): ProfileSettingDAO
    companion object {
        private var INSTANCE: SoundDatabase? = null
        fun getDatabase(context: Context):SoundDatabase{

            if(INSTANCE == null)
            {
                INSTANCE = Room.databaseBuilder(
                    context,
                    SoundDatabase::class.java,
                    "data.db").build()
            }
            return INSTANCE!!//переработать
        }
    }
}