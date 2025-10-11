package com.palastrov.study_bot.service.data

object BotConstants {
    // Telegram bot commands
    object Command {
        const val START = "/start"
        const val FEEDBACK = "/feedback"
        const val HELP = "/help"
        const val SCHEDULE = "/schedule"  // future option
        const val HOMEWORK = "/homework"  // future option
        const val TIMETABLE = "/timetable"
        const val TASK = "/task"
        const val PROGRESS = "/progress"
    }

    // Data for callback buttons
    object Callback {
        const val FEEDBACK = "feedback"
        const val HELP = "help"
        const val TIMETABLE = "timetable"
        const val TIMETABLE_SHOW = "timetable_show"
        const val TIMETABLE_ADD = "timetable_add"
        const val TIMETABLE_REMOVE = "timetable_remove"

        const val TASK = "task"
        const val TASK_CREATE = "task_create"


        const val PROGRESS = "progress"
        const val PROGRESS_STAT = "progress_stat"




    }
}