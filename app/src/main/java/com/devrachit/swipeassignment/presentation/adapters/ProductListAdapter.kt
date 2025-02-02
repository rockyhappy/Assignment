package com.devrachit.swipeassignment.presentation.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devrachit.swipeassignment.R
import com.devrachit.swipeassignment.data.models.ProductItem
import com.devrachit.swipeassignment.databinding.LayoutProductListItemBinding


class ProductListAdapter(private val productList: List<ProductItem>, private val listener: OnClickListener) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = LayoutProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    inner class ProductViewHolder(private val binding: LayoutProductListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener.onClick(adapterPosition)
            }
        }

        fun bind(product: ProductItem) {
            binding.itemName.text = product.productName
            binding.itemPrice.text = "₹"+product.price.toString()
            binding.itemType.text = "Type: "+product.productType
            binding.itemTax.text = "Tax: "+product.tax.toString()
            Glide.with(binding.itemImage.context).load(product.image).placeholder(R.drawable.picture).into(binding.itemImage)

        }
    }
}

//private class AdapterDiffUtil : DiffUtil.ItemCallback<ProductItem>() {
//    override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
//        return oldItem == newItem
//    }
//}

interface OnClickListener {
    fun onClick(index: Int)

}



