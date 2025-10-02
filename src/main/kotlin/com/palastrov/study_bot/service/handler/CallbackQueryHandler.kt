package com.palastrov.study_bot.service.handler

import com.palastrov.study_bot.telegram.TelegramWebhookBot
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

@Service
class CallbackQueryHandler {
    fun answer(callbackQuery: CallbackQuery, bot: TelegramWebhookBot): BotApiMethod<*>?{
        return null
    }
}