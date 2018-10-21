package bot

import io.reactivex.Observable
import main.App
import objects.BotUser
import objects.Message

class BotsControler(val app: App) {

    val bots = listOf<BotModel>(VKBot())
    val states = mutableMapOf<BotUser,Int>()
    val logins = mutableMapOf<BotUser,String>()
    val passwords = mutableMapOf<BotUser,String>()

    init {

        bots.forEach { it.msgObservable.subscribe {update ->
            if (!states.contains(update.botUser)) states.put(update.botUser,0)
            println(states)
            bots.find { it.TAG==update.botUser.source }?.also { bot->

                when (states[update.botUser]){
                    -1 -> {
                        bot.sendMsg(Message(MessageGenerator.login,update.botUser))
                        states[update.botUser]=1
                    }
                    0-> {
                        bot.sendMsg(Message(MessageGenerator.hi,update.botUser))
                        bot.sendMsg(Message(MessageGenerator.login,update.botUser))
                        states[update.botUser]=1
                    }
                    1 -> {
                        if (update.msg.toLowerCase()=="отмена"){
                            bot.sendMsg(Message("Принято!",update.botUser))
                            states[update.botUser]=-1
                        }else{
                            bot.sendMsg(Message(MessageGenerator.password,update.botUser))
                            logins[update.botUser]=update.msg
                            states[update.botUser]=2
                        }
                    }
                    2 -> {
                        if (update.msg.toLowerCase()=="отмена"){
                            bot.sendMsg(Message("Принято!",update.botUser))
                            states[update.botUser]=-1
                        }else{
                            passwords[update.botUser]=update.msg
                            if (app.login(update.botUser,logins[update.botUser]!!,passwords[update.botUser]!!)){
                                bot.sendMsg(MessageGenerator.ready,update.botUser)
                                states[update.botUser]=3
                            }else{
                                bot.sendMsg(MessageGenerator.wrong,update.botUser)
                                states[update.botUser]=-1
                            }
                        }
                    }
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