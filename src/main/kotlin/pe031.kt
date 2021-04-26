package pe031

// https://www.educative.io/edpresso/what-is-coins-sums
// coin sums
// https://projecteuler.net/problem=31
fun main() {
    bruteForce()

    val numbers = listOf(1, 2, 5, 10, 20, 50, 100, 200)
    println(count(numbers, numbers.size, 200))
    println(countTable(numbers, numbers.size, 200))
    println(coinSumMatrix(numbers, numbers.size, 200))

}

fun bruteForce() {
    val start = 200
    var ways = 0

    for (a in start downTo 0 step 200) {
        for (b in a downTo 0 step 100) {
            for (c in b downTo 0 step 50) {
                for (d in c downTo 0 step 20) {
                    for (e in d downTo 0 step 10) {
                        for (f in e downTo 0 step 5) {
                            for (g in f downTo 0 step 2) {
                                ways++
                            }
                        }
                    }
                }
            }
        }
    }
    println(ways)
}

// https://www.geeksforgeeks.org/coin-change-dp-7/
fun count(numbers: List<Int>, m: Int, targetSum: Int): Int {
    // If n is 0 then there is 1 solution (do not include any coin)
    if (targetSum == 0)
        return 1

    // If n is less than 0 then no solution exists
    if (targetSum < 0)
        return 0

    // If there are no coins and n is greater than 0, then no solution exist
    if (m <= 0 && targetSum >= 1)
        return 0

    // count is sum of solutions (i)
    // including S[m-1] (ii) excluding S[m-1]
    return count(numbers, m - 1, targetSum) +
            count(numbers, m, targetSum - numbers[m - 1]);
}

fun countTable(numbers: List<Int>, m: Int, n: Int): Int {
    // table[i] will be storing the number of solutions for value i.
    // We need n+1 rows as the table is constructed in bottom up manner using the base case (n = 0)
    val table = IntArray(n + 1)

    // Base case (If given value is 0)
    table[0] = 1;

    // Pick all coins one by one and update the table[] values after
    // the index greater than or equal to the value of the picked coin
    for (i in (0 until m)) {
        for (j in numbers[i]..n) {
            table[j] += table[j - numbers[i]]
        }
    }
    return table[n];
}

// https://www.educative.io/edpresso/what-is-coins-sums
fun coinSumMatrix(coins: List<Int>, N: Int, T: Int): Int {
    // We build a table with size N x T + 1
    // + 1 since we add a column for the total sum of 0
    val table = Array(N) { IntArray(T + 1) }

    // Filling the entries for T = 0 case
    // Only 1 way for having a total of 0
    for (i in 0 until N)
        table[i][0] = 1

    // Fill rest of the table entries
    for (i in 0 until N) { // for each row (available coins)
        for (j in 1 until T + 1) { // for each column (total sums)
            // Number of ways with the coin: coins[j]
            val withNewCoin = if (j - coins[i] >= 0) table[i][j - coins[i]] else 0

            // Number of ways without the coin: coins[j]
            val withoutNewCoin = if (i >= 1) table[i - 1][j] else 0

            // total number of ways
            table[i][j] = withNewCoin + withoutNewCoin;
        }
    }
    // The last entry is the answer
    return table[N - 1][T];
}