package com.palastrov.study_bot.service.manager

import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message

object FeedbackManager {
    fun handleFeedbackCommand(message: Message): BotApiMethod<*>? {
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

    fun handleFeedbackCallback(callbackQuery: CallbackQuery): BotApiMethod<*>? {
        return EditMessageText.builder()
            .chatId(callbackQuery.message.chatId.toString())
            .messageId(callbackQuery.message.messageId)
            .text(
                """
                     📝 *Форма обратной связи*
                
                Пожалуйста, напишите ваше сообщение в чат.
                
                Что мы ждем:
                • Подробное описание проблемы или идеи
                • Шаги для воспроизведения (если это баг)
                • Ваши контакты (если нужен ответ)
                
                ⚠️ *Внимание:* Не отправляйте конфиденциальные данные!
                          """.trimIndent()
            )
            .parseMode("Markdown")
            .build()
    }
}