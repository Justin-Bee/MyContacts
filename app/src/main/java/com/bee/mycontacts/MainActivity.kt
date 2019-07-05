package com.bee.mycontacts


import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ContactListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        //added for button clicks
        adapter.onItemClick = { contacts ->
            //Toast.makeText(this@MainActivity, "Test" , Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ContactInfoActivity::class.java)

          //  intent.putExtra(NAME_REPLY, contacts.name )
            var name = contacts.name
            var phone = contacts.phone
            var address= contacts.address
            var email = contacts.email
            var facebook = contacts.facebook
            var twitter = contacts.twitter
            val temp= arrayOf (name, phone, address, email, facebook, twitter)
            intent.putExtra(NAME_REPLY, temp)
            startActivity(intent)

        }

        // Get a new or existing ViewModel from the ViewModelProvider.
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        contactViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setNames(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, NewContactActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val temp=(data.getStringArrayExtra(NewContactActivity.EXTRA_REPLY))
                val contact = Contact(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5])
                contactViewModel.insert(contact)

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
        const val NAME_REPLY = "com.bee.mycontact.REPLY"

    }
}
