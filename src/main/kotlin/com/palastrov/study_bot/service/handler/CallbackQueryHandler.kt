package com.palastrov.study_bot.service.handler

import com.palastrov.study_bot.service.data.BotConstants.Callback
import com.palastrov.study_bot.service.manager.FeedbackManager
import com.palastrov.study_bot.service.manager.HelpManager
import com.palastrov.study_bot.telegram.TelegramWebhookBot
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

@Service
class CallbackQueryHandler {
    fun answer(callbackQuery: CallbackQuery, bot: TelegramWebhookBot): BotApiMethod<*>? {
        val callbackData = callbackQuery.data
        return when (callbackData) {
            Callback.FEEDBACK -> FeedbackManager.handleFeedbackCallback(callbackQuery)
            Callback.HELP -> HelpManager.handleHelpCallback(callbackQuery)
            else -> null
        }
    }
}