package com.itmo.ppo.tasktreker.model

data class Task(
    val id: Int?,
    val name: String,
    val description: String,
    val status: String?,
) {
}