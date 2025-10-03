package com.palastrov.study_bot.service.factory

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

@Component
class KeyboardFactory {

    fun getInlineKeyboard(
        text: List<String>,
        configuration: List<Int>,
        data: List<String>
    ): InlineKeyboardMarkup {

        val keyboard = mutableListOf<List<InlineKeyboardButton>>()
        var index = 0

        for (rowNumber in configuration) {
            val row = mutableListOf<InlineKeyboardButton>()
            for (i in 0 until rowNumber) {
                val button = InlineKeyboardButton.builder()
                    .text(text[index])
                    .callbackData(data[index])
                    .build()
                row.add(button)
                index++
            }
            keyboard.add(row)
        }

        return InlineKeyboardMarkup.builder()
            .keyboard(keyboard)
            .build()
    }


    fun getReplyKeyboard(
        text: List<String>,
        configuration: List<Int>
    ): ReplyKeyboardMarkup {
        val keyboard = mutableListOf<KeyboardRow>()
        var index = 0

        for (rowNumber in configuration) {
            val row = KeyboardRow()
            for (i in 0 until rowNumber) {
                val button = KeyboardButton.builder()
                    .text(text[index])
                    .build()
                row.add(button)
                index++
            }
            keyboard.add(row)
        }

        return ReplyKeyboardMarkup.builder()
            .keyboard(keyboard)
            .resizeKeyboard(true)
            .build()
    }
}
//todo Move the keyboard creation function to the KeyboardFactoryUtils object