package com.itmo.ppo.tasktreker.entity

import javax.persistence.*

@Entity
@Table(name = "tasks")
class TaskEntity(

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "description")
    var description: String? = null

    @ManyToOne
    @JoinColumn(name = "status_id")
    var taskStatus: TaskStatus? = null
}