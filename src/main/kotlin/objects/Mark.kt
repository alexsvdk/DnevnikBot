package objects

import com.google.gson.annotations.SerializedName

data class Mark(
        @SerializedName("id") val id: Long,
        @SerializedName("id_str") val idStr: String,
        @SerializedName("type") val type: String,
        @SerializedName("value") val value: String,
        @SerializedName("textValue") val textValue: String,
        @SerializedName("person") val person: Long,
        @SerializedName("person_str") val personStr: String,
        @SerializedName("work") val work: Long,
        @SerializedName("work_str") val workStr: String,
        @SerializedName("lesson") val lesson: Long,
        @SerializedName("lesson_str") val lessonStr: String,
        @SerializedName("number") val number: Int,
        @SerializedName("date") val date: String,
        @SerializedName("workType") val workType: Long,
        @SerializedName("mood") val mood: String,
        @SerializedName("use_avg_calc") val useAvgCalc: Boolean
)