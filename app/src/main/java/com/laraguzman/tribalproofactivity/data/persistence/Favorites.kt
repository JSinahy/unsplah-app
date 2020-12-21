package com.laraguzman.tribalproofactivity.data.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
class Favorites (
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "data") val data: String
)