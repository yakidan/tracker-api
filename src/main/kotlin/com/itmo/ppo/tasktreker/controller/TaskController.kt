package com.itmo.ppo.tasktreker.controller

import com.itmo.ppo.tasktreker.model.Task
import com.itmo.ppo.tasktreker.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/tasks")
class TaskController(@Autowired val taskService: TaskService) {

    @GetMapping
    fun getTasks(@RequestParam(value = "limit", defaultValue = "100") limit: Int): List<Task> {
        return taskService.getTasks(limit)
    }

    @GetMapping("/{id}")
    fun findTask(@PathVariable id: Int): Task? {
        return taskService.findTask(id)
    }

    @PostMapping
    fun createTasks(@RequestBody task: Task): Task {
        return taskService.createTask(task)
    }

    @PutMapping
    fun updateTask(@RequestBody task: Task): Task? {
        return taskService.updateTask(task)
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Int): String {
        return taskService.deleteTask(id)
    }

}