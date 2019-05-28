package phil.homework.threadingproject.kotlinreview

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class Coroutine {

    suspend fun sampleSuspend() = withContext(Dispatchers.IO) {
        async {
            delay(1500)
            println("async returns")
        }
        delay(1000)
        println("coroutine returns")
    }

    fun runSuspendingFunctions(block: suspend () -> Unit) {
        GlobalScope.launch {
            block()
        }
    }

    fun main() {
        val startTime = System.currentTimeMillis()
        runSuspendingFunctions {
            runBlocking {
                sampleSuspend()
            }
            println("executed in ${System.currentTimeMillis() - startTime}")
        }
        Thread.sleep(2000)
    }
}