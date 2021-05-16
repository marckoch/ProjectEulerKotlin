package pe090

import util.cartesianProduct.cartesianProductOf
import util.combinations.combinations

// https://projecteuler.net/problem=90
fun main() {
    val digits = (0..9).toList()
    val cubeCombis = combinations(digits, 6).toList()
    // println(cubeCombis.size) 210 combinations!

    var countValidCube = 0
    for (cube1 in cubeCombis) {
        for (cube2 in cubeCombis) {
            val valid = checkCubes(cube1, cube2)
            if (valid) {
                // println("valid: $cube1 $cube2")
                countValidCube++
            }
        }
    }
    // divide by 2 because we counted double: cube1 and cube 2 <-> cube2 and cube1
    println(countValidCube / 2)

    // the same with cartesianProduct
    cartesianProductOf(cubeCombis, cubeCombis)
        .filter { (cube1, cube2) -> checkCubes(cube1, cube2) }
        .count()
        .let { println(it / 2) }
}

// check if cube1 and cube2 can form all square numbers from 01 ... 81
fun checkCubes(cube1: List<Int>, cube2: List<Int>): Boolean {
    return canForm01(cube1, cube2) &&
            canForm04(cube1, cube2) &&
            canForm09(cube1, cube2) &&
            canForm16(cube1, cube2) &&
            canForm25(cube1, cube2) &&
            canForm36(cube1, cube2) &&
            canForm49(cube1, cube2) &&
            canForm64(cube1, cube2) &&
            canForm81(cube1, cube2)
}

fun canForm01(cube1: List<Int>, cube2: List<Int>): Boolean {
    return cube1.contains(0) && cube2.contains(1) ||
            cube1.contains(1) && cube2.contains(0)
}

fun canForm04(cube1: List<Int>, cube2: List<Int>): Boolean {
    return cube1.contains(0) && cube2.contains(4) ||
            cube1.contains(4) && cube2.contains(0)
}

fun canForm09(cube1: List<Int>, cube2: List<Int>): Boolean {
    return cube1.contains(0) && cube2.contains6or9() ||
            cube1.contains6or9() && cube2.contains(0)
}

fun canForm16(cube1: List<Int>, cube2: List<Int>): Boolean {
    return cube1.contains(1) && cube2.contains6or9() ||
            cube1.contains6or9() && cube2.contains(1)
}

fun canForm25(cube1: List<Int>, cube2: List<Int>): Boolean {
    return cube1.contains(2) && cube2.contains(5) ||
            cube1.contains(5) && cube2.contains(2)
}

fun canForm36(cube1: List<Int>, cube2: List<Int>): Boolean {
    return cube1.contains(3) && cube2.contains6or9() ||
            cube1.contains6or9() && cube2.contains(3)
}

fun canForm49(cube1: List<Int>, cube2: List<Int>): Boolean {
    return cube1.contains(4) && cube2.contains6or9() ||
            cube1.contains6or9() && cube2.contains(4)
}

fun canForm64(cube1: List<Int>, cube2: List<Int>): Boolean {
    return cube1.contains(4) && cube2.contains6or9() ||
            cube1.contains6or9() && cube2.contains(4)
}

fun canForm81(cube1: List<Int>, cube2: List<Int>): Boolean {
    return cube1.contains(1) && cube2.contains(8) ||
            cube1.contains(8) && cube2.contains(1)
}

// small helper function to make code above  more readable
fun List<Int>.contains6or9(): Boolean {
    return contains(6) || contains(9)
}