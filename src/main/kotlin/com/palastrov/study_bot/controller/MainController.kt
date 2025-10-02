package com.palastrov.study_bot.controller

import com.palastrov.study_bot.telegram.TelegramWebhookBot
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

@RestController
class MainController(
    private val webhookBot: TelegramWebhookBot
) {

    @PostMapping("/")
    fun listener(@RequestBody update: Update): BotApiMethod<*>? {
        return webhookBot.onWebhookUpdateReceived(update)
    }

}