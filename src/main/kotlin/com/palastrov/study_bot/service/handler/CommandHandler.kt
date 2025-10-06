package com.palastrov.study_bot.service.handler

import com.palastrov.study_bot.service.data.BotConstants.Command
import com.palastrov.study_bot.service.manager.FeedbackManager
import com.palastrov.study_bot.service.manager.HelpManager
import com.palastrov.study_bot.service.manager.StartManager
import com.palastrov.study_bot.telegram.TelegramWebhookBot
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class CommandHandler(
     private val feedbackManager: FeedbackManager,
     private val helpManager: HelpManager,
     private val startManager: StartManager
) {
    fun answer(message: Message, bot: TelegramWebhookBot): BotApiMethod<*>? {
        return when (message.text) {
            Command.FEEDBACK -> feedbackManager.handleCommand(message)
            Command.HELP -> helpManager.handleCommand(message)
            Command.START -> startManager.handleCommand(message)
            else -> defaultAnswer(message)
        }

    }

    private fun defaultAnswer(message: Message): BotApiMethod<*>? {
        return SendMessage.builder()
            .chatId(message.chatId)
            .text(
                """
                ❓ *Извините, я не понял команду*
            
            Вот что я умею:
            /start - Начало работы
            /help - Справка по командам  
            /feedback - Обратная связь
            
            Если вам нужна помощь, используйте /help для получения полного списка команд.
            
            🤖 *Tutor-Bot*
            """.trimIndent()
            )
            .parseMode("Markdown")
            .build()
    }
}
//todo Move the message creation function to the object MessageUtils
//todo Add an inline keyboard for the start command