package com.palastrov.study_bot.entity.task

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID
@Entity
@Table(name = "task")
class Task(
    @field:Column(name = "title") var title: String? = null,
    @field:Column(name = "text_content") var textContent: String? = null,
    @field:Column(name = "actual_message_id") var messageId: Integer? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null
}