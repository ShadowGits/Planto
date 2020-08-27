package com.example.edp_plantsystem.Adapter

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.example.edp_plantsystem.R
import com.google.firebase.udacity.friendlychat.FriendlyMessage

class MessageAdapter(context: Context, resource: Int, objects: List<FriendlyMessage>) : ArrayAdapter<FriendlyMessage>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = (context as AppCompatActivity).layoutInflater.inflate(R.layout.item_message, parent, false)
        }

        val photoImageView = convertView!!.findViewById<View>(R.id.photoImageView) as ImageView
        val messageTextView = convertView.findViewById<View>(R.id.messageTextView) as TextView
        val authorTextView = convertView.findViewById<View>(R.id.nameTextView) as TextView

        val message = getItem(position)

        val isPhoto = message!!.photoUrl != null
        if (isPhoto) {
            messageTextView.visibility = View.GONE
            photoImageView.visibility = View.VISIBLE
            Glide.with(photoImageView.context)
                    .load(message.photoUrl)
                    .into(photoImageView)
        } else {
            messageTextView.visibility = View.VISIBLE
            photoImageView.visibility = View.GONE
            messageTextView.text = message.text
        }
        authorTextView.text = message.name

        return convertView
    }
}
