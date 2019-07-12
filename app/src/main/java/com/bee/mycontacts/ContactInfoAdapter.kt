package com.bee.mycontacts

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception


class ContactInfoAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<ContactInfoAdapter.ContactViewHolder>() {

    //private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val contactinflater: LayoutInflater = LayoutInflater.from(context)
    private var contacts = emptyList<Contact>() // Cached copy of words
    private var contactFound = Contact("name", "phone","a", "e", "f", "t")
    private val context: Context = context


    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


          val contactNameView: TextView = itemView.findViewById(R.id.nameView)
          val contactPhoneView: TextView = itemView.findViewById(R.id.phoneView)
          val contactAddressView: TextView = itemView.findViewById(R.id.addressView)
          val contactEmailView: TextView = itemView.findViewById(R.id.emailView)
          val contactFacebookView: TextView = itemView.findViewById(R.id.facebookView)
          val contactTwitterView: TextView = itemView.findViewById(R.id.twitterView)
          val contactInstagramView: TextView = itemView.findViewById(R.id.instagramview)

        init {
            contactPhoneView.setOnClickListener {
                val Uri = Uri.parse("tel:" + contactFound.phone)
                val callIntent = Intent(Intent.ACTION_DIAL, Uri)
                startActivity(context, callIntent, null)
            }
            contactEmailView.setOnClickListener {
            val phone = Uri.parse("mailto:" + contactFound.email)
            val emailIntent = Intent(Intent.ACTION_SENDTO, phone)
            startActivity(context, emailIntent, null)
            }
            contactTwitterView.setOnClickListener{
                val twitter = contactFound.twitter
                try{
                    startActivity(context,Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name="+twitter)), null)
                }catch(e: Exception){
                    startActivity(context,Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/"+twitter)), null)
                }
            }
            //TODO fix the facebook code doesnt load the profile page
            contactFacebookView.setOnClickListener{
                val facebook = contactFound.facebook
                try{
                    startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/"+ facebook)), null)
                }catch(e: Exception){
                    startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/"+ facebook)), null)
                }
            }
            
            //TODO add button click to instagramview
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = contactinflater.inflate(R.layout.recyclerview_contact_info, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.contactPhoneView.text = contactFound.phone
        holder.contactNameView.text = contactFound.name
        holder.contactAddressView.text =contactFound.address
        holder.contactEmailView.text = contactFound.email
        holder.contactFacebookView.text = contactFound.facebook
        holder.contactTwitterView.text = contactFound.twitter
        holder.contactInstagramView.text = contactFound.instagram

    }


    internal fun setNames(names: List<Contact>) {
        this.contacts = names
        notifyDataSetChanged()
    }

    //finds the contact matching the name passed to it
    internal fun setContactInfo(name: String){
        for(i in contacts ){
            if(i.name.equals(name)){
                var temp= listOf(i)
                contacts = temp
                contactFound = i
            }
        }
            }


    override fun getItemCount() = contacts.size
}
