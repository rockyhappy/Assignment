package com.devrachit.swipeassignment.presentation.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devrachit.swipeassignment.R
import com.devrachit.swipeassignment.databinding.LayoutImageItemBinding

class ImageListAdapter(private val imageList: List<Uri>, private val OnImageClickListener: OnImageClickListener) :
    RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>(){

    inner class ImageViewHolder(val binding: LayoutImageItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            LayoutImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUri = imageList[position]
        holder.binding.removeImageBtn.setOnClickListener {
            OnImageClickListener.onRemoveImageClick(imageUri)
        }
        holder.binding.fileImage.setOnClickListener {
            OnImageClickListener.onImageClick(imageUri)
        }
        Glide.with(holder.itemView)
            .load(imageUri)
            .override(250, 250)
            .centerCrop()
            .into(holder.binding.fileImage)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }


}

interface OnImageClickListener {
    fun onImageClick(uri: Uri)
    fun onRemoveImageClick(uri: Uri)
}