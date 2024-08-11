package com.capstone.suitmediatestmsib

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.capstone.suitmediatestmsib.databinding.ItemRowBinding

class UserAdapter(private val listUser : List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    inner class ViewHolder(binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val image : ImageView = binding.imgItemPhoto
        val name : TextView = binding.tvFirstName
        val lastname : TextView = binding.tvLastName
        val email : TextView = binding.tvItemDescription
    }
    override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(viewgroup.context), viewgroup, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val (name, imageUrl, lastname, email) = listUser[position]
        holder.name.text = name
        Glide.with(holder.itemView)
            .load(imageUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(holder.image)
        holder.lastname.text = lastname
        holder.email.text = email

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, SecondActivity::class.java)
            intent.putExtra("EXTRA_USER_FIRSTNAME", name)
            intent.putExtra("EXTRA_USER_LASTNAME", lastname)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

}