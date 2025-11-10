package com.palastrov.study_bot

import com.palastrov.study_bot.telegram.TelegramProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
@EnableConfigurationProperties(TelegramProperties::class)
class StudyBotApplication

fun main(args: Array<String>) {
	runApplication<StudyBotApplication>(*args)
}
