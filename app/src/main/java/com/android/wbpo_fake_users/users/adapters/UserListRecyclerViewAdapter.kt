package com.android.wbpo_fake_users.users.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.wbpo_fake_users.R
import com.android.wbpo_fake_users.databinding.UserListItemBinding
import com.android.wbpo_fake_users.users.retrofit.models.User
import com.bumptech.glide.Glide

class UserListRecyclerViewAdapter(
    private val isUserFollowing: (Int) -> Boolean,
    private val onFollowButtonClick: (Int) -> Unit,
) :
    RecyclerView.Adapter<UserListRecyclerViewAdapter.UserViewHolder>() {

    private val users = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUsers(newUsers: List<User>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            val nameSurnameText = "${user.firstName} ${user.lastName}"
            binding.nameSurname.text = nameSurnameText
            binding.email.text = user.email
            Glide.with(binding.avatarPicture.context)
                .load(user.avatar)
                .placeholder(R.drawable.wbpo_logo)
                .into(binding.avatarPicture)

            val isFollowing = isUserFollowing(user.id)
            binding.favoriteButton.setImageResource(
                if (isFollowing) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            )

            binding.favoriteButton.setOnClickListener {
                onFollowButtonClick(user.id)
                notifyItemChanged(adapterPosition)
            }
        }
    }
}
