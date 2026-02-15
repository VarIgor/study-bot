package com.palastrov.study_bot.repository

import com.palastrov.study_bot.entity.timetable.TimeTable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TimeTableRepo: JpaRepository<TimeTable, UUID > {
}