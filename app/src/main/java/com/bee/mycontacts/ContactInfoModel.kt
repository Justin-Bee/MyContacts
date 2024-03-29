package com.bee.mycontacts


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

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
    fun update(name:String,phone:String, address:String, email:String, facebook:String, twitter:String, instagram:String, linkedin:String) = viewModelScope.launch {
        repository.update(name, phone, address, email, facebook, twitter, instagram, linkedin)
    }

    fun updateContact(contact:Contact)= viewModelScope.launch {
        repository.updateContact(contact)
    }
}

