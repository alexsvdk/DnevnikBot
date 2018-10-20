package objects

import com.google.gson.annotations.SerializedName

data class Subject(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("knowledgeAreaId") val knowledgeAreaId: Int
)