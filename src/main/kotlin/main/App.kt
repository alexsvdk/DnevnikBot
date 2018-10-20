package main

import bot.BotsControler
import cache.Cache
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import objects.Update

class App{

    private val botsController = BotsControler(this)
    private val subscribers = mutableListOf<ObservableEmitter<Update>>()
    val updates = Observable.create<Update> { subscribers.add(it) }!!

    init {
        Cache.init()
    }

    fun login(username:String,password:String){

    }
}