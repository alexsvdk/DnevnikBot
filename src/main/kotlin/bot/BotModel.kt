package bot

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import objects.BotUser
import objects.Message

abstract class BotModel {

    private val subsribers = mutableListOf<ObservableEmitter<Message>>()
    val msgObservable: Observable<Message> = Observable.create { subsribers.add(it) }

    abstract fun sendMsg(msg:Message)
    abstract val TAG:String

    fun onMsgRecived(msg:Message){subsribers.forEach { it.onNext(msg) }}
    fun sendMsg(msg: String, botUser: BotUser) {
        sendMsg(Message(msg,botUser))
    }


}