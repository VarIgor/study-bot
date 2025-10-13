package com.palastrov.study_bot.repository

import com.palastrov.study_bot.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findByChatId(chatId: Long): User?
    fun existsByChatId(chatId: Long): Boolean
}