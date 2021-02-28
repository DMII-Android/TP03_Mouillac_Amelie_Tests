package com.openclassrooms.magicgithub.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.openclassrooms.magicgithub.R
import com.openclassrooms.magicgithub.databinding.ListUserFragmentBinding
import com.openclassrooms.magicgithub.di.Injection
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.repository.UserRepository
import com.openclassrooms.magicgithub.ui.user_list.ListUserActivity
import com.openclassrooms.magicgithub.ui.user_list.UserListAdapter

class ListUserFragment(var repository: UserRepository): Fragment(), UserListAdapter.Listener {

    // FOR DESIGN ---
    lateinit var binding: ListUserFragmentBinding
    lateinit var recyclerView: RecyclerView
    lateinit var fab: FloatingActionButton

    // FOR DATA ---
    private lateinit var adapter: UserListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = ListUserFragmentBinding.inflate(inflater, container, false)

        configureFab()
        configureRecyclerView()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    // CONFIGURATION ---
    private fun configureRecyclerView() {
        recyclerView = binding.activityListUserRv
        adapter = UserListAdapter(this)
        recyclerView.setAdapter(adapter)
    }

    private fun configureFab() {
        fab = binding.activityListUserFab
        fab.setOnClickListener { view: View? ->
            repository.generateRandomUser()
            loadData()
        }
    }

    private fun loadData() {
        adapter.updateList(Injection.createUserRepository().users)
    }

    // ACTIONS ---
    override fun onClickDelete(user: User) {
        Log.d(ListUserActivity::class.java.name, "User tries to delete a item.")
        repository.deleteUser(user)
        loadData()
    }
}