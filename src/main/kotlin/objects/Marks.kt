package objects

import com.google.gson.annotations.SerializedName

data class Marks(
        @SerializedName("marks") val marks: List<Mark>,
        @SerializedName("criteriabasedmarks") val criteriabasedmarks: List<Any>,
        @SerializedName("works") val works: List<Work>,
        @SerializedName("subjects") val subjects: List<Subject>,
        @SerializedName("workTypes") val workTypes: List<WorkType>,
        @SerializedName("lessons") val lessons: List<Lesson>,
        @SerializedName("lessonLogEntries") val lessonLogEntries: List<LessonLogEntry>
)