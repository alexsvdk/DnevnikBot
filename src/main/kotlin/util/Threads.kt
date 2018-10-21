package util

object Threads {

    fun doAsync(exec: () -> Unit){
        Thread{ exec() }.start()
    }
}