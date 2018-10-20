package objects

import com.google.gson.annotations.SerializedName

data class School(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("type") val type: String,
        @SerializedName("groupIds") val groupIds: List<Long>
)