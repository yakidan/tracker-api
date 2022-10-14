package com.itmo.ppo.tasktreker.repository

import com.itmo.ppo.tasktreker.entity.TaskStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskStatusRepository : JpaRepository<TaskStatus, Int> {
    fun findByName(name: String):TaskStatus?
}