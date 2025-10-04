package com.palastrov.study_bot.service.data

object BotConstants {
    // Telegram bot commands
    object Command {
        const val START = "/start"
        const val FEEDBACK = "/feedback"
        const val HELP = "/help"
        const val SCHEDULE = "/schedule"  // future option
        const val HOMEWORK = "/homework"  // future option
    }

    // Data for callback buttons
    object Callback {
        const val FEEDBACK = "feedback"
        const val HELP = "help"
    }
}