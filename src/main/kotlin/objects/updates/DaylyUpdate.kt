package objects.updates

import objects.BotUser
import objects.Mark

class DaylyUpdate(
        override val botUsers: List<BotUser>,
        val marks: List<Mark>
): Update()