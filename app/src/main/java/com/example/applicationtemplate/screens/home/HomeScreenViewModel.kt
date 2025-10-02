package com.example.applicationtemplate.screens.home

import androidx.lifecycle.ViewModel
import com.example.applicationtemplate.domain.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenViewModel(): ViewModel() {

    private val _viewState = MutableStateFlow(HomeScreenViewState())
    val viewState = _viewState.asStateFlow()

    fun onCreateTaskClick() {
        println("Task Created!")
    }
}

data class HomeScreenViewState(
    val tasks: List<Task> = emptyList()
)