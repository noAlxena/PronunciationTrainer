package com.alxena.pronunciationtrainer.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sounds")
data class SoundProfileEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "soundNameId")
    var soundId: Int,
    @ColumnInfo(name = "categoryId")
    var categoryId: Int,
    @ColumnInfo(name = "completion")
    var completion: Boolean
)