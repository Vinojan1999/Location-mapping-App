package com.vinojanv.android.mappingApp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clients")
data class Client(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "lat") var lat: Float,
    @ColumnInfo(name = "lng") var lng: Float
)

