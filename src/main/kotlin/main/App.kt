package main

import api.Requester
import bot.BotsControler
import cache.Cache
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import objects.BotUser
import objects.Update

class App{

    private val subscribers = mutableListOf<ObservableEmitter<Update>>()
    val updates = Observable.create<Update> { subscribers.add(it) }!!
    private val botsController = BotsControler(this)
    private val users = Cache.getUsers()
    private val botusers = Cache.getBotUsrDatas()
    private val marksData = Cache.getRecentMarksMap()




    init {
        Thread{
            while (true){
                users.forEach { user ->
                    try {
                        if (user.userContent == null) user.userContent = Requester.userContent(user.accessToken)
                        val marks = Requester.userMarks(user.accessToken, user.userContent!!.personId.toString(), user.userContent!!.groupIds[0].toString())
                        marks.marks.forEach { mark->
                            if (marksData[user.user]==null) marksData[user.user] = mutableListOf()
                            if (!marksData[user.user]!!.contains(mark)){
                                marksData[user.user]!!.add(mark)
                                Cache.saveRecentMarks(marksData[user.user]!!,user)
                                subscribers.forEach { sbs -> sbs.onNext(Update(botusers.filter { it.accounts.contains(user.user.toString()) },mark,marks.lessons,marks.subjects)) }
                            }
                        }
                    }
                    catch (e:Exception){
                        e.printStackTrace()
                    }


                    Thread.sleep(Config.sleeptime)
                }
                Thread.sleep(Config.sleeptime)
            }

        }.start()
    }

    fun login(botUser: BotUser,username:String,password:String):Boolean{
        val user = Requester.login(username,password)
        if (user.user!=0L && user.accessToken!="null"){

            if (!users.contains(user)){
                Cache.saveUser(user)
                users.add(user)
            }

            if (!botusers.contains(botUser)) botusers.add(botUser)
            val botuserr = botusers.find { it.toString()==botUser.toString() }!!
            botuserr.accounts.add(user.user.toString())
            Cache.saveBotUser(botuserr)

            return true
        }
        return false

    }
}