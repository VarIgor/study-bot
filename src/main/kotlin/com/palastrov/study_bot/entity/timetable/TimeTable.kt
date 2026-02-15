package com.palastrov.study_bot.entity.timetable

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID
import java.time.DayOfWeek

@Entity
@Table(name = "timetable")
class TimeTable (
    @field:Column(name = "title") var title: String? = null,
    @field:Column(name = "description") var description: String? = null,
    @field:Column(name = "hour") var hour: Short? = null,
    @field:Column(name = "minute") var minute: Short? = null,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null
}