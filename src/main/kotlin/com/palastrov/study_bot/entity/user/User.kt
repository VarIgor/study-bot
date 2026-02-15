package com.palastrov.study_bot.entity.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "chat_id", unique = true, nullable = false)
    var chatId: Long = 0

    @Enumerated(EnumType.STRING)
    var role: Role? = null

    @Enumerated(EnumType.STRING)
    var action: Action? = null

    constructor()
    constructor(chatId: Long, role: Role? = null, action: Action? = null) : this() {
        this.chatId = chatId
        this.role = role
        this.action = action
    }
}
