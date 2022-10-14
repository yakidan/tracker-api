package com.itmo.ppo.tasktreker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskTrackerApplication

fun main(args: Array<String>) {
    runApplication<TaskTrackerApplication>(*args)
}
