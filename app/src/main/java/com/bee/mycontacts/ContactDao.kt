package com.bee.mycontacts



import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * The Room Magic is in this file, where you map a Java method call to an SQL query.
 *
 * When you are using complex data types, such as Date, you have to also supply type converters.
 * To keep this example basic, no types that require type converters are used.
 * See the documentation at
 * https://developer.android.com/topic/libraries/architecture/room.html#type-converters
 */

@Dao
interface ContactDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from contact_table ORDER BY name ASC")
    fun getAlphabetizedWords(): LiveData<List<Contact>>

    @Query("SELECT * from contact_table where name like :name")
    fun getContactInfo(name: String): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: Contact)

    @Query("DELETE FROM contact_table")
    suspend fun deleteAll()

    @Query("UPDATE contact_table set phone=:uphone, address=:uaddress, email=:uemail, facebook=:ufacebook, twitter=:utwitter where name =:uname")
    fun updateInfo(uname: String, uphone: String, uaddress: String, uemail:String, ufacebook:String, utwitter:String)

    //@Update
   // @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Update
    suspend fun updateContact(contact: Contact)

}
