package cache

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import objects.BotUser
import objects.Mark
import objects.User
import java.io.File
import java.util.*

object Cache {

    private val patch = "data"
    private val gson = Gson()

    private val marksType = object : TypeToken<List<Mark>>() {}.type

    fun init(){
        File(patch+File.separator+"users").mkdirs()
        File(patch+File.separator+"botusers").mkdirs()
        File(patch+File.separator+"marks").mkdirs()
    }

    fun getUser(username:String):User? = getUser(File(patch+File.separator+"users"+File.separator+username+".json"))
    fun getUser(file: File):User? = if (file.exists()) gson.fromJson(file.reader(),User::class.java) else null

    fun getBotUser(botUser: BotUser):BotUser? = getBotUser(File(patch+File.separator+"botusers"+File.separator+botUser+".json"))
    fun getBotUser(file: File):BotUser? = if (file.exists()) gson.fromJson(file.reader(),BotUser::class.java) else null

    fun getRecentMarks(user: User):MutableList<Mark>? = getRecentMarks(File(patch+File.separator+"marks"+File.separator+user.user+".json"))
    fun getRecentMarks(file: File):MutableList<Mark> = if (file.exists()) gson.fromJson(file.reader(), marksType) else mutableListOf()

    fun saveUser(user: User){
        val file = File(patch+File.separator+"users"+File.separator+user.user+".json")
        file.writeText(gson.toJson(user))
    }

    fun saveBotUser(botUser: BotUser){
        val file = File(patch+File.separator+"botusers"+File.separator+botUser+".json")
        file.writeText(gson.toJson(botUser))
    }

    fun saveRecentMarks(marks: List<Mark>,user: User){
        val file = File(patch+File.separator+"marks"+File.separator+user.user+".json")
        file.writeText(gson.toJson(marks))
    }

    fun getUsers():MutableList<User> = File(patch+File.separator+"users").listFiles().map { getUser(it)!! }.toMutableList()
    fun getBotUsrDatas(): MutableList<BotUser> = File(patch+File.separator+"botusers").listFiles().map { getBotUser(it)!! }.toMutableList()
    fun getRecentMarksMap(): MutableMap<Long,MutableList<Mark>> = File(patch+File.separator+"marks").listFiles().map { Pair(it.nameWithoutExtension.toLong(),getRecentMarks(it)) }.toMap().toMutableMap()

}