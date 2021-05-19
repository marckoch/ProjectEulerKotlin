package pe095

import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=95
fun main() {
    solveProjectEuler(1_000_000)
}

private var sumOfDivisors: IntArray = IntArray(1)
private val chains: MutableMap<Int, List<Int>> = HashMap()
private val cycles: MutableList<List<Int>> = ArrayList()

fun solveProjectEuler(limit: Int) {
    val time = measureTimeMillis { init(limit) }
    println("init took $time")

//    println(buildChain(120))
//    println(buildChain(220))
//    println(buildChain(284))
//    println(buildChain(1184))
//    println(buildChain(12496))
//    println(buildChain(17384))
    for (n in 0..limit) {
        val chain = buildChain(n)
        // println("$n: $chain")
        chains[n] = chain
    }

    val validChains = chains.values.filter { i -> i.isNotEmpty() && i[i.size - 1] > 1 }
    // println(validChains)

    val validChainsByLength = validChains.groupBy { list -> list.size }
    // println(validChainsByLength)

    val maxLength = validChainsByLength.keys.max()
    println("max validChainsByLength=$maxLength")

    val maxValidChains = validChainsByLength[maxLength]!!
    println(maxValidChains.size.toString() + " chains of maxlength found: " + maxValidChains)

    val minOfLongestValidChain = maxValidChains[0].min()
    println("min of longest valid chain=$minOfLongestValidChain")

    // TODO: we detect each cycle multiple times, once for each number :-(
    val cyclesByLength = cycles.groupBy { obj -> obj.size }
    println(cyclesByLength)

    val maxCycleLength = cyclesByLength.keys.max()
    println(maxCycleLength)

    val cyclesWithMaxLength = cyclesByLength[maxCycleLength]!!
    println("${cyclesWithMaxLength.size} cycles with max length of $maxCycleLength");

    // max cycle has length 28 and occurs 28 times, we just take the first one
    println(cyclesWithMaxLength[0].min())
}

fun buildChain(N: Int): List<Int> {
    var n = N
    val chain: MutableList<Int> = ArrayList()

    // exclude primes >> empty chain
    if (sumOfDivisors[n] == 1) {
        return chain
    }
    chain.add(n)
    while (n > 1) {
        if (n > sumOfDivisors.size) {
            chain.add(-1)
            return chain
        }
        n = sumOfDivisors[n]
        if (chain[0] == n) { // we have a cycle
            cycles.add(chain)
            break
        } else if (chain.contains(n)) { // we have a 'normal' chain
            break
        } else {
            chain.add(n)
        }
    }
    return chain
}

fun init(limit: Int) {
    sumOfDivisors = IntArray(limit + 1)
    for (i in 0 until limit) {
        //sumOfDivisors[i] = getProperDivisorsOf(i.toLong()).sum().toInt() // very slow! 11 sek
        sumOfDivisors[i] = getSumOfDivisors(i) // faster! 2,3 sek!!
    }
}

fun getSumOfDivisors(n: Int): Int {
    if (n == 1) {
        return 0
    }

    var sum = 0
    for (i in 1..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) {
            sum += i
            if (i > 1 && i != n / i) {
                sum += n / i
            }
        }
    }
    return sum
}

// a bit slower than the other one: 4 seks
fun getSumOfDivisors2(n: Int): Int {
    if (n == 1) {
        return 0
    }

    return (1..sqrt(n.toDouble()).toInt())
        .filter { n % it == 0 }
        .sumOf { if (it > 1 && it != n / it) it + n / it else it }
}