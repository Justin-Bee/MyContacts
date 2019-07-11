package com.bee.mycontacts

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
    private var contacts = emptyList<Contact>() // Cached copy of words
    var onItemClick: ((Contact) -> Unit )? = null


    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactItemView: TextView = itemView.findViewById(R.id.buttonView)
        //added for button clicks
        init {
            contactItemView.setOnClickListener { onItemClick?.invoke(contacts[position]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val current = contacts[position]
        holder.contactItemView.text = current.name
    }

    internal fun setNames(names: List<Contact>) {
        this.contacts = names
        notifyDataSetChanged()
    }


    override fun getItemCount() = contacts.size
}


