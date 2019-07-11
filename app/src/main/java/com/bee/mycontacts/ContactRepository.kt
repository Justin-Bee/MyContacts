package com.bee.mycontacts


import androidx.lifecycle.LiveData

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
class ContactRepository(private val contactDao: ContactDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Contact>> = contactDao.getAlphabetizedWords()

    // The suspend modifier tells the compiler that this must be called from a
    // coroutine or another suspend function.
    // This ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    suspend fun insert(contact: Contact) {
        contactDao.insert(contact)
    }

    fun update(name:String, phone:String, address:String, email:String, facebook:String, twitter:String, instagram:String){
        contactDao.updateInfo(name,phone, address, email, facebook, twitter, instagram)
    }

    suspend fun updateContact(contact:Contact){
        contactDao.updateContact(contact)
    }
}
