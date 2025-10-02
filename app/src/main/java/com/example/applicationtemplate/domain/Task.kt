package com.example.applicationtemplate.domain

data class Task(
    var title: String,
    var description: String,
    var status: Status,
) {

    fun deleteTask() {
        //TODO
    }

    fun completeTask() {
        status = Status.COMPLETE
    }

    fun editTask(
        newTitle: String,
        newDescription: String,
    ) {
        title = newTitle
        description = newDescription
    }

    enum class Status {
        INCOMPLETE,
        COMPLETE,
    }
}