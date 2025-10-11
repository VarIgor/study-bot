package com.palastrov.study_bot.service.handler

import com.palastrov.study_bot.service.data.BotConstants.Callback
import com.palastrov.study_bot.service.manager.FeedbackManager
import com.palastrov.study_bot.service.manager.HelpManager
import com.palastrov.study_bot.service.manager.ProgressControlManager
import com.palastrov.study_bot.service.manager.TaskManager
import com.palastrov.study_bot.service.manager.TimetableManager
import com.palastrov.study_bot.telegram.TelegramWebhookBot
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class CallbackQueryHandler(
    private val feedbackManager: FeedbackManager,
    private val helpManager: HelpManager,
    private val timetableManager: TimetableManager,
    private val taskManager: TaskManager,
    private val progressControlManager: ProgressControlManager
) {
    fun answer(callbackQuery: CallbackQuery, bot: TelegramWebhookBot): BotApiMethod<*>? {
        val callbackData = callbackQuery.data
        val keyWord = callbackData.split("_")[0]

        if (Callback.TASK.equals(keyWord)) {
            return taskManager.handleCallback(callbackQuery)
        }
        if (Callback.TIMETABLE.equals(keyWord)) {
            return timetableManager.handleCallback(callbackQuery)
        }
        if (Callback.PROGRESS.equals(keyWord)) {
            return progressControlManager.handleCallback(callbackQuery)
        }

        return when (callbackData) {
            Callback.FEEDBACK -> feedbackManager.handleCallback(callbackQuery)
            Callback.HELP -> helpManager.handleCallback(callbackQuery)
            else -> null
        }
    }
}