package com.palastrov.study_bot.service.manager

import com.palastrov.study_bot.service.data.BotConstants.Callback
import com.palastrov.study_bot.service.factory.KeyboardFactory
import com.palastrov.study_bot.service.factory.TelegramMessageFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
@Component
class TimetableManager(
    private val telegramMessageFactory: TelegramMessageFactory,
    private val keyboardFactory: KeyboardFactory
) : Manager {

    private val mainMenuText = """
         📆 Здесь вы можете управлять вашим расписанием.
    """.trimIndent()

    override fun handleCommand(message: Message): BotApiMethod<*>? {
        return mainMenu(message)
    }

    override fun handleCallback(callbackQuery: CallbackQuery): BotApiMethod<*>? {
        return when (callbackQuery.data) {
            Callback.TIMETABLE -> mainMenu(callbackQuery)
            Callback.TIMETABLE_SHOW -> show(callbackQuery)
            Callback.TIMETABLE_REMOVE -> remove(callbackQuery)
            Callback.TIMETABLE_ADD -> add(callbackQuery)
            else -> null
        }
    }

    private fun show(callbackQuery: CallbackQuery): BotApiMethod<*>? {
        return telegramMessageFactory.createEditMessage(
            callbackQuery,
            """
                📆 Выберете день недели
            """.trimIndent(),
            keyboardFactory.createInlineKeyboard(
                mutableListOf("Назад"),
                mutableListOf(1),
                mutableListOf(Callback.TIMETABLE)
            )
        )
    }

    private fun remove(callbackQuery: CallbackQuery): BotApiMethod<*>? {
        return telegramMessageFactory.createEditMessage(
            callbackQuery,
            """
                📆 Выберете занятие, которое хотите удалить из вашего расписания
            """.trimIndent(),
            keyboardFactory.createInlineKeyboard(
                mutableListOf("Назад"),
                mutableListOf(1),
                mutableListOf(Callback.TIMETABLE)
            )
        )
    }
    private fun add(callbackQuery: CallbackQuery): BotApiMethod<*>? {
        return telegramMessageFactory.createEditMessage(
            callbackQuery,
            """
                📆 Выберете день, в который хотите добавить занятие.
            """.trimIndent(),
            keyboardFactory.createInlineKeyboard(
                mutableListOf("Назад"),
                mutableListOf(1),
                mutableListOf(Callback.TIMETABLE)
            )
        )
    }

    private fun mainMenu(message: Message): BotApiMethod<*>? {
        return telegramMessageFactory.createTextMessage(
            message.chatId,
            mainMenuText,
            keyboardFactory.createInlineKeyboard(
                mutableListOf(
                    "Показать мое расписание",
                    "Удалить занятие",
                    "Добавить занятие"
                ), // todo remove hardcode
                mutableListOf(1, 2),
                mutableListOf(Callback.TIMETABLE_SHOW, Callback.TIMETABLE_REMOVE, Callback.TIMETABLE_ADD)
            )
        )
    }

    private fun mainMenu(callbackQuery: CallbackQuery): BotApiMethod<*>? {
        return telegramMessageFactory.createEditMessage(
            callbackQuery,
            mainMenuText,
            keyboardFactory.createInlineKeyboard(
                mutableListOf(
                    "Показать мое расписание",
                    "Удалить занятие",
                    "Добавить занятие"
                ), // todo remove hardcode
                mutableListOf(1, 2),
                mutableListOf(Callback.TIMETABLE_SHOW, Callback.TIMETABLE_REMOVE, Callback.TIMETABLE_ADD)
            )
        )
    }
}