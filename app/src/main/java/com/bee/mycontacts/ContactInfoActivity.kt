package com.bee.mycontacts

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactInfoActivity : AppCompatActivity() {


    private lateinit var contactViewModel: ContactInfoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.recyclerview_contact_info)
        setContentView(R.layout.activity_contactinfo)

        val temp=intent.getStringExtra(MainActivity.NAME_REPLY)
        //Toast.makeText(
        //   applicationContext,
        //    temp,
        //    Toast.LENGTH_LONG
       // ).show()
        //val toolbar = findViewById<Toolbar>(R.id.contacttoolbar)
       // setSupportActionBar(toolbar)

        val recycleView = findViewById<RecyclerView>(R.id.contactrecyclerview)
        val adapter = ContactInfoAdapter(this)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.
        contactViewModel = ViewModelProviders.of(this).get(ContactInfoModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        contactViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
           words?.let {
               adapter.setNames(it)
               adapter.setContactInfo(temp) }
        })
    }

    companion object {
        const val NAME_REPLY = "com.bee.mycontact.REPLY"
    }


}
