package objects

import com.google.gson.annotations.SerializedName

data class WorkType(
        @SerializedName("id") val id: Long,
        @SerializedName("schoolId") val schoolId: Long,
        @SerializedName("abbreviation") val abbreviation: String,
        @SerializedName("name") val name: String,
        @SerializedName("isFinal") val isFinal: Boolean,
        @SerializedName("isImportant") val isImportant: Boolean,
        @SerializedName("kindId") val kindId: Int,
        @SerializedName("kind") val kind: String
)