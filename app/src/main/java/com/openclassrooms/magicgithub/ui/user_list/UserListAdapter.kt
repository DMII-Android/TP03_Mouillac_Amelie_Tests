package com.openclassrooms.magicgithub.ui.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.magicgithub.R
import com.openclassrooms.magicgithub.databinding.ItemListUserBinding
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.repository.UserRepository
import com.openclassrooms.magicgithub.utils.UserDiffCallback

class UserListAdapter(private val callback: Listener) : RecyclerView.Adapter<ListUserViewHolder>() {
    // FOR DATA ---
    private lateinit var users: List<User>

    interface Listener {
        fun onClickDelete(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        users = listOf()
        val binding = ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bind(users[position], callback)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    // PUBLIC API ---
    fun updateList(newList: List<User>) {
        users = listOf()
        val diffResult = DiffUtil.calculateDiff(UserDiffCallback(newList, users))
        users = ArrayList(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}