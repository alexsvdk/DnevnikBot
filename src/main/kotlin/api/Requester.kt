package api

import com.google.gson.Gson
import khttp.get
import khttp.post
import main.Config
import objects.Marks
import objects.Schedulse
import objects.User
import objects.UserContent
import org.json.JSONObject

object Requester {

    val api = "https://api.school.mosreg.ru/v1/"
    val gson = Gson()

    fun login(username:String,password:String): User {
         return gson.fromJson(post(api+"authorizations/bycredentials", json = mapOf("username" to username,"password" to password,"scope" to "Schools,Relatives,EduGroups,Lessons,Marks,EduWorks,Avatar").toJson()).text,User::class.java)
    }

    fun refresh(refreshToken:String):User{
         return gson.fromJson(post(api+"authorizations",json = mapOf("grant_type" to "RefreshToken","refreshToken" to refreshToken,"scope" to "Schools,Relatives,EduGroups,Lessons,Marks,EduWorks,Avatar").toJson()).text,User::class.java)
    }

    fun userContent(accessToken:String):UserContent{
         return gson.fromJson(get(api+"users/me/context", params = mapOf("access_token" to accessToken)).text,UserContent::class.java)
    }

    fun userMarks(accessToken:String,personId:String,groupId:String):Marks{
         return gson.fromJson(get(api+"persons/$personId/group/$groupId/recentmarks", params = mapOf("access_token" to accessToken, "limit" to "4")).text,Marks::class.java)
    }

    fun userSchedules(accessToken:String, personId:String, groupId:String,startDate:String,endDate: String = startDate):Schedulse{
        return gson.fromJson(get(api+"persons/$personId/groups/$groupId/schedules", params = mapOf("access_token" to accessToken, "startDate" to "${startDate}T00:00:00", "endDate" to "${endDate}T23:59:59")).text,Schedulse::class.java)
    }
}

fun Map<String,String>.toJson():JSONObject{
    val json = JSONObject()
    json.put("client_id", Config.client_id)
    json.put("client_secret",Config.client_secret)
    forEach { k, v -> json.put(k,v) }
    return json
}