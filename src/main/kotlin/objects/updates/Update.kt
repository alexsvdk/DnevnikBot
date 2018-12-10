package objects.updates

import objects.BotUser
import objects.Lesson
import objects.Mark
import objects.Subject

abstract class Update {
    abstract val botUsers: List<BotUser>
}

