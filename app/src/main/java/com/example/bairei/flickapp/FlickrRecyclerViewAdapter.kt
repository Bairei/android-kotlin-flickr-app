package com.example.bairei.flickapp

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class FlickImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    var title: TextView = view.findViewById(R.id.title)
}

class FlickrRecyclerViewAdapter(private var photoList: List<Photo>) : RecyclerView.Adapter<FlickImageViewHolder>() {
    private val TAG = "FlickrRVAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickImageViewHolder {
        // Called by the layout manager when it needs a new view
        Log.d(TAG, ".onCreateViewHolder new view requested")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return FlickImageViewHolder(view)

    }

    override fun getItemCount(): Int {
        // Log.d(TAG, ".getItemCount called")
        return if(photoList.isNotEmpty()) return photoList.size else 1
    }

    override fun onBindViewHolder(holder: FlickImageViewHolder, position: Int) {
        // called by the layout manager when it wants new data in an existing view
        if (photoList.isEmpty()){
            holder.thumbnail.setImageResource(R.drawable.placeholder)
            holder.title.setText(R.string.empty_photo)
        } else {
            val photoItem = photoList[position]
            // Log.d(TAG, ".onBindViewHolder: ${photoItem.title} --> $position")

            Picasso.with(holder.thumbnail.context)
                    .load(photoItem.image)
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.thumbnail)

            holder.title.text = photoItem.title
        }

    }

    fun loadNewData(newPhotos: List<Photo>) {
        photoList = newPhotos
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int): Photo? {
        return if (photoList.isNotEmpty()) photoList[position] else null
    }
}