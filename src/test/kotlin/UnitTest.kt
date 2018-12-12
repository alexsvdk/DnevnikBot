import api.Requester
import com.google.gson.Gson
import objects.User

fun main(args:Array<String>){
    val user = Gson().fromJson("{\"expiresIn\":2592000,\"expiresIn_str\":\"2592000\",\"scope\":\"commoninfo,friendsandrelatives,educationalinfo\",\"accessToken\":\"648Z721Pe38cDpqAQWtX5qeHDmWKkTFS\",\"user\":1000005849983,\"refreshToken\":\"RHXvBYjrLoeMscfrPK7kWX2OZ1WUfppL\",\"user_str\":\"1000005849983\"}",User::class.java)
    user.userContent = Requester.userContent(user.accessToken)
    print(Requester.userSchedules(user.accessToken, user.userContent!!.personId.toString(), user.userContent!!.groupIds[0].toString()))
}
