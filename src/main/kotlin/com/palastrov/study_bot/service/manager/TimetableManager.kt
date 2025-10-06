package com.palastrov.study_bot.service.manager

import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message

class TimetableManager: Manager {
    override fun handleCommand(message: Message): BotApiMethod<*>? {
        TODO("Not yet implemented")
    }

    override fun handleCallback(callbackQuery: CallbackQuery): BotApiMethod<*>? {
        TODO("Not yet implemented")
    }
}