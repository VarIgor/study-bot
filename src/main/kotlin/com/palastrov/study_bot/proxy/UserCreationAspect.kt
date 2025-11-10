package com.palastrov.study_bot.proxy

import com.palastrov.study_bot.entity.user.Action
import com.palastrov.study_bot.entity.user.Role
import com.palastrov.study_bot.entity.user.User
import com.palastrov.study_bot.entity.user.UserDetails
import com.palastrov.study_bot.repository.DetailsRepo
import com.palastrov.study_bot.repository.UserRepo
import jakarta.transaction.Transactional
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import java.time.LocalDateTime
import kotlin.jvm.java

@Aspect
@Component
class UserCreationAspect(
    private val userRepo: UserRepo,
    private val detailsRepo: DetailsRepo
) {
    private val log = LoggerFactory.getLogger(UserCreationAspect::class.java)

    @Pointcut("execution(* com.palastrov.study_bot.service.UpdateDispatcher.distribute(..))")
    fun distributeMethodPointcut() {
    }

    @Transactional
    @Around("distributeMethodPointcut()")
    fun distributeMethodAdvice(joinPoint: ProceedingJoinPoint): Any? {
        val telegramUser = extractTelegramUser(joinPoint.args[0] as Update) ?: return joinPoint.proceed()

        if (!userRepo.existsByChatId(telegramUser.id)) {
            createNewUser(telegramUser)
        }

        return joinPoint.proceed()
    }

    private fun extractTelegramUser(update: Update): org.telegram.telegrambots.meta.api.objects.User? {
        return when {
            update.hasMessage() -> update.message.from
            update.hasCallbackQuery() -> update.callbackQuery.from
            else -> null
        }
    }

    private fun createNewUser(telegramUser: org.telegram.telegrambots.meta.api.objects.User) {
        try {
            val newUser = User(
                chatId = telegramUser.id,
                role = Role.USER,
                action = Action.FREE
            )
            val savedUser = userRepo.save(newUser)

            val details = UserDetails(
                username = telegramUser.userName,
                firstName = telegramUser.firstName,
                lastName = telegramUser.lastName,
                registeredAt = LocalDateTime.now(),
                user = savedUser
            )
            detailsRepo.save(details)

            log.info("✅ Created new user: ${telegramUser.userName ?: telegramUser.firstName} (ID: ${telegramUser.id})")

        } catch (e: Exception) {
            log.error("❌ Failed to create user: ${telegramUser.id}", e)
        }
    }
}
