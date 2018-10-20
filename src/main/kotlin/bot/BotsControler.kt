package bot

import io.reactivex.Observable
import main.App
import objects.BotUser
import objects.Message

class BotsControler(app: App) {

    val bots = listOf<BotModel>(VKBot())
    val states = mutableMapOf<BotUser,Int>()
    val logins = mutableMapOf<BotUser,String>()
    val passwords = mutableMapOf<BotUser,String>()

    init {

        bots.forEach { it.msgObservable.subscribe {update ->
            if (!states.contains(update.botUser)) states.put(update.botUser,0)
            bots.find { it.TAG==update.botUser.source }?.also { bot->

                when (states[update.botUser]){
                    -1 -> {}
                    0-> {}
                    1 -> {}
                    2 -> {}
                    else -> {}
                }
            } } }

        app.updates.subscribe {
            it.botUsers.forEach {botuser->
                bots.find { it.TAG==botuser.source }?.sendMsg( Message(MessageGenerator.newMarkNotification(it.newMark),botuser) )
            }
        }
    }
}