package objects

data class Update (
        val botUsers: List<BotUser>,
        val newMark: Mark,
        val lessons: List<Lesson>,
        val subjects: List<Subject>
)