package com.palastrov.study_bot.service.manager

import com.palastrov.study_bot.service.data.BotConstants.Callback
import com.palastrov.study_bot.service.factory.KeyboardFactory
import com.palastrov.study_bot.service.factory.TelegramMessageFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message

@Component
class ProgressControlManager(
    private val telegramMessageFactory: TelegramMessageFactory,
    private val keyboardFactory: KeyboardFactory
) : Manager {

    private val mainText = """
        Здесь вы можете увидеть
    """.trimIndent()

    override fun handleCommand(message: Message): BotApiMethod<*>? {
        return mainMenu(message)
    }

    override fun handleCallback(callbackQuery: CallbackQuery): BotApiMethod<*>? {
        val callbackData = callbackQuery.data
        return when (callbackData){
            Callback.PROGRESS -> mainMenu(callbackQuery)
            Callback.PROGRESS_STAT -> stat(callbackQuery)
            else -> null
        }
    }

    private fun mainMenu(callbackQuery: CallbackQuery): BotApiMethod<*>? {
        return telegramMessageFactory.createEditMessage(
            callbackQuery,
            mainText,
            keyboardFactory.createInlineKeyboard(
                mutableListOf("Статистика успеваемости"),
                mutableListOf(1),
                mutableListOf(Callback.PROGRESS_STAT)
            )
        )
    }

    private fun mainMenu(message: Message): BotApiMethod<*>? {
        return telegramMessageFactory.createTextMessage(
            message.chatId,
            mainText,
            keyboardFactory.createInlineKeyboard(
                mutableListOf("Статистика успеваемости"),
                mutableListOf(1),
                mutableListOf(Callback.PROGRESS_STAT)
            )
        )
    }

    private fun stat(callbackQuery: CallbackQuery): BotApiMethod<*>?{
        return telegramMessageFactory.createEditMessage(
            callbackQuery,
            "Здесь будет статистика",
            keyboardFactory.createInlineKeyboard(
                mutableListOf("Назад"),
                mutableListOf(1),
                mutableListOf(Callback.PROGRESS)
            )
        )
    }
}