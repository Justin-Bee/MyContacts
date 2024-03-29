package com.bee.mycontacts


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable

import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity for entering a word.
 */


class NewContactActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText
    private lateinit var editPhoneView: EditText
    private lateinit var editAddrView: EditText
    private lateinit var editEmailView: EditText
    private lateinit var editFacebookView: EditText
    private lateinit var editTwitterView: EditText
    private lateinit var editInstagramView: EditText
    private lateinit var editLinkedinView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        editWordView = findViewById(R.id.edit_word)
        editPhoneView = findViewById(R.id.edit_phone)
        editAddrView = findViewById(R.id.edit_address)
        editEmailView = findViewById(R.id.edit_email)
        editFacebookView = findViewById(R.id.edit_facebook)
        editTwitterView = findViewById(R.id.edit_twitter)
        editInstagramView = findViewById(R.id.edit_instagram)
        editLinkedinView = findViewById(R.id.edit_linkedin)
        //todo all of this needs fixed
      //  var contactInfo = intent.getStringArrayExtra(ContactInfoActivity.CONTACT_INFO)

     //   var tester = intent.getStringArrayExtra(MainActivity.NAME_REPLY)

     //  if(!tester[0].equals("null")){
      //      editWordView.setText(contactInfo[0])
      //      editPhoneView.setText(contactInfo[1])
      ///      editAddrView.setText(contactInfo[2])
      //      editEmailView.setText(contactInfo[3])
      ///      editFacebookView.setText(contactInfo[4])
      //      editTwitterView.setText(contactInfo[5])
     //   }

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val contact= arrayOf(editWordView.text.toString(), editPhoneView.text.toString(),
                    editAddrView.text.toString(), editEmailView.text.toString(),
                    editFacebookView.text.toString(), editTwitterView.text.toString(), editInstagramView.text.toString(),
                    editLinkedinView.text.toString())
               // val contact = editWordView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, contact)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.bee.mycontact.REPLY"

    }
}

