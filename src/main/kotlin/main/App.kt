package main

import api.Requester
import bot.BotsControler
import cache.Cache
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import objects.Update

class App{

    private val subscribers = mutableListOf<ObservableEmitter<Update>>()
    val updates = Observable.create<Update> { subscribers.add(it) }!!
    private val botsController = BotsControler(this)



    init {
        Cache.init()
    }

    fun login(username:String,password:String){
        println(Requester.login(username,password))
    }
}