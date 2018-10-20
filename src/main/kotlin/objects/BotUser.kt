package objects

data class BotUser (
        val id:Long,
        val source: String
){
    override fun toString(): String {
        return "${source}_$id"
    }
}