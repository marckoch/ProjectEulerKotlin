package util

import kotlin.system.measureTimeMillis

// utility function to memoize other functions

// https://jorgecastillo.dev/kotlin-purity-and-function-memoization
fun <T, R> ((T) -> R).memoize(): (T) -> R = Memoize1(this)

class Memoize1<in T, out R>(val f: (T) -> R) : (T) -> R {
    private val values = mutableMapOf<T, R>()
    override fun invoke(x: T): R {
        return values.getOrPut(x) { f(x) }
    }
}

fun main() {
    val n = 40L

    val result: Long
    val millis = measureTimeMillis { result = fibonacciRec(n) }
    println("result = $result, execution took $millis ms")

    val resultMemoized: Long
    val millisMemoized = measureTimeMillis { resultMemoized = fibMemo(n) }
    println("result = $resultMemoized, execution memoized took $millisMemoized ms")
}

fun fibonacciRec(n: Long): Long {
    return when {
        n == 0L -> 0
        n == 1L -> 1
        n > 1L -> fibonacciRec(n - 1) + fibonacciRec(n - 2)
        else -> throw IllegalStateException("cannot calculate factorial of $n")
    }
}

val fibMemo = { n: Long -> fibMemo__(n) }.memoize()

fun fibMemo__(n: Long): Long {
    return when {
        n == 0L -> 0
        n == 1L -> 1
        n > 1L -> fibMemo(n - 1) + fibMemo(n - 2) // call memoized function !!
        else -> throw IllegalStateException("cannot calculate factorial of $n")
    }
}