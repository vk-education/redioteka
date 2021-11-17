package com.example.redioteka.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redioteka.models.User
import com.example.redioteka.models.UserAuth
import com.example.redioteka.repository.UserRepo
import com.example.redioteka.repository.Result
import kotlinx.coroutines.launch

class UserViewModel() : ViewModel() {
    private val userRepo: UserRepo = UserRepo()
    private val userId: String = "1"
    val user = MutableLiveData<User>()
    protected val state = MutableLiveData<State<User>>()

    fun state() = state

    fun login(email: String, password: String) {
        val userForm = UserAuth(email = email, password = password)

        viewModelScope.launch {
            Log.i("TAG", "in viewmodel")
            val newState = when(val resp: Result<User> = userRepo.login(userForm)) {
                is Result.Success -> State.Success(resp.data)
                is Result.Fail -> State.Fail(resp.error)
            }
            state.postValue(newState)
        }
    }
}
