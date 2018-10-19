package bot

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import objects.Massage

abstract class BotModel {

    private val subsribers = mutableListOf<ObservableEmitter<Massage>>()
    val msgObservable: Observable<Massage> = Observable.create { subsribers.add(it) }

    abstract fun sendMsg(msg:Massage)

    fun onMsgRecived(msg:Massage){subsribers.forEach { it.onNext(msg) }}

}