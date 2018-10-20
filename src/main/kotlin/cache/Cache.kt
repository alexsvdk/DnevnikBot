package cache

import com.google.gson.Gson
import objects.BotUser
import objects.BotUserData
import objects.Mark
import objects.User
import java.io.File
import java.util.*

object Cache {

    private val patch = "data"
    private val gson = Gson()

    fun init(){
        File(patch+File.separator+"users").mkdirs()
        File(patch+File.separator+"botusers").mkdirs()
        File(patch+File.separator+"marks").mkdirs()
    }

    fun getUser(username:String):User?{
        val file = File(patch+File.separator+"users"+File.separator+username+".json")
        return if (file.exists()) gson.fromJson(file.reader(),User::class.java) else null
    }

    fun getBotUserData(botUser: BotUser):BotUserData?{
        val file = File(patch+File.separator+"botusers"+File.separator+botUser+".json")
        return if (file.exists()) gson.fromJson(file.reader(),BotUserData::class.java) else null
    }

    fun getRecentMarks(user: User):List<Mark>?{
        val file = File(patch+File.separator+"marks"+File.separator+user.user+".json")
        return if (file.exists()) gson.fromJson(file.reader(),(object :LinkedList<Mark>(){})::class.java) else null
    }

    fun saveUser(user: User){
        val file = File(patch+File.separator+"users"+File.separator+user.user+".json")
        file.writeText(gson.toJson(user))
    }

    fun saveBotUserData(botUserData: BotUserData,botUser: BotUser){
        val file = File(patch+File.separator+"botusers"+File.separator+botUser+".json")
        file.writeText(gson.toJson(botUserData))
    }

    fun saveRecentMarks(marks: List<Mark>,user: User){
        val file = File(patch+File.separator+"marks"+File.separator+user.user+".json")
        file.writeText(gson.toJson(marks))
    }
}