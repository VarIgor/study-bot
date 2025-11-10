package com.palastrov.study_bot.service

import com.palastrov.study_bot.entity.user.User
import com.palastrov.study_bot.repository.UserRepo
import com.palastrov.study_bot.service.handler.CallbackQueryHandler
import com.palastrov.study_bot.service.handler.CommandHandler
import com.palastrov.study_bot.service.handler.MessageHandler
import com.palastrov.study_bot.telegram.TelegramWebhookBot
import jakarta.transaction.Transactional
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
    private val callbackQueryHandler: CallbackQueryHandler,
    private val userRepository: UserRepo
) {

    fun distribute(update: Update, bot: TelegramWebhookBot): BotApiMethod<*>? {
        return when {
            update.hasCallbackQuery() -> callbackQueryHandler.answer(update.callbackQuery, bot)
            update.hasMessage() -> {
                createUser(update.message)
                handleMessage(update.message, bot)
            }
            else -> {
                log.info("Unsupported update: $update")
                null
            }
        }
    }

    private fun handleMessage(message: Message, bot: TelegramWebhookBot): BotApiMethod<*>? {
        return when {
            message.hasText() && message.text?.startsWith("/") == true ->
                commandHandler.answer(message, bot)

            else -> messageHandler.answer(message, bot)
        }
    }
    @Transactional
    private fun createUser(message: Message) {
        try {
            val existingUser = userRepository.findByChatId(message.chatId)
            if (existingUser == null) {
                val user = User(chatId = message.chatId)
                userRepository.save(user)
                log.info("Created new user: ${user.chatId}")
            }
        } catch (e: Exception) {
            log.error("Failed to create user for chatId: ${message.chatId}", e)
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(UpdateDispatcher::class.java)
    }
}