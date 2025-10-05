package com.palastrov.study_bot.service.manager

import com.palastrov.study_bot.service.factory.KeyboardFactory
import com.palastrov.study_bot.service.factory.TelegramMessageFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
@Component
class HelpManager (
    private val messageFactory: TelegramMessageFactory,
) {
    private val helpText = """""
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

    private val callbackText = """
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

    fun handleHelpCommand(message: Message): BotApiMethod<*>? {
        return messageFactory.createTextMessage(message.chatId, helpText, null)

    }

    fun handleHelpCallback(callbackQuery: CallbackQuery): BotApiMethod<*>? {
        return messageFactory.createEditMessage(callbackQuery, callbackText, null)
    }
}