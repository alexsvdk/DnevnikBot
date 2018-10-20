package objects

import com.google.gson.annotations.SerializedName

data class LessonLogEntry(
        @SerializedName("person") val person: Long,
        @SerializedName("lesson") val lesson: Long,
        @SerializedName("person_str") val personStr: String,
        @SerializedName("lesson_str") val lessonStr: String,
        @SerializedName("comment") val comment: Any,
        @SerializedName("status") val status: String,
        @SerializedName("createdDate") val createdDate: String
)