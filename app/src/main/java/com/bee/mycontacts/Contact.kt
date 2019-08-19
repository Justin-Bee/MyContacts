package com.bee.mycontacts



import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** Copyright 2019 Justin Bee
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
**/

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
                   @ColumnInfo var address: String, //change address to include many fields (street, city, state, country)
                   @ColumnInfo var email: String,
                   @ColumnInfo var facebook: String,
                   @ColumnInfo var twitter: String,
                   @ColumnInfo var instagram: String,
                   @ColumnInfo var linkedin: String
//todo add linkedin
//todo add pictures support
                   )
//Todo make sure to update the update query in ContactDao when adding or removing columns
{
    constructor(): this("name", "phone", "address", "email", "facebook", "twitter", "instagram", "linkedin")
}



