package com.palastrov.study_bot.service.manager

import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery

interface Manager {
    fun handleCommand(message: Message): BotApiMethod<*>?
    fun handleCallback(callbackQuery: CallbackQuery): BotApiMethod<*>?
    fun handleMessage(message: Message): BotApiMethod<*>? = null
    fun handleInlineQuery(inlineQuery: InlineQuery): BotApiMethod<*>? = null
}