package objects

import api.Requester
import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("expiresIn") val expiresIn: Int,
        @SerializedName("expiresIn_str") val expiresInStr: String,
        @SerializedName("scope") val scope: String,
        @SerializedName("accessToken") val accessToken: String,
        @SerializedName("user") val user: Long,
        @SerializedName("refreshToken") val refreshToken: String,
        @SerializedName("user_str") val userStr: String
){
    var userContent: UserContent? = null

    override fun equals(other: Any?): Boolean {
        return (other is User && other.user==user)
    }
}