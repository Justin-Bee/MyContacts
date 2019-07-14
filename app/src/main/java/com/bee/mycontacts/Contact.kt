package com.bee.mycontacts



import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A basic class representing an entity that is a row in a one-column database table.
 *
 * @ Entity - You must annotate the class as an entity and supply a table name if not class name.
 * @ PrimaryKey - You must identify the primary key.
 * @ ColumnInfo - You must supply the column name if it is different from the variable name.
 *
 * See the documentation for the full rich set of annotations.
 * https://developer.android.com/topic/libraries/architecture/room.html
 */

@Entity(tableName = "contact_table")
data class Contact(@PrimaryKey @ColumnInfo var name: String,
                   @ColumnInfo var phone: String,
                   @ColumnInfo var address: String,
                   @ColumnInfo var email: String,
                   @ColumnInfo var facebook: String,
                   @ColumnInfo var twitter: String,
                   @ColumnInfo var instagram: String
//todo add linkedin
//todo add pictures support
                   )
//Todo make sure to update the update query in ContactDao when adding or removing columns
{
    constructor(): this("name", "phone", "address", "email", "facebook", "twitter", "instagram")
}



