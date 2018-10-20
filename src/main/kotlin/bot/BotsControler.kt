package bot

import io.reactivex.Observable
import objects.Message
import objects.Update

class BotsControler(updates: Observable<Update>) {

    val bots = listOf<BotModel>(VKBot())

    init {

        bots.forEach { it.msgObservable.subscribe {

        } }

        updates.subscribe {
            it.botUsers.forEach {botuser->
                bots.find { it.TAG==botuser.source }?.sendMsg( Message(MessageGenerator.newMarkNotification(it.newMark),botuser) )
            }
        }
    }
}