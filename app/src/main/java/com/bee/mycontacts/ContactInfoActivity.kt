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
import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactInfoActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private lateinit var contactViewModel: ContactInfoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactinfo)
        val temp=intent.getStringArrayExtra(MainActivity.NAME_REPLY)
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
               adapter.setContactInfo(temp[0]) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fabUpdate)
        fab.setOnClickListener {
            //todo pass the info as it already is, so user can edit it
            //above we use temp to set contact info maybe use that here
            val intent = Intent(this, NewContactActivity::class.java)
            //var temp2 = intent.getStringArrayExtra(MainActivity.CONTACT_REPLY)

            intent.putExtra(CONTACT_INFO,temp)
            startActivityForResult(intent, newWordActivityRequestCode)

            //Toast.makeText(this, "to be implemented", Toast.LENGTH_LONG)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val temp=(data.getStringArrayExtra(NewContactActivity.EXTRA_REPLY))
                val contact = Contact(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5])
                contactViewModel.updateContact(contact)
                //this.onRestart()
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        const val CONTACT_REPLY = "com.bee.mycontact.CONTACT_REPLY"
        const val CONTACT_INFO = "com.bee.mycontacts.CONTACT_INFO"
    }


}
