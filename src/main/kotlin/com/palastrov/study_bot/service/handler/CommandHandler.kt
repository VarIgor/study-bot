package com.palastrov.study_bot.service.handler

import com.palastrov.study_bot.service.data.Command
import com.palastrov.study_bot.telegram.TelegramWebhookBot
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class CommandHandler {
    fun answer(message: Message, bot: TelegramWebhookBot): BotApiMethod<*>? {
        return when (message.text) {
            Command.START -> handleStart(message)
            Command.FEEDBACK -> handleFeedback(message)
            Command.HELP -> handleHelp(message)
            else -> defaultAnswer(message)
        }

    }

    private fun handleStart(message: Message): BotApiMethod<*> {
        return SendMessage.builder()
            .chatId(message.chatId.toString())
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

    private fun handleFeedback(message: Message): BotApiMethod<*>? {
        return SendMessage.builder()
            .chatId(message.chatId)
            .text(
                """
                 💌 *Обратная связь*
            
            Мы ценим ваше мнение! Здесь вы можете:
            
            📢 *Сообщить о проблеме* - если что-то работает не так
            💡 *Предложить улучшение* - ваши идеи для развития бота
            ❓ *Задать вопрос* - если нужна помощь
            
            *Как оставить отзыв?*
            Просто напишите ваше сообщение в чат, и оно будет передано разработчикам.
            
            ⏱ *Время ответа*: 1-2 рабочих дня
            🔒 *Конфиденциальность*: Ваши данные защищены
            
            Спасибо, что помогаете делать бота лучше! 🚀
            """.trimIndent()
            )
            .parseMode("Markdown")
            .build()
    }

    private fun handleHelp(message: Message): BotApiMethod<*>? {
        return SendMessage.builder()
            .chatId(message.chatId)
            .text(
                """
                📚 *Помощь по командам Tutor-Bot*
            
            Основные команды:
            /start - Начало работы с ботом
            /help - Получить справку по командам
            /feedback - Оставить отзыв или предложение
            
            Функциональность:
            📅 *Расписание* - Просмотр и управление занятиями
            📝 *Домашние задания* - Получение и отправка ДЗ
            📊 *Успеваемость* - Отслеживание прогресса
            👥 *Ученики/Репетиторы* - Управление профилями
            
            Нужна помощь? Используйте /feedback чтобы связаться с разработчиком!       
            """.trimIndent()
            )
            .parseMode("Markdown")
            .build()
    }

    private fun defaultAnswer(message:Message): BotApiMethod<*>? {
        return SendMessage.builder()
            .chatId(message.chatId)
            .text("""
                ❓ *Извините, я не понял команду*
            
            Вот что я умею:
            /start - Начало работы
            /help - Справка по командам  
            /feedback - Обратная связь
            
            Если вам нужна помощь, используйте /help для получения полного списка команд.
            
            🤖 *Tutor-Bot*
            """.trimIndent())
            .parseMode("Markdown")
            .build()
    }
}
//todo Move the message creation function to the object MessageUtils
//todo Add an inline keyboard for the start command