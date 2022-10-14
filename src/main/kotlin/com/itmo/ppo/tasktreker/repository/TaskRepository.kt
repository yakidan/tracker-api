package com.itmo.ppo.tasktreker.repository

import com.itmo.ppo.tasktreker.entity.TaskEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<TaskEntity, Int> {
}