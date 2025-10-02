package com.palastrov.study_bot.telegram

import com.palastrov.study_bot.service.UpdateDispatcher
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramWebhookBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class TelegramWebhookBot(
    private val telegramProperties: TelegramProperties,
    private val updateDispatcher: UpdateDispatcher
) : TelegramWebhookBot(DefaultBotOptions(), telegramProperties.botToken) {

    override fun getBotUsername(): String = telegramProperties.userName
    override fun getBotPath(): String = telegramProperties.path

    override fun onWebhookUpdateReceived(update: Update): BotApiMethod<*>? = updateDispatcher.distribute(update, this)

}