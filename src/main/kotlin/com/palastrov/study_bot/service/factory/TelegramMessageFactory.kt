package com.palastrov.study_bot.service.factory

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard

@Component
class TelegramMessageFactory {

    fun createTextMessage(
        chatId: Long,
        text: String,
        keyboard: ReplyKeyboard? = null
    ): SendMessage {
        return SendMessage.builder()
            .chatId(chatId)
            .text(text.trimIndent())
            .replyMarkup(keyboard)
            .parseMode("Markdown")
            .disableWebPagePreview(true)
            .build()
    }

    fun createEditMessage(
        callbackQuery: CallbackQuery,
        text: String,
        keyboard: InlineKeyboardMarkup? = null
    ): EditMessageText {
        return EditMessageText.builder()
            .chatId(callbackQuery.message.chatId)
            .messageId(callbackQuery.message.messageId)
            .text(text.trimIndent())
            .replyMarkup(keyboard)
            .parseMode("Markdown")
            .build()
    }

    fun createDeleteMessage(chatId: Long, messageId: Int): DeleteMessage {
        return DeleteMessage.builder()
            .chatId(chatId)
            .messageId(messageId)
            .build()
    }
}