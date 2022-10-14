package com.itmo.ppo.tasktreker.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "task_status")
class TaskStatus {
    @Id
    var id: UUID? = null

    @Column(name = "name")
    var name: String? = null
}