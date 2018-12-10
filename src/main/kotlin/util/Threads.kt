package util

object Threads {

    fun doAsync(exec: () -> Unit){
        Thread{ exec() }.start()
    }

    fun doContinuosly(exec: () -> Unit){
        while (true) exec()
    }

    fun doContinuoslyAsync(exec: () -> Unit){
        doAsync {
            while (true) exec()
        }
    }


}