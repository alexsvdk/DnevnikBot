package objects.updates

import objects.BotUser
import objects.Day

class SchadulesUpdate (
        override val botUsers: List<BotUser>,
        val day: Day
): Update()