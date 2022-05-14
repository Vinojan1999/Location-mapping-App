package com.vinojanv.android.mappingApp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vinojanv.android.mappingApp.model.Client

@Dao
interface ClientDao {

    @Query("SELECT * FROM clients")
    fun getAll(): List<Client>

    @Insert
    fun add(client: Client)

    @Delete
    fun remove(client: Client)

}