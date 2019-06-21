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

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ContactListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val contact_inflater: LayoutInflater = LayoutInflater.from(context)
    private var contacts = emptyList<Contact>() // Cached copy of words
    var onItemClick: ((Contact) -> Unit )? = null


    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactItemView: Button = itemView.findViewById(R.id.buttonView)
        //added for button clicks
        init{
            contactItemView.setOnClickListener {onItemClick?.invoke(contacts[position])  }
        }
      //  val contactNameView: TextView = itemView.findViewById(R.id.nameView)
      //  val contactPhoneView: TextView = itemView.findViewById(R.id.phoneView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val current = contacts[position]
        holder.contactItemView.text = current.name


       // holder.contactNameView.text = current.name
      //  holder.contactPhoneView.text = current.phone
    }



    internal fun setNames(names: List<Contact>) {
        this.contacts = names
        notifyDataSetChanged()
    }


    override fun getItemCount() = contacts.size
}

