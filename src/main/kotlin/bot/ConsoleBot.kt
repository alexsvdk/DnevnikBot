package bot

import objects.Message
import java.util.*

class ConsoleBot: BotModel() {

    override val TAG = "C"

    init {
        val scanner = Scanner(System.`in`)
        while (true)
            onMsgRecived(Message(0,scanner.nextLine(),TAG))
    }

    override fun sendMsg(msg: Message) {
        println(msg.msg)
    }

}