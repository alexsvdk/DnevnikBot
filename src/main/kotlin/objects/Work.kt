package objects

import com.google.gson.annotations.SerializedName

data class Work(
        @SerializedName("id") val id: Long,
        @SerializedName("id_str") val idStr: String,
        @SerializedName("type") val type: Long,
        @SerializedName("workType") val workType: Long,
        @SerializedName("markType") val markType: String,
        @SerializedName("markCount") val markCount: Int,
        @SerializedName("lesson") val lesson: Long,
        @SerializedName("lesson_str") val lessonStr: String,
        @SerializedName("displayInJournal") val displayInJournal: Boolean,
        @SerializedName("status") val status: String,
        @SerializedName("eduGroup") val eduGroup: Long,
        @SerializedName("eduGroup_str") val eduGroupStr: String,
        @SerializedName("text") val text: String,
        @SerializedName("periodNumber") val periodNumber: Int,
        @SerializedName("periodType") val periodType: String,
        @SerializedName("subjectId") val subjectId: Long,
        @SerializedName("isImportant") val isImportant: Boolean,
        @SerializedName("targetDate") val targetDate: String,
        @SerializedName("sentDate") val sentDate: Any,
        @SerializedName("createdBy") val createdBy: Long,
        @SerializedName("files") val files: Any,
        @SerializedName("oneDriveLinks") val oneDriveLinks: Any
)