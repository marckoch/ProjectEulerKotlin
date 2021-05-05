package pe073

import util.fraction.Fraction

fun main() {
    fareyNaiveStart(2, 12000)
}

fun fareyNaiveStart(a: Long, n: Long): Long {
    var count: Long = 0

    // we start with two smallest fractions : 0/1 and 1/n
    var x0 = Fraction(0L, 1)
    var x1 = Fraction(1L, n)
    //println(x0)
    //println(x1)

    var x2 = Fraction(0L, 0)
    while (x2.bottom != 1L) {
        val k = (n + x0.bottom) / x1.bottom
        x2 = Fraction(k * x1.top - x0.top, k * x1.bottom - x0.bottom)
        // println(x2)

        // check if   1/(A+1) < x2 < 1/A
        if (x2.bottom < x2.top * (a + 1)   // 1/(A+1) < x2
            && x2.top * a < x2.bottom      // x2 < 1/A
        ) {
            //println("$x2 matches")
            count++
        }

        if (x2.bottom < x2.top * a) { // 1/(A) < x2
            break
        }
        x0 = x1
        x1 = x2
    }
    println(count)
    return count
}