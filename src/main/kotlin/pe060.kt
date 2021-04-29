import util.primes.checkIfPrime
import util.primes.primes

// https://projecteuler.net/problem=60
// based on https://www.mathblog.dk/project-euler-60-primes-concatenate/
fun main() {
    solve()
}

// sort of bottom up solution, we build pairs of primes that are concatenable either way and
// keep adding more primes to the group
fun solve() {
    val primes = primes()
        .takeWhile { it < 20_000 } // just a guess
        .toList().map(Long::toInt)
        .toTypedArray()

    var minSum = Int.MAX_VALUE - 1

    val pairs = arrayOfNulls<HashSet<Int>>(primes.size)

    for (a in 1 until primes.size) {
        // this a (and any 4 primes bigger than a) already exceed the current minimum, so we can safely end here
        if (primes[a] * 5 >= minSum) {
            println("finished, we have reached minimum and can never find a smaller one")
            return
        }

        if (pairs[a] == null) {
            pairs[a] = getPairPartnerPrimes(primes, a)
        }

        for (b in a + 1 until primes.size) {
            // this a and b already exceed the current minimum, so we can break and continue with other a and b
            if (primes[a] + primes[b] * 4 >= minSum) {
                break
            }

            // b is > than a, so if a and b form a concatenable pair we have already seen b while checking a
            // if b does not match with a, we can ignore b and continue with the next prime
            if (!pairs[a]!!.contains(primes[b])) {
                continue
            }

            // at this point we know a and b match, so now we can search for new partners for b and store them in `pairs`
            if (pairs[b] == null) {
                pairs[b] = getPairPartnerPrimes(primes, b)
            }

            for (c in b + 1 until primes.size) {
                // this a, b and c already exceed the current minimum, so we can break and continue with other a, b, c
                if (primes[a] + primes[b] + primes[c] * 3 >= minSum) {
                    break
                }

                // c is > than a and b, so if c forms a group with a and b, we have already seen it while checking a and b
                // if c does not match with a and b, we can ignore c and continue with the next prime
                if (!pairs[a]!!.contains(primes[c])
                    || !pairs[b]!!.contains(primes[c])
                ) {
                    continue
                }

                // at this point we know a and b match (see first for loop)
                // and also a and c, and b and c match (see previous `if`),
                // so now we can search for new partners for c and store them in `pairs`
                if (pairs[c] == null) {
                    pairs[c] = getPairPartnerPrimes(primes, c)
                }

                for (d in c + 1 until primes.size) {
                    // this a, b, c and d already exceed the current minimum,
                    // so we can break and continue with other a, b, c, d
                    if (primes[a] + primes[b] + primes[c] + primes[d] * 2 >= minSum) {
                        break
                    }

                    // see above, you get the idea...
                    if (!pairs[a]!!.contains(primes[d])
                        || !pairs[b]!!.contains(primes[d])
                        || !pairs[c]!!.contains(primes[d])
                    ) {
                        continue
                    }

                    // see above, you get the idea...
                    if (pairs[d] == null) {
                        pairs[d] = getPairPartnerPrimes(primes, d)
                    }

                    for (e in d + 1 until primes.size) {
                        // this a, b, c, d and e already exceed the current minimum,
                        // so we can break and continue with other a, b, c, d, e
                        if (primes[a] + primes[b] + primes[c] + primes[d] + primes[e] >= minSum) {
                            break
                        }

                        // see above, you get the idea...
                        if (!pairs[a]!!.contains(primes[e])
                            || !pairs[b]!!.contains(primes[e])
                            || !pairs[c]!!.contains(primes[e])
                            || !pairs[d]!!.contains(primes[e])
                        ) {
                            continue
                        }

                        // we have a new minimum ! :-)
                        if (minSum > primes[a] + primes[b] + primes[c] + primes[d] + primes[e]) {
                            minSum = primes[a] + primes[b] + primes[c] + primes[d] + primes[e]
                        }

                        System.out.printf(
                            "%s + %s + %s + %s + %s = %s\n",
                            primes[a], primes[b], primes[c], primes[d], primes[e], minSum
                        )
                        break
                    }
                }
            }
        }
    }
}

// returns a hashset of all primes in given `primes` array
// that together with primes[`i`] form a new prime by concatenating in either way
// e.g.
// getPairPartnerPrimes([2, 3, 5, 7, 11, 13, 17, 19], 3) = [19]
// i = 3 means we take 4th prime out of the array (7) and get result 19 because 719 and 197 are prime
private fun getPairPartnerPrimes(primes: Array<Int>, i: Int): HashSet<Int> {
    val pairs: HashSet<Int> = HashSet()
    val p1 = primes[i]
    for (j in i + 1 until primes.size) {
        val p2 = primes[j]

        if (checkIfPrime(concat(p1, p2).toLong()) && checkIfPrime(concat(p2, p1).toLong())) {
            pairs.add(p2)
        }
    }
    return pairs
}

// concat two numbers
// concat(23, 67) => 2367
fun concat(a: Int, b: Int): Int {
    var shift = 10
    while (shift <= b) {
        shift *= 10
    }
    return a * shift + b
}