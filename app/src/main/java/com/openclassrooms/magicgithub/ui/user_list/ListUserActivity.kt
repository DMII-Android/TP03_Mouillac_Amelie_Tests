package com.openclassrooms.magicgithub.ui.user_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.openclassrooms.magicgithub.NavigationListener
import com.openclassrooms.magicgithub.R
import com.openclassrooms.magicgithub.databinding.ActivityListUserBinding
import com.openclassrooms.magicgithub.di.Injection
import com.openclassrooms.magicgithub.fragments.ListUserFragment
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.repository.UserRepository

class ListUserActivity : AppCompatActivity(), NavigationListener {

    @VisibleForTesting
    lateinit var repository: UserRepository

    lateinit var binding: ActivityListUserBinding

    // OVERRIDE ---
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = Injection.createUserRepository()

        showFragment(ListUserFragment(repository))
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun showFragment(fragment: Fragment) {
        changeFragment(fragment)
    }

}