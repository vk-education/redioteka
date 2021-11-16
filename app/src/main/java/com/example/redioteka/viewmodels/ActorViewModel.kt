package com.example.redioteka.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redioteka.models.Actor
import com.example.redioteka.repository.ActorRepo
import kotlinx.coroutines.launch

class ActorViewModel() : ViewModel() {
    private val actorRepo: ActorRepo = ActorRepo()
    private val actorId: String = "3"
    val actor = MutableLiveData<Actor>()

    init {
        viewModelScope.launch {
            actor.value = actorRepo.getActor(actorId)
            Log.i("QUERY", actor.value.toString())
        }
    }
}
