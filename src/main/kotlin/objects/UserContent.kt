package objects

import com.google.gson.annotations.SerializedName

data class UserContent(
        @SerializedName("userId") val userId: Long,
        @SerializedName("avatarUrl") val avatarUrl: String,
        @SerializedName("roles") val roles: List<String>,
        @SerializedName("children") val children: List<Any>,
        @SerializedName("schools") val schools: List<School>,
        @SerializedName("eduGroups") val eduGroups: List<EduGroup>,
        @SerializedName("splitId") val splitId: String,
        @SerializedName("personId") val personId: Long,
        @SerializedName("firstName") val firstName: String,
        @SerializedName("lastName") val lastName: String,
        @SerializedName("middleName") val middleName: String,
        @SerializedName("schoolIds") val schoolIds: List<Long>,
        @SerializedName("groupIds") val groupIds: List<Long>
)