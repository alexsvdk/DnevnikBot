package main

import api.Requester
import bot.BotsControler
import cache.Cache
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import objects.BotUser
import objects.BotUserData
import objects.Update

class App{

    private val subscribers = mutableListOf<ObservableEmitter<Update>>()
    val updates = Observable.create<Update> { subscribers.add(it) }!!
    private val botsController = BotsControler(this)
    private val users = Cache.getUsers()
    private val botusers = Cache.getBotUsrDatas()
    private val marksData = Cache.getRecentMarksMap()




    init {
        Cache.init()
        Thread{
            while (true){
                users.forEach { user ->
                    val marks = Requester.userMarks(user.accessToken,user.userContent.personId.toString(), user.userContent.groupIds[0].toString())
                    marks.marks.forEach { mark->
                        if (!marksData[user.user]!!.contains(mark)){
                            marksData[user.user]!!.add(mark)
                            //subscribers.forEach { sbs -> botusers.filter { it.users.contains(user.user) }.forEach { sbs.onNext(Update())} }
                        }
                    }


                }
                Thread.sleep(60000)
            }

        }
    }

    fun login(botUser: BotUser,username:String,password:String):Boolean{
        val user = Requester.login(username,password)
        if (user.accessToken!="null"){
            Cache.saveUser(user)
            //Cache.saveBotUserData(BotUserData(5,))
            return true
        }
        return false

    }
}