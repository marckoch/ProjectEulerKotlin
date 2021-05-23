package util.graph

import java.util.*

fun main() {
    val matrix = arrayOf(
        intArrayOf(0, 16, 12, 21),
        intArrayOf(16, 0, 0, 17),
        intArrayOf(12, 0, 0, 28),
        intArrayOf(21, 17, 28, 0)
    )
    val matrix2 = arrayOf(intArrayOf(0, 16, 12), intArrayOf(16, 0, 0), intArrayOf(12, 0, 0))
    val matrix3 = arrayOf(
        intArrayOf(0, 16, 12, 21, 0, 0, 0),
        intArrayOf(16, 0, 0, 17, 20, 0, 0),
        intArrayOf(12, 0, 0, 28, 0, 31, 0),
        intArrayOf(21, 17, 28, 0, 18, 19, 23),
        intArrayOf(0, 20, 0, 18, 0, 0, 11),
        intArrayOf(0, 0, 31, 19, 0, 0, 27),
        intArrayOf(0, 0, 0, 23, 11, 27, 0)
    )
    val matrix4 =
        arrayOf(intArrayOf(0, 0, 12, 0), intArrayOf(0, 0, 0, 17), intArrayOf(12, 0, 0, 28), intArrayOf(0, 17, 28, 0))

    isConnectedGraph(matrix4)

    findAllCycles(matrix)
    val edges = getEdges(matrix)
    println(edges)
    val sortedEdges = getEdgesSorted(matrix)
    println(sortedEdges)
}

fun isConnectedGraph(matrix: Array<IntArray>): Boolean {
    // TODO we could/should short circuit the search by aborting when we find the first graph
    val allPathsInGraph = findAllPathsInGraph(matrix)
    return allPathsInGraph.isNotEmpty()
}

fun findAllPathsInGraph(matrix: Array<IntArray>): Map<Stack<Int>, Int> {
    val paths: MutableMap<Stack<Int>, Int> = HashMap()
    // start from every node
    matrix.indices.forEach { i ->
        val path = Stack<Int>()
        path.push(i)
        traverseGraph(matrix, i, path, paths, 0)
    }
    return paths
}

fun traverseGraph(
    matrix: Array<IntArray>,
    node: Int,
    path: Stack<Int>,
    paths: MutableMap<Stack<Int>, Int>,
    cost: Int
): Boolean {
    var cost = cost
    if (path.size == matrix.size) {
        println("path found: $path $cost")
        val copy = Stack<Int>()
        copy.addAll(path)
        paths[copy] = cost
        return true
    }
    // find nodes we can travel to
    val candidates = matrix.indices
        .map { if (matrix[node][it] > 0) it else -1 }
        .filter { it >= 0 }

    for (cand in candidates) {
        if (!path.contains(cand)) {
            path.push(cand)
            cost += matrix[node][cand]
            traverseGraph(matrix, cand, path, paths, cost)
            path.pop()
            cost -= matrix[node][cand]
        }
    }
    return false
}

fun findAllCycles(matrix: Array<IntArray>): List<Stack<Int>> {
    val cycles: MutableList<Stack<Int>> = ArrayList()
    for (i in matrix.indices) {
        val path = Stack<Int>()
        path.push(i)
        detectCycleRecursive(matrix, i, path, cycles)
    }

    //cycles.forEach(System.out::println);
    return cycles
}

fun detectCycleRecursive(
    matrix: Array<IntArray>,
    node: Int,
    path: Stack<Int>,
    cycles: MutableList<Stack<Int>>
): Boolean {
    if (path.size == matrix.size) {
        return false
    }
    // find nodes we can travel to
    val candidates = matrix.indices
        .map { if (matrix[node][it] > 0) it else -1 }
        .filter { it >= 0 }

    for (cand in candidates) {
        if (!path.contains(cand)) {
            path.push(cand)
            detectCycleRecursive(matrix, cand, path, cycles)
            path.pop()
        } else {
            if (path.indexOf(cand) < path.size - 2) {
                // println("cycle detected: $path")
                val copy = Stack<Int>()
                copy.addAll(path)
                copy.sorted()
                if (!cycles.contains(copy)) {
                    cycles.add(copy)
                }
                return true
            }
        }
    }
    return false
}

fun getEdges(matrix: Array<IntArray>): List<Edge> {
    val edges = mutableListOf<Edge>()
    for (row in matrix.indices) {
        for (col in matrix[row].indices) {
            val cost = matrix[row][col]
            if (cost > 0 && row < col) {
                val edge = Edge(row, col, cost)
                edges.add(edge)
            }
        }
    }
    return edges
}

fun getEdgesSorted(matrix: Array<IntArray>): List<Edge> {
    return getEdges(matrix).sortedBy { it.cost }
}

fun getNodeCount(matrix: Array<IntArray>): Int {
    return matrix.size
}

fun getWeight(matrix: Array<IntArray>): Long {
    var count: Long = 0
    for (row in matrix.indices) {
        for (col in 0 until row) {
            val weight = matrix[row][col].toLong()
            if (weight > 0) {
                count += weight
            }
        }
    }
    return count
}

fun isMinimalSpanningTree(matrix: Array<IntArray>): Boolean {
    return getNodeCount(matrix) == getEdges(matrix).count() + 1
}

data class Edge(val from: Int, val to: Int, val cost: Int)