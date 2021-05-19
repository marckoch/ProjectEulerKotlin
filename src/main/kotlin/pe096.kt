package pe096

import java.util.*

// https://projecteuler.net/problem=96
fun main() {
    val fileContent = object {}.javaClass.getResource("pe096_sudoku.txt")!!.readText()

    fileContent.lines()
        .chunked(10) // 10 lines form a game
        .sumOf { solveGame(it) }
        .let { println(it) }
}

// solve one game
private fun solveGame(game: List<String>): Int {
    val board = parseGame(game)
    solveBoard(board)
    // printBoard(board)
    return getWinningNumber(board)
}

// solve board, will be called recursively
private fun solveBoard(board: Array<IntArray>): Boolean {
    if (isValid(board)) {
        // println("found valid board!")
        return true
    }
    for (row in board.indices) {
        for (col in board.indices) {
            if (board[row][col] == 0) {
                for (n in getCandidates(board, row, col)) {
                    board[row][col] = n

                    val valid = solveBoard(board)
                    if (valid)
                        return true
                    else
                        board[row][col] = 0 // ok, setting row/col to 'n' did not work, revert it
                }
                return false // none of the candidates worked :-(
            }
        }
    }
    return true
}

private fun parseGame(game: List<String>): Array<IntArray> {
    return game
        .drop(1) // ignore first line that contains the header "Game xx"
        .map { toIntArr(it) }
        .toTypedArray()
}

// convert String of 9 digits to int[]
// e.g. "123456789" -> [1,2,3,4,5,6,7,8,9]
fun toIntArr(line: String): IntArray {
    return line.toCharArray()
        .map { Character.digit(it, 10) }
        .toIntArray()
}

// check if the board is valid, that means:
// every row is valid, every column is valid and every quadrant is valid
private fun isValid(board: Array<IntArray>): Boolean {
    // check if each row is valid
    for (rowArr in board) {
        if (!isValid(rowArr)) {
            return false
        }
    }

    // check if each col is valid
    for (i in board.indices) {
        val colArr = extractCol(board, i)
        if (!isValid(colArr)) {
            return false
        }
    }

    // check if each quadrant is valid
    for (row in board.indices step 3) {
        for (col in board.indices step 3) {
            val qArr = extractQuadrant(board, row, col)
            if (!isValid(qArr)) {
                return false
            }
        }
    }
    return true
}

// check if in given arr all numbers are set and distinct
fun isValid(arr: IntArray): Boolean {
    return arr.all { it > 0 } && arr.distinct() == arr.toList()
}

// extract column with index 'col' from given board (in each row the col-th number)
fun extractCol(board: Array<IntArray>, col: Int): IntArray {
    return board.map { it[col] }.toIntArray()
}

// extract the quadrant that contains inputRow and inputCol
fun extractQuadrant(board: Array<IntArray>, inputRow: Int, inputCol: Int): IntArray {
    val quadrantSize = 3
    val startRow = inputRow / quadrantSize * quadrantSize
    val endRow = startRow + quadrantSize
    val startCol = inputCol / quadrantSize * quadrantSize
    val endCol = startCol + quadrantSize

    val quadrant: MutableList<Int> = ArrayList()
    for (row in board.indices) {
        for (col in board.indices) {
            if (row in startRow until endRow &&
                (col in startCol until endCol)
            ) {
                quadrant.add(board[row][col])
            }
        }
    }
    return quadrant.toIntArray()
}

// find candidates for position row/col in given board
// finds all numbers that are NOT set in the same row, same col or same quadrant
private fun getCandidates(board: Array<IntArray>, row: Int, col: Int): IntArray {
    if (board[row][col] > 0) {  // cell already occupied!
        return intArrayOf()
    }

    val rowArr = board[row]
    val colArr = extractCol(board, col)
    val qArr = extractQuadrant(board, row, col)

    val numbersAlreadySet: MutableSet<Int> = HashSet()
    numbersAlreadySet.addAll(rowArr.toTypedArray())
    numbersAlreadySet.addAll(colArr.toTypedArray())
    numbersAlreadySet.addAll(qArr.toTypedArray())

    return (1..9).filter { !numbersAlreadySet.contains(it) }.toIntArray()
}

fun getWinningNumber(board: Array<IntArray>): Int {
    return 100 * board[0][0] + 10 * board[0][1] + board[0][2]
}

// print the board, add lines to show quadrants
private fun printBoard(board: Array<IntArray>) {
    for (row in board.indices) {
        if (row > 0 && row % 3 == 0) {
            println("---+---+---")
        }
        for (col in board.indices) {
            if (col > 0 && col % 3 == 0) {
                print("|")
            }
            print(board[row][col])
        }
        print("\n")
    }
}

// print board in simple form, that means NO lines for quadrants
private fun printBoardSimple(board: Array<IntArray>) {
    println("----------")
    for (ints in board) {
        println(ints.contentToString())
    }
}