package com.palastrov.study_bot.service.manager

import com.palastrov.study_bot.service.data.BotConstants.Callback
import com.palastrov.study_bot.service.factory.KeyboardFactory
import com.palastrov.study_bot.service.factory.TelegramMessageFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

@Component
class StartManager(
    private val messageFactory: TelegramMessageFactory,
    private val keyboardFactory: KeyboardFactory
) {
    private val startText = """
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

    fun handleCommand(message: Message) : SendMessage {
        return messageFactory.createTextMessage(
            message.chatId, startText, keyboardFactory.getInlineKeyboard(
                mutableListOf("Помощь", "Обратная связь"),
                mutableListOf(2),
                mutableListOf(Callback.HELP, Callback.FEEDBACK)
            )
        )
    }
}