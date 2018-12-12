package objects

import com.google.gson.annotations.SerializedName

data class Day(
        @SerializedName("date") val date: String,
        @SerializedName("homeworks") val homeworks: List<Homework>,
        @SerializedName("lessonLogEntries") val lessonLogEntries: List<Any>,
        @SerializedName("lessons") val lessons: List<Lesson>,
        @SerializedName("marks") val marks: List<Any>,
        @SerializedName("nextDate") val nextDate: String,
        @SerializedName("subjects") val subjects: List<Subject>,
        @SerializedName("teachers") val teachers: List<Teacher>,
        @SerializedName("workTypes") val workTypes: List<WorkType>,
        @SerializedName("works") val works: List<Work>
)