package com.example.applicationtemplate.domain

data class Task(
    var title: String,
    var description: String,
    var status: Status,
) {
    enum class Status {
        INCOMPLETE,
        COMPLETE,
    }
}