package api

import khttp.get
import khttp.post

object Requester {
    val api = "https://api.school.mosreg.ru/v1/"

    fun login(username:String,password:String){
         post(api+"authorizations/bycredentials", mapOf("username" to username,"password" to password,"scope" to "Schools,Relatives,EduGroups,Lessons,Marks,EduWorks,Avatar"))
    }

    fun refresh(refreshToken:String){
         post(api+"authorizations", mapOf("grant_type" to "RefreshToken","refreshToken" to refreshToken,"scope" to "Schools,Relatives,EduGroups,Lessons,Marks,EduWorks,Avatar"))
    }

    fun userContent(accessToken:String){
         get(api+"users/me/context", mapOf("access_token" to accessToken))
    }

    fun userMarks(accessToken:String,personId:String,groupId:String){
         get(api+"persons/$personId/group/$groupId/recentmarks", mapOf("access_token" to accessToken, "limit" to "4"))
    }
}