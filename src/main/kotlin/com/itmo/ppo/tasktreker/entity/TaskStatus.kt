package com.itmo.ppo.tasktreker.entity

import javax.persistence.*

@Entity
@Table(name = "status")
class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(name = "name")
    var name: String? = null

}