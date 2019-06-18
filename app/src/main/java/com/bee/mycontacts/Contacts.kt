package com.bee.mycontacts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//todo implement rooms db for the contacts

@Entity(tableName = "Contacts")

data class Contacts (@PrimaryKey(autoGenerate = true) var id : Long?,
                     @ColumnInfo(name = "name") var name: String,
                     @ColumnInfo(name = "phoneNumber") var phoneNumber: Int,
                     @ColumnInfo(name = "street") var street: String,
                     @ColumnInfo(name = "city") var city: String,
                     @ColumnInfo(name = "state") var state: String,
                     @ColumnInfo(name = "zip") var zip: Int,
                     @ColumnInfo(name = "email") var email: String,
                     @ColumnInfo(name = "facebook") var facebook: String,
                     @ColumnInfo(name = "instagram") var instagram: String,
                     @ColumnInfo(name = "twitter") var twitter: String


)