package objects

import com.google.gson.annotations.SerializedName

data class Teacher(
        @SerializedName("person") val person: Person,
        @SerializedName("role") val role: String
)