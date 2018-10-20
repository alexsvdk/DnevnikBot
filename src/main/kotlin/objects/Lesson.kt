package objects

import com.google.gson.annotations.SerializedName

data class Lesson(
        @SerializedName("id") val id: Long,
        @SerializedName("title") val title: String,
        @SerializedName("date") val date: String,
        @SerializedName("number") val number: Int,
        @SerializedName("subjectId") val subjectId: Long,
        @SerializedName("status") val status: String,
        @SerializedName("resultPlaceId") val resultPlaceId: Any,
        @SerializedName("building") val building: Any,
        @SerializedName("place") val place: Any,
        @SerializedName("floor") val floor: Any,
        @SerializedName("hours") val hours: String,
        @SerializedName("works") val works: List<Long>,
        @SerializedName("teachers") val teachers: List<Long>
)