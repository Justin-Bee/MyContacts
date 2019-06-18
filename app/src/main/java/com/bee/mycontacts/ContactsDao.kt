package com.bee.mycontacts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface ContactsDao{
    @Query("Select * from Contacts")
    fun getAll(): List<Contacts>

    @Insert(onConflict = REPLACE)
    fun insert (Contacts: Contacts)

    @Query("DELETE from Contacts")
    fun deleteAll()
}
