package pe142

import kotlin.math.floor
import kotlin.math.sqrt

// https://projecteuler.net/problem=142
fun main() {
//    solve()
    solveMe()
}

fun solveMe() {
    // x > y > z > 0
    // a = x + y
    // b = x - y
    // c = x + z
    // d = x - z
    // e = y + z
    // f = y - z

    var found = false
    var i = 0L
    while (!found) {
        i++
        val a = i * i
        for (j in 1L until i) {
            val c = j * j
            val f = a - c
            if (f <= 0L || !isSquare(f)) continue

            for (k in 1L until j) {
                val d = k * k

                val e = a - d
                if (e <= 0L || !isSquare(e)) continue

                val b = c - e
                if (b <= 0L || !isSquare(b)) continue

                val x = (a + b) / 2L
                val y = (e + f) / 2L
                val z = (c - d) / 2L

                // check again because (e + f) might be odd , then y gets off by 1
                // if we dont do these checks we get a wrong answer
                // i=697 j=680 k=185  a=x+y=485809 b=x-y=10816 c=x+z=462400 d=x-z=34225 e=y+z=451584 f=y-z=23409  x=248312 y=237496 z=214087  sum=699895
                // where y=237496 but it should be 237497
                if (x + y != a) continue
                if (x - y != b) continue
                if (x + z != c) continue
                if (x - z != d) continue
                if (y + z != e) continue
                if (y - z != f) continue

                val sum = x + y + z
                println("i=$i j=$j k=$k  a=x+y=$a b=x-y=$b c=x+z=$c d=x-z=$d e=y+z=$e f=y-z=$f  x=$x y=$y z=$z  sum=$sum")
                println("x+y = $x + $y = ${x + y}  ${sqrt((x + y).toDouble())}^2")
                println("x-y = $x - $y = ${x - y}  ${sqrt((x - y).toDouble())}^2")
                println("y+z = $y + $z = ${y + z}  ${sqrt((y + z).toDouble())}^2")
                println("y-z = $y - $z = ${y - z}  ${sqrt((y - z).toDouble())}^2")
                println("x+z = $x + $z = ${x + z}  ${sqrt((x + z).toDouble())}^2")
                println("x-z = $x - $z = ${x - z}  ${sqrt((x - z).toDouble())}^2")
                found = true
            }
        }
    }
}

fun isSquare(n: Long): Boolean {
    val s = sqrt(n.toDouble())
    val fl = floor(s)
    return fl == s
}

// taken from https://www.mathblog.dk/project-euler-142-perfect-square-collection/
// to find bug in my code
fun solve() {
    var a: Long
    var b: Long
    var c: Long
    var d: Long
    var e: Long
    var f: Long
    var solved: Boolean = false
    var result: Long = 0

    var i: Long = 4
    while (!solved) {
        a = i * i
        var j: Long = 3
        while (j < i && !solved) {
            c = j * j
            f = a - c
            if (f <= 0 || !isSquare(f)) {
                j++
                continue
            }
            val kstart = if (j % 2 == 1L) 1 else 2.toLong()
            var k = kstart
            while (k < j) {
                d = k * k
                e = a - d
                b = c - e
                if (b <= 0 || e <= 0 || !isSquare(b) || !isSquare(e)) {
                    k += 2
                    continue
                }
                val x = (d + c) / 2
                val y = (e + f) / 2
                val z = (c - d) / 2
                result = x + y + z
                println("$i $j $k  $a $b $c $d $e $f  x=$x y=$y z=$z  sum=$result")
                solved = true
                break
                k += 2
            }
            j++
        }
        i++
    }
}