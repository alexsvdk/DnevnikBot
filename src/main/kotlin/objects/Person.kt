package objects

import com.google.gson.annotations.SerializedName

data class Person(
        @SerializedName("firstName")        val firstName: String,
        @SerializedName("id") val id: Long,
        @SerializedName("id_str") val idStr: String,
        @SerializedName("lastName") val lastName: String,
        @SerializedName("middleName") val middleName: String,
        @SerializedName("sex") val sex: String,
        @SerializedName("userId") val userId: Long,
        @SerializedName("userId_str") val userIdStr: String
)