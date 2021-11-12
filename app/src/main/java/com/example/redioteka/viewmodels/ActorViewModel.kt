package com.example.redioteka.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redioteka.models.Actor
import com.example.redioteka.repository.ActorRepo
import kotlinx.coroutines.launch

class ActorViewModel(actorRepo: ActorRepo, actorId: String) : ViewModel() {
    private var actor: Actor = Actor()

    init {
        viewModelScope.launch {
            actor = actorRepo.getActor(actorId)
            Log.i("QUERY", actor.toString())
        }
    }
}