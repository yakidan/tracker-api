package com.itmo.ppo.tasktreker.service

import com.itmo.ppo.tasktreker.model.Task
import com.itmo.ppo.tasktreker.repository.TaskRepository
import com.itmo.ppo.tasktreker.repository.TaskStatusRepository
import com.itmo.ppo.tasktreker.service.mapper.TaskMapper
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityNotFoundException

@Service
class TaskService(
    val taskRepository: TaskRepository,
    val taskStatusRepository: TaskStatusRepository,
    val mapper: TaskMapper
) {
    @Transactional(readOnly = true)
    fun getTasks(limit: Int): List<Task> {
        return taskRepository.findAll(Pageable.ofSize(limit)).map { mapper.entityToModel(it) }.toList()
    }

    @Transactional(readOnly = true)
    fun getTask(id: UUID): Task? {
        val task = mapper.entityToModel(
            taskRepository.findById(id)
                .orElseThrow {
                    EntityNotFoundException("Task with id $id is not found")
                }
        )
        return task
    }

    @Transactional
    fun createTask(task: Task): Task {
        return mapper.entityToModel(taskRepository.save(mapper.modelToEntity(task)))
    }

    @Transactional
    fun updateTask(task: Task): Task? {
        if (task.id == null) {
            throw IllegalArgumentException("ID was is not null")
        }
        val entity = taskRepository.findById(task.id)
            .orElseThrow {
                EntityNotFoundException("Task with id = ${task.id} was not found")
            }
        entity.name = task.name
        entity.description = task.description
        entity.taskStatus = taskStatusRepository.findByName(task.status)
            .orElseThrow {
                EntityNotFoundException("Status with name ${task.status} not found")
            }
        return mapper.entityToModel(taskRepository.save(entity))
    }

    fun deleteTask(id: UUID) {
        taskRepository.deleteById(id)
    }
}