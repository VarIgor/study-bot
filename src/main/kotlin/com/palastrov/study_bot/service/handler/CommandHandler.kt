package com.palastrov.study_bot.service.handler

import com.palastrov.study_bot.telegram.MyTelegramWebhookBot
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class CommandHandler {
    fun answer(message: Message, bot: MyTelegramWebhookBot): BotApiMethod<*>?{
        return null
    }
}