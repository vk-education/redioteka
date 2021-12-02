package com.example.redioteka.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redioteka.models.User
import com.example.redioteka.models.UserAuth
import com.example.redioteka.repository.UserRepo
import com.example.redioteka.repository.Result
import kotlinx.coroutines.launch

class UserViewModel(app: Application) : AndroidViewModel(app) {

    private val userRepo: UserRepo = UserRepo(app.applicationContext)
    private val userId: String = "1"
    val user = MutableLiveData<User>()
    protected val state = MutableLiveData<State<User>>()

    fun state() = state

    fun login(email: String, password: String) {
        val userForm = UserAuth(email = email, password = password)

        viewModelScope.launch {
            val newState = when(val resp: Result<User> = userRepo.login(userForm)) {
                is Result.Success -> State.Success(resp.data)
                is Result.Fail -> State.Fail(resp.error)
            }
            state.postValue(newState)
        }
    }
}
