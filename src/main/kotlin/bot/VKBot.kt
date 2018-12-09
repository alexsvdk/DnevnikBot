package bot

import com.petersamokhin.bots.sdk.callbacks.callbackapi.ExecuteCallback
import com.petersamokhin.bots.sdk.clients.Group
import com.petersamokhin.bots.sdk.objects.Message
import main.Config

class VKBot:BotModel() {
    override val TAG = "VK"
    val group = Group(Config.vkGroupId,Config.vkGroupToken)

    init {
        group.onSimpleTextMessage {
            onMsgRecived(objects.Message(it.authorId().toLong(),it.text,TAG))
        }
    }

    override fun sendMsg(msg: objects.Message) {
        Message().from(group).to(msg.botUser.id.toInt()).text(msg.msg).send(ExecuteCallback { })
    }




}