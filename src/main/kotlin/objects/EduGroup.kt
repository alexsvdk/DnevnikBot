package objects

import com.google.gson.annotations.SerializedName

data class EduGroup(
        @SerializedName("id") val id: Long,
        @SerializedName("id_str") val idStr: String,
        @SerializedName("parentIds") val parentIds: List<Any>,
        @SerializedName("parentIds_str") val parentIdsStr: List<Any>,
        @SerializedName("type") val type: String,
        @SerializedName("name") val name: String,
        @SerializedName("fullName") val fullName: Any,
        @SerializedName("parallel") val parallel: Int,
        @SerializedName("timetable") val timetable: Long,
        @SerializedName("timetable_str") val timetableStr: String,
        @SerializedName("status") val status: String,
        @SerializedName("studyyear") val studyyear: Int,
        @SerializedName("subjects") val subjects: Any,
        @SerializedName("journaltype") val journaltype: String
)