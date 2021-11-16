package com.example.redioteka.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redioteka.models.User
import com.example.redioteka.repository.UserRepo
import kotlinx.coroutines.launch

class UserViewModel() : ViewModel() {
    private val userRepo: UserRepo = UserRepo()
    private val userId: String = "1"
    val user = MutableLiveData<User>()

    init {
        viewModelScope.launch {
            user.value = userRepo.getUser(userId)
            Log.i("QUERY", user.value.toString())
        }
    }
}
