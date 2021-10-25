package com.example.redioteka.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redioteka.models.User
import com.example.redioteka.repository.UserRepo
import kotlinx.coroutines.launch

class UserViewModel(userRepo: UserRepo, userId: String) : ViewModel() {
    private var user: User = User()

    init {
        viewModelScope.launch {
            user = userRepo.getUser(userId)
            Log.i("QUERY", user.toString())
        }
    }
}