package objects

data class BotUser (
        val id:Long,
        val source: String,
        val accounts: MutableList<String> = mutableListOf<String>()
){
    override fun toString(): String {
        return "${source}_$id"
    }
}