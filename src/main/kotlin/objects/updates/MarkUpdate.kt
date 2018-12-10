package objects.updates

import objects.BotUser
import objects.Lesson
import objects.Mark
import objects.Subject

data class MarkUpdate(
        override val botUsers: List<BotUser>,
        val newMark: Mark,
        val lessons: List<Lesson>,
        val subjects: List<Subject>
) : Update()