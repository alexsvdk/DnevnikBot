package objects

data class Message (
        val msg: String,
        val botUser: BotUser
){
    constructor(userid: Long , msg:String , source:String):this(msg,BotUser(userid,source))
}