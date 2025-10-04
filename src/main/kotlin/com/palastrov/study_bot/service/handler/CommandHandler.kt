package com.palastrov.study_bot.service.handler

import com.palastrov.study_bot.service.data.BotConstants.Callback
import com.palastrov.study_bot.service.data.BotConstants.Command
import com.palastrov.study_bot.service.factory.KeyboardFactory
import com.palastrov.study_bot.service.manager.FeedbackManager
import com.palastrov.study_bot.service.manager.HelpManager
import com.palastrov.study_bot.telegram.TelegramWebhookBot
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class CommandHandler(
    val keyboardFactory: KeyboardFactory
) {
    fun answer(message: Message, bot: TelegramWebhookBot): BotApiMethod<*>? {
        return when (message.text) {
            Command.START -> handleStart(message)
            Command.FEEDBACK -> FeedbackManager.handleFeedbackCommand(message)
            Command.HELP -> HelpManager.handleHelpCommand(message)
            else -> defaultAnswer(message)
        }

    }

    private fun handleStart(message: Message): BotApiMethod<*> {
        return SendMessage.builder()
            .chatId(message.chatId.toString())
            .replyMarkup(
                keyboardFactory.getInlineKeyboard(
                    mutableListOf("Помощь", "Обратная связь"),
                    mutableListOf(2),
                    mutableListOf(Callback.HELP,Callback.FEEDBACK)
                )
            )
            .text(
                """
                🖖 *Добро пожаловать в Tutor-Bot!*
            
            *Ваш цифровой помощник для эффективного обучения*
            
            🎯 *Что умеет бот:*
            📅 *Умное расписание* - Организация занятий и напоминания
            📚 *Домашние задания* - Выдача, прием и проверка ДЗ
            📊 *Аналитика успеваемости* - Наглядная статистика прогресса
            👥 *Управление группами* - Для репетиторов с несколькими учениками
            
            🚀 *Ближайшие планы:*
            • Система оплаты занятий
            • Видео-конференции
            • Интеграция с Google Calendar
            
            💡 *Начните с этих команд:*
            /help - Получить справку по всем функциям
            /feedback - Предложить идею или сообщить о проблеме
            
            *Давайте сделаем обучение эффективным вместе!* ✨
            """.trimIndent()
            ) // ← removes code indentation
            .parseMode("Markdown")
            .build()
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