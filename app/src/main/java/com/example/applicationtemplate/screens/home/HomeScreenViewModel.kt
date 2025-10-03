package com.example.applicationtemplate.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.applicationtemplate.domain.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenViewModel(): ViewModel() {

    private val _viewState = MutableStateFlow(HomeScreenViewState())
    val viewState = _viewState.asStateFlow()

    fun onShowCreateDialog() {
        _viewState.value = _viewState.value.copy(isCreateDialogVisible = true)
    }

    fun onSubmitCreateDialog() {
        val newTask = Task(
            title = _viewState.value.newTaskTitle.trim(),
            description = _viewState.value.newTaskDescription.trim(),
            status = Task.Status.INCOMPLETE,
        )

        val updatedTasks = (_viewState.value.tasks + newTask).sortedBy { it.status == Task.Status.COMPLETE }
        _viewState.value = _viewState.value.copy(
            tasks = updatedTasks,
            isCreateDialogVisible = false,
            newTaskTitle = "",
            newTaskDescription = "",
        )
    }

    fun onTitleChanged(title: String) {
        _viewState.value = _viewState.value.copy(newTaskTitle = title)
    }

    fun onDescriptionChange(description: String) {
        _viewState.value = _viewState.value.copy(newTaskDescription = description)
    }

    fun onDismissCreateDialog() {
        _viewState.value = _viewState.value.copy(
            isCreateDialogVisible = false,
            newTaskTitle = "",
            newTaskDescription = "",
        )
    }

    fun onCompleteTaskClick(task: Task) {
        val updatedTasks = _viewState.value.tasks.map {
            if (it == task) {
                it.copy(
                    status = if (it.status == Task.Status.COMPLETE)
                        Task.Status.INCOMPLETE
                    else
                        Task.Status.COMPLETE
                )
            } else it
        }.sortedBy { it.status == Task.Status.COMPLETE }
        _viewState.value = _viewState.value.copy(tasks = updatedTasks)
    }

    fun onEditTaskClick(task: Task) {
        Log.d("HomeScreenVM","Task Edited!")
    }

    fun onDeleteTaskClick(task: Task) {
        val updatedTasks = _viewState.value.tasks.filter { it != task }
        _viewState.value = _viewState.value.copy(tasks = updatedTasks)
    }

}

data class HomeScreenViewState(
    val tasks: List<Task> = emptyList(),
    val isCreateDialogVisible: Boolean = false,
    val newTaskTitle: String = "",
    val newTaskDescription: String = "",
)