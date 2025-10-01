package com.palastrov.study_bot.service.handler

import com.palastrov.study_bot.telegram.MyTelegramWebhookBot
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class CommandHandler {
    fun answer(message: Message, bot: MyTelegramWebhookBot): BotApiMethod<*>? {
        return when (message.text) {
            "/start" -> createStartMessage(message)
            else -> null
        }

    }

    private fun createStartMessage(message: Message): BotApiMethod<*> {
        return SendMessage.builder()
            .chatId(message.chatId.toString())
            .text(
                "\uD83D\uDD96Приветствую в Tutor-Bot, инструменте для упрощения взаимодействия репититора и ученика.\n" +
                        "\n" +
                        "Что бот умеет?\n" +
                        "\uD83D\uDCCC Составлять расписание\n" +
                        "\uD83D\uDCCC Прикреплять домашние задания\n" +
                        "\uD83D\uDCCC Ввести контроль успеваемости".trimIndent() ) // ← removes code indentation
            .build()
    }
}