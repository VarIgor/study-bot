package com.palastrov.study_bot.telegram

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("telegram-bot")
class TelegramProperties {

    var userName: String = ""
    var botToken: String = ""
    var path: String = ""
}