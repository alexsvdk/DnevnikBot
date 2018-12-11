package main

import api.Requester
import bot.BotsControler
import cache.Cache
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import objects.BotUser
import objects.updates.DaylyUpdate
import objects.updates.MarkUpdate
import objects.updates.Update
import java.text.SimpleDateFormat
import java.util.*
import util.Threads.doContinuoslyAsync
import java.lang.NullPointerException

class App{

    private val subscribers = mutableListOf<ObservableEmitter<Update>>()
    val updates = Observable.create<Update> { subscribers.add(it) }!!
    private val botsController = BotsControler(this)
    private val users = Cache.getUsers()
    private val botusers = Cache.getBotUsrDatas()
    private val marksData = Cache.getRecentMarksMap()

    private val dateFormat = SimpleDateFormat("MMdd")
    private var day = dateFormat.format(Date()).toInt()

    init {

        doContinuoslyAsync {
            users.forEach { user ->
                try {
                    if (user.userContent == null) user.userContent = Requester.userContent(user.accessToken)
                    val marks = Requester.userMarks(user.accessToken, user.userContent!!.personId.toString(), user.userContent!!.groupIds[0].toString())
                    marks.marks.filter { it.date.substring(5,10).replace("-","").toInt()==day }.forEach { mark->
                        if (marksData[user.user]==null) marksData[user.user] = mutableListOf()
                        if (!marksData[user.user]!!.contains(mark)){
                            marksData[user.user]!!.add(mark)
                            Cache.saveRecentMarks(marksData[user.user]!!,user)
                            subscribers.forEach { sbs -> sbs.onNext(MarkUpdate(botusers.filter { it.accounts.contains(user.user.toString()) }, mark, marks.lessons, marks.subjects)) }
                        }
                    }
                }
                catch (e:Exception){
                    if (e is NullPointerException){
                        try {
                            val newUser = Requester.refresh(user.refreshToken)
                            users.remove(user)
                            users.add(newUser)
                            Cache.saveUser(newUser)
                        }catch (e: Exception){
                            users.remove(user)
                            e.printStackTrace()
                        }
                    }
                }
                Thread.sleep(Config.sleeptime)
            }
            Thread.sleep(Config.sleeptime)
        }

        doContinuoslyAsync {
            Thread.sleep(Config.sleeptime*30)
            day = dateFormat.format(Date()).toInt()
            marksData.map { Pair(it.key,it.value.filter {it.date.substring(5,10).replace("-","").toInt()!=day }) }.filter { it.second.isNotEmpty() }
                    .forEach{
                        val user = users.find { user -> user.user==it.first }!!
                        subscribers.forEach { sbs -> sbs.onNext(DaylyUpdate(botusers.filter { it.accounts.contains(user.user.toString()) },it.second)) }
                        it.second.forEach {mark -> marksData[it.first]!!.remove(mark) }
                        Cache.saveRecentMarks(marksData[it.first]!!,user)
                    }



        }





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