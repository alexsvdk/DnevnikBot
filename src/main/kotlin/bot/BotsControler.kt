package bot

import main.App
import objects.BotUser
import objects.Message

class BotsControler(val app: App) {

    val bots = listOf(ConsoleBot())
    val states = mutableMapOf<String,Int>()
    val logins = mutableMapOf<BotUser,String>()
    val passwords = mutableMapOf<BotUser,String>()

    init {

        bots.forEach { it.msgObservable.subscribe {update ->
            if (!states.contains(update.botUser.toString())) states.put(update.botUser.toString(),0)
            bots.find { it.TAG==update.botUser.source }?.also { bot->

                when (states[update.botUser.toString()]){
                    -1 -> {
                        bot.sendMsg(Message(MessageGenerator.login,update.botUser))
                        states[update.botUser.toString()]=1
                    }
                    0-> {
                        bot.sendMsg(Message(MessageGenerator.hi,update.botUser))
                        bot.sendMsg(Message(MessageGenerator.login,update.botUser))
                        states[update.botUser.toString()]=1
                    }
                    1 -> {
                        if (update.msg.toLowerCase()=="отмена"){
                            bot.sendMsg(Message("Принято!",update.botUser))
                            states[update.botUser.toString()]=-1
                        }else{
                            bot.sendMsg(Message(MessageGenerator.password,update.botUser))
                            logins[update.botUser]=update.msg
                            states[update.botUser.toString()]=2
                        }
                    }
                    2 -> {
                        if (update.msg.toLowerCase()=="отмена"){
                            bot.sendMsg(Message("Принято!",update.botUser))
                            states[update.botUser.toString()]=-1
                        }else{
                            passwords[update.botUser]=update.msg
                            if (app.login(update.botUser,logins[update.botUser]!!,passwords[update.botUser]!!)){
                                bot.sendMsg(MessageGenerator.ready,update.botUser)
                                states[update.botUser.toString()]=3
                            }else{
                                bot.sendMsg(MessageGenerator.wrong,update.botUser)
                                states[update.botUser.toString()]=-1
                            }
                        }
                    }
                    else -> {}
                }
            }
        } }

        app.updates.subscribe {
            it.botUsers.forEach {botuser->
                println(MessageGenerator.newMarkNotification(it.newMark,it.lessons, it.subjects))
                bots.find { it.TAG==botuser.source }?.sendMsg( Message(MessageGenerator.newMarkNotification(it.newMark,it.lessons, it.subjects),botuser) )
            }
        }
    }
}