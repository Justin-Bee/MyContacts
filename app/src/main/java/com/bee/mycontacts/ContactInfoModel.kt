package com.bee.mycontacts


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*


/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

class ContactInfoModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContactRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<Contact>>

    init {
        val contactDao = ContactRoomDatabase.getDatabase(application, viewModelScope).contactDao()
        repository = ContactRepository(contactDao)
        allWords = repository.allWords
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun update(name:String,phone:String, address:String, email:String, facebook:String, twitter:String) = viewModelScope.launch {
        repository.update(name, phone, address, email, facebook, twitter)
    }

    fun updateContact(contact:Contact)= viewModelScope.launch {
        repository.updateContact(contact)
    }
}

