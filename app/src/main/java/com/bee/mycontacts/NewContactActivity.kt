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
import android.os.Bundle
import android.text.Editable

import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
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
   // private val contactInfo = intent.getStringArrayExtra(ContactInfoActivity.CONTACT_REPLY)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        editWordView = findViewById(R.id.edit_word)
        editPhoneView = findViewById(R.id.edit_phone)
        editAddrView = findViewById(R.id.edit_address)
        editEmailView = findViewById(R.id.edit_email)
        editFacebookView = findViewById(R.id.edit_facebook)
        editTwitterView = findViewById(R.id.edit_twitter)

       // if(contactInfo != null){
      //      editWordView.setText(contactInfo[0])
       //     editPhoneView.setText(contactInfo[1])
       //     editAddrView.setText(contactInfo[2])
       //     editEmailView.setText(contactInfo[3])
       //     editFacebookView.setText(contactInfo[4])
       //     editTwitterView.setText(contactInfo[5])
      //  }

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val contact= arrayOf(editWordView.text.toString(), editPhoneView.text.toString(),
                    editAddrView.text.toString(), editEmailView.text.toString(),
                    editFacebookView.text.toString(), editTwitterView.text.toString())
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

