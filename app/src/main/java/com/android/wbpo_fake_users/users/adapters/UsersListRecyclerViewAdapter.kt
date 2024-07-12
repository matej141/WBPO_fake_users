package com.android.wbpo_fake_users.users.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.android.wbpo_fake_users.databinding.UsersListItemBinding
import com.android.wbpo_fake_users.users.retrofit.models.User


class UsersListRecyclerViewAdapter(
    private val data: List<User>,
    private val onMenuClick: (User, ImageButton) -> Unit,
    // private val onClickListener: View.OnClickListener
) : RecyclerView.Adapter<UsersListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            UsersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: User = data[position]
        holder.bind(item)
        with(holder.itemView) {
            tag = data[position]
           // setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val binding: UsersListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            val nameAndSurname = "${item.firstName} ${item.lastName}"
            binding.nameSurname.text = nameAndSurname
            binding.email.text = item.email

            binding.favoriteButton.setOnClickListener {
                onMenuClick(item, binding.favoriteButton)
            }
        }
    }
}