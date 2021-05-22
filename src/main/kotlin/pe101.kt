package pe101

// https://projecteuler.net/problem=101
fun main() {
    val coefficients = intArrayOf(1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1)
    val p = Polynom(coefficients)
    println(p)

    val fits = (1..p.degree).sumOf { n ->
        val fit = lagrange(p, n)
        println("$n fit=$fit")
        fit
    }
    println("fits=$fits")
}

// http://mathworld.wolfram.com/LagrangeInterpolatingPolynomial.html
fun lagrange(p: Polynom, x: Int): Long {
    var P_x: Long = 0
    for (j in 1..x) {
        var top: Long = 1
        var bottom: Long = 1
        for (k in 1..x) {
            if (k != j) {
                top *= (x + 1 - k).toLong()
                bottom *= (j - k).toLong()
            }
        }
        val yJ = p.evaluate(j.toLong())
        P_x += top * yJ / bottom
    }
    return P_x

    // or functional (no vars):
//    return (1..x).sumOf { j ->
//        (1..x)
//            .map { k -> Fraction((x + 1 - k).toLong(), (j - k).toLong()) }
//            .filter { pair -> pair.bottom != 0L }  // k != j
//            .fold(Fraction(1L, 1L)) { acc, pair -> Fraction(acc.top * pair.top, acc.bottom * pair.bottom) }
//            .let { pair -> pair.top * p.evaluate(j.toLong()) / pair.bottom }
//    }
}

class Polynom(private val coefficients: IntArray) {
    val degree: Int
        get() = coefficients.size - 1

    fun evaluate(x: Long): Long {
        var result = 0L
        for (i in degree downTo 0) {
            result = result * x + coefficients[i]
        }
        return result
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (i in coefficients.indices) {
            val c = coefficients[i]
            if (c == 0) continue
            if (sb.isNotEmpty()) {
                sb.append(" + ")
            }
            sb.append(c)
            if (i > 0) {
                sb.append("n^").append(i)
            }
        }
        return sb.toString()
    }

}