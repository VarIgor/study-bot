package com.palastrov.study_bot.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Version

@Entity
@Table(name = "users")
class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "chatId", unique = true)
    var chatId: Long = 0

    constructor()
    constructor(chatId: Long) : this() {
        this.chatId = chatId
    }
}