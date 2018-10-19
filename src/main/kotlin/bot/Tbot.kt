package bot

import objects.Massage
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException
import org.telegram.telegrambots.meta.updateshandlers.SentCallback
import java.lang.Exception

class Tbot :BotModel() {

    override fun sendMsg(msg: Massage){
        bot.executeAsync(SendMessage(msg.userid,msg.msg),object : SentCallback<Message>{
            override fun onResult(method: BotApiMethod<Message>?, response: Message?) {

            }

            override fun onException(method: BotApiMethod<Message>?, exception: Exception?) {

            }

            override fun onError(method: BotApiMethod<Message>?, apiException: TelegramApiRequestException?) {

            }
        })
    }

    val bot = object :TelegramLongPollingBot(){
        override fun getBotToken(): String = Config.telegramToken
        override fun getBotUsername(): String = Config.botName

        override fun onUpdateReceived(update: Update?) {
            if (update!=null&&update.hasMessage()&&update.message.hasText()&&!update.message.groupchatCreated)
                onMsgRecived(Massage(update.message.chatId.toString(),update.message.text))
        }
    }

}