package bot

import main.App
import objects.BotUser
import objects.Message
import objects.updates.DaylyUpdate
import objects.updates.MarkUpdate
import util.MessageGenerator

class BotsControler(val app: App) {

    val bots = listOf(VKBot(),Tbot())
    val states = mutableMapOf<String,Int>()
    val logins = mutableMapOf<BotUser,String>()
    val passwords = mutableMapOf<BotUser,String>()

    fun sendMessage(message: Message){
        bots.find { it.TAG==message.botUser.source }?.sendMsg(message)
    }

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

        app.updates.subscribe { update ->
            when{
                (update is MarkUpdate) ->
                    update.botUsers.forEach { sendMessage(Message(MessageGenerator.newMark(update),it)) }


                (update is DaylyUpdate) ->
                    update.botUsers.forEach { sendMessage(Message(MessageGenerator.daylyNotification(update),it)) }


                else -> {

                }
            }

        }
    }



}