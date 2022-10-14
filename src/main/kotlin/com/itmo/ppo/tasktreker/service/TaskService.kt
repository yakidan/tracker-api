package com.itmo.ppo.tasktreker.service

import com.itmo.ppo.tasktreker.model.Task

interface TaskService {

    fun getTasks(limit: Int): List<Task>
    fun findTask(id: Int): Task?
    fun createTask(task: Task): Task
    fun updateTask(task: Task): Task?
    fun deleteTask(id: Int): String
}