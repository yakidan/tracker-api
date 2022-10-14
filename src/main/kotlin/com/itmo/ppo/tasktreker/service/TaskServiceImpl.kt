package com.itmo.ppo.tasktreker.service

import com.itmo.ppo.tasktreker.model.Task
import com.itmo.ppo.tasktreker.repository.TaskRepository
import com.itmo.ppo.tasktreker.repository.TaskStatusRepository
import com.itmo.ppo.tasktreker.service.mapper.TaskMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import javax.transaction.Transactional

@Component
class TaskServiceImpl(
    @Autowired val taskRepository: TaskRepository,
    @Autowired val taskStatusRepository: TaskStatusRepository,
    @Autowired val mapper: TaskMapper
) : TaskService {
    override fun getTasks(limit: Int): List<Task> {
        return taskRepository.findAll(Pageable.ofSize(limit)).map { mapper.entityToModel(it) }.toList()
    }

    override fun findTask(id: Int): Task? {
        val task = mapper.entityToModel(
            taskRepository.findById(id)
                .orElseThrow {
                    throw ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Task with id ${id} is not found"
                    )
                }
        )

        return task
    }

    @Transactional
    override fun createTask(task: Task): Task {
        val entity = try {
            mapper.modelToEntity(task)
        } catch (e: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
        return mapper.entityToModel(taskRepository.save(entity))
    }

    @Transactional
    override fun updateTask(task: Task): Task? {
        if (task.id == null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "ID was is not null")
        } else {
            val entity =
                taskRepository.findById(task.id)
                    .orElseThrow {
                        ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Task with id = ${task.id} was not found"
                        )
                    }
            entity.name = task.name
            entity.description = task.description
            entity.taskStatus = taskStatusRepository.findByName(task.status!!)
                ?: throw IllegalArgumentException("Status with name ${task.status} not found")


            return mapper.entityToModel(taskRepository.save(entity))
        }
    }

    override fun deleteTask(id: Int): String {
        taskRepository.deleteById(id)
        return "task with id = ${id} was deleted"
    }


}