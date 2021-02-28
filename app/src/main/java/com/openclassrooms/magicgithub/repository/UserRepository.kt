package com.openclassrooms.magicgithub.repository

import com.openclassrooms.magicgithub.api.ApiService
import com.openclassrooms.magicgithub.api.FakeApiServiceGenerator
import com.openclassrooms.magicgithub.model.User
import java.util.*

class UserRepository(private val apiService: ApiService) {

    val users: MutableList<User>
        get() = InMemory_UserS

    fun generateRandomUser() {
        InMemory_UserS.add(FakeApiServiceGenerator.generateRandomUser())
    }

    fun deleteUser(user: User?) {
        InMemory_UserS.remove(user)
    }

    private fun getRandomString(): String {
        val generator = Random()
        val randomStringBuilder = StringBuilder()
        val randomLength = generator.nextInt(10)
        var tempChar: Char
        for (i in 0 until randomLength) {
            tempChar = (generator.nextInt(96) + 32).toChar()
            randomStringBuilder.append(tempChar)
        }
        return randomStringBuilder.toString()
    }

    private val InMemory_UserS: MutableList<User> = mutableListOf(
            User(UUID.randomUUID().toString(),"lola-m", ""),
            User(UUID.randomUUID().toString(),"enzo-w-77", ""),
            User(UUID.randomUUID().toString(),"AmikaA", ""),
            User(UUID.randomUUID().toString(),"xxXXxx", ""),
            User(UUID.randomUUID().toString(),"meteoY-05", ""),
            User(UUID.randomUUID().toString(),"immi.ht74", "")
    )
}
