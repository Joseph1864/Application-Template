package com.example.applicationtemplate.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.applicationtemplate.domain.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenViewModel(): ViewModel() {

    private val _viewState = MutableStateFlow(
        HomeScreenViewState(
            tasks = List(10) { index ->
                Task(
                    title = "Task $index",
                    description = "Here is a big long description",
                    status = Task.Status.INCOMPLETE
                )
            }
        )
    )
    val viewState = _viewState.asStateFlow()

    fun onCreateTaskClick() {
        Log.d("HomeScreenVM","Task Created!")
    }

    fun onCompleteTaskClick(task: Task) {
        Log.d("HomeScreenVM","Task Completed!")
    }
}

data class HomeScreenViewState(
    val tasks: List<Task> = emptyList()
)