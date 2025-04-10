package com.alxena.pronunciationtrainer.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class ProfileSettingsEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "token")
    var token: String,
    @ColumnInfo(name = "login")
    var login: String,
    @ColumnInfo(name = "teacher_token")
    var teacherToken: String?
)