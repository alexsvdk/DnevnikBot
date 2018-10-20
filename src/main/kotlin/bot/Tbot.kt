package bot

import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException
import org.telegram.telegrambots.meta.updateshandlers.SentCallback
import java.lang.Exception


class Tbot :BotModel() {

    override val TAG = "TG"

    val bot = object :TelegramLongPollingBot(){
        override fun getBotToken(): String = Config.telegramToken
        override fun getBotUsername(): String = Config.botName

        override fun onUpdateReceived(update: Update?) {
            if (update!=null&&update.hasMessage()&&update.message.hasText()&&!update.message.groupchatCreated)
                onMsgRecived(objects.Message(update.message.chatId!!,update.message.text,TAG))
        }
    }

    init {
        ApiContextInitializer.init()
        TelegramBotsApi().registerBot(bot)
    }

    override fun sendMsg(msg: objects.Message){
        bot.executeAsync(SendMessage(msg.botUser.id,msg.msg),object : SentCallback<Message>{
            override fun onResult(method: BotApiMethod<Message>?, response: Message?) {

            }

            override fun onException(method: BotApiMethod<Message>?, exception: Exception?) {

            }

            override fun onError(method: BotApiMethod<Message>?, apiException: TelegramApiRequestException?) {

            }
        })
    }


}