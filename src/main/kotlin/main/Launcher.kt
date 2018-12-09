package main

import cache.Cache
import main.App
import org.telegram.telegrambots.ApiContextInitializer

fun main(args: Array<String>) {
    ApiContextInitializer.init()
    Cache.init()
    App()
}