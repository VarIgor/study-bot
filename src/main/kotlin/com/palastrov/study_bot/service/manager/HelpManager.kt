package com.palastrov.study_bot.service.manager

import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message

object HelpManager {
    fun handleHelpCommand(message: Message): BotApiMethod<*>? {
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

    fun handleHelpCallback(callbackQuery: CallbackQuery): BotApiMethod<*>? {
        return EditMessageText.builder()
            .chatId(callbackQuery.message.chatId.toString())
            .messageId(callbackQuery.message.messageId)
            .text(
                """
                     ℹ️ *Расширенная справка*
                
                Здесь будет детальная информация о функциях:
                
                📅 *Расписание:*
                • Создание уроков
                • Напоминания о занятиях
                • Управление расписанием
                
                📚 *Домашние задания:*
                • Выдача заданий
                • Проверка выполненных работ
                • Обратная связь по ДЗ
                
                📊 *Успеваемость:*
                • Статистика прогресса
                • Графики обучения
                • Рекомендации по улучшению
                       """.trimIndent()
            )
            .parseMode("Markdown")
            .build()
    }
}