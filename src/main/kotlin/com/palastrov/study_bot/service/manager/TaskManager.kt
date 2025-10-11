package com.palastrov.study_bot.service.manager

import com.palastrov.study_bot.service.data.BotConstants.Callback
import com.palastrov.study_bot.service.factory.KeyboardFactory
import com.palastrov.study_bot.service.factory.TelegramMessageFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
@Component
class TaskManager(
    private val telegramMessageFactory: TelegramMessageFactory,
    private val keyboardFactory: KeyboardFactory
) : Manager {

    private val mainMenuText = "\uD83D\uDDC2 Вы можете добавить домашнее задание вашему ученику".trimIndent()
    private val createMenuText = "👤 Выберете ученика, которому хотите дать домашнее задание".trimIndent()

    override fun handleCommand(message: Message): BotApiMethod<*>? {
        return mainMenu(message)
    }

    override fun handleCallback(callbackQuery: CallbackQuery): BotApiMethod<*>? {
        return when (callbackQuery.data) {
            Callback.TASK -> mainMenu(callbackQuery)
            Callback.TASK_CREATE -> create(callbackQuery)
            else -> null
        }
    }

    private fun create(callbackQuery: CallbackQuery): BotApiMethod<*>? {
        return telegramMessageFactory.createEditMessage(
            callbackQuery,
            createMenuText,
            keyboardFactory.createInlineKeyboard(
                mutableListOf("Назад"),
                mutableListOf(1),
                mutableListOf(Callback.TASK)
            )
        )
    }

    private fun mainMenu(message: Message): BotApiMethod<*>? {
        return telegramMessageFactory.createTextMessage(
            message.chatId,
            mainMenuText,
            keyboardFactory.createInlineKeyboard(
                mutableListOf(
                    "Прикрепить домашнее задание"
                ), // todo remove hardcode
                mutableListOf(1),
                mutableListOf(Callback.TASK_CREATE)
            )
        )
    }

    private fun mainMenu(callbackQuery: CallbackQuery): BotApiMethod<*>? {
        return telegramMessageFactory.createEditMessage(
            callbackQuery,
            mainMenuText,
            keyboardFactory.createInlineKeyboard(
                mutableListOf(
                    "Прикрепить домашнее задание"
                ), // todo remove hardcode
                mutableListOf(1),
                mutableListOf(Callback.TASK_CREATE)
            )
        )
    }
}