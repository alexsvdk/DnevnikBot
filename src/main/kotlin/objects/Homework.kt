package objects

import com.google.gson.annotations.SerializedName

data class Homework(
        @SerializedName("createdBy") val createdBy: Long,
        @SerializedName("displayInJournal") val displayInJournal: Boolean,
        @SerializedName("eduGroup") val eduGroup: Long,
        @SerializedName("eduGroup_str") val eduGroupStr: String,
        @SerializedName("files") val files: List<Any>,
        @SerializedName("id") val id: Long,
        @SerializedName("id_str") val idStr: String,
        @SerializedName("isImportant") val isImportant: Boolean,
        @SerializedName("lesson") val lesson: Long,
        @SerializedName("lesson_str") val lessonStr: String,
        @SerializedName("markCount") val markCount: Int,
        @SerializedName("markType") val markType: String,
        @SerializedName("oneDriveLinks") val oneDriveLinks: List<Any>,
        @SerializedName("periodNumber") val periodNumber: Int,
        @SerializedName("periodType") val periodType: String,
        @SerializedName("sentDate") val sentDate: String,
        @SerializedName("status") val status: String,
        @SerializedName("subjectId") val subjectId: Long,
        @SerializedName("targetDate") val targetDate: String,
        @SerializedName("text") val text: String,
        @SerializedName("type") val type: Long,
        @SerializedName("workType") val workType: Long
)