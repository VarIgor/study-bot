package com.palastrov.study_bot.service

import com.palastrov.study_bot.service.handler.CallbackQueryHandler
import com.palastrov.study_bot.service.handler.CommandHandler
import com.palastrov.study_bot.service.handler.MessageHandler
import com.palastrov.study_bot.telegram.MyTelegramWebhookBot
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Service
@Slf4j
class UpdateDispatcher(
    private val messageHandler: MessageHandler,
    private val commandHandler: CommandHandler,
    private val callbackQueryHandler: CallbackQueryHandler
) {

    fun distribute(update: Update, bot: MyTelegramWebhookBot): BotApiMethod<*>? {
        return when {
            update.hasCallbackQuery() -> callbackQueryHandler.answer(update.callbackQuery, bot)
            update.hasMessage() -> handleMessage(update.message, bot)
            else -> {
                log.info("Unsupported update: $update")
                null
            }
        }
    }

    private fun handleMessage(message: Message, bot: MyTelegramWebhookBot): BotApiMethod<*>? {
        return when {
            message.hasText() && message.text?.startsWith("/") == true ->
                commandHandler.answer(message, bot)

            else -> messageHandler.answer(message, bot)
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(UpdateDispatcher::class.java)
    }
}