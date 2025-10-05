package com.palastrov.study_bot.service.manager

import com.palastrov.study_bot.service.factory.TelegramMessageFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message

@Component
class FeedbackManager(
    private val messageFactory: TelegramMessageFactory,
) {

    private val commandText = """
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
        private val callbackText = """
                     📝 *Форма обратной связи*
                
                Пожалуйста, напишите ваше сообщение в чат.
                
                Что мы ждем:
                • Подробное описание проблемы или идеи
                • Шаги для воспроизведения (если это баг)
                • Ваши контакты (если нужен ответ)
                
                ⚠️ *Внимание:* Не отправляйте конфиденциальные данные!
                          """.trimIndent()

    fun handleFeedbackCommand(message: Message): BotApiMethod<*>? {
        return  messageFactory.createTextMessage(message.chatId, commandText, null)
    }

    fun handleFeedbackCallback(callbackQuery: CallbackQuery): BotApiMethod<*>? {
        return  messageFactory.createEditMessage(callbackQuery, callbackText, null)
    }
}