package com.palastrov.study_bot.controller

import com.palastrov.study_bot.telegram.MyTelegramWebhookBot
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@RestController
class MainController(
    private val webhookBot: MyTelegramWebhookBot
) {

    @PostMapping("/")
    fun listener(@RequestBody update: Update): BotApiMethod<*>? {
        return webhookBot.onWebhookUpdateReceived(update)
    }

}