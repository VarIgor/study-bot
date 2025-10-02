package com.palastrov.study_bot.service.handler

import com.palastrov.study_bot.telegram.TelegramWebhookBot
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class MessageHandler {
    fun answer(message: Message, bot: TelegramWebhookBot): BotApiMethod<*>?{
        return null
    }
}