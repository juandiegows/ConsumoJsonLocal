package com.example.consumojsonlocal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.consumojsonlocal.databinding.ItemUserBinding
import com.example.consumojsonlocal.helper.toBitmap
import com.example.consumojsonlocal.models.User

class UserAdapter(var list : List<User>) : RecyclerView.Adapter<UserAdapter.UserHolder>() {
    inner  class UserHolder (var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
       holder.binding.apply {
           list[position].apply {
               txtName.text = "$FirstName $LastName"
               if(Description != null){
                   txtDesc.text = Description
               }
               imgPhoto.setImageBitmap(Photo.toBitmap())
           }
       }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}