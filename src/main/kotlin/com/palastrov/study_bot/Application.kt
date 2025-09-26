package com.palastrov.study_bot

import com.palastrov.study_bot.telegram.TelegramProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(TelegramProperties::class)
class StudyBotApplication

fun main(args: Array<String>) {
	runApplication<StudyBotApplication>(*args)
}
