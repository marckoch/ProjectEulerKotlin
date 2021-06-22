package pe151

// https://projecteuler.net/problem=151
// TODO UNFINISHED
fun main() {
    keepCutting(listOf(Sheet.A4)).let { println(it) }

//    process(listOf(Sheet.A1), 0)
//    println(hit)
//    println(total)
}

var hit = 0
var total = 0

fun process(sheets: List<Sheet>, depth: Int) {
    if (sheets.isEmpty()) {
        return
    }
    if (sheets.size == 1) {
        hit++
    }
    val i = "  ".repeat(depth)
//    println("$i got $sheets $depth")
    for (s in sheets) {
//        println("$i processing $s")
        val pieces = cut(s)
//        println("$i cutting $s -> $pieces")

        val newSheets = ArrayList<Sheet>()
        newSheets.addAll(sheets)
        newSheets.remove(s)
        newSheets.addAll(pieces)
        newSheets.remove(Sheet.A5)
//        println("$i consuming one A5")
        total++

//        println("$i continuing with $newSheets")

        process(newSheets, depth + 1)
    }
}


fun batch(sheet: Sheet): List<Sheet> {
    val cutSheets = cut(sheet)
    val leftOverSheets = cutSheets.toMutableList()
    leftOverSheets.remove(Sheet.A5) // one A5 is consumed in this batch
    // println("batch $sheet -> $leftOverSheets")
    return leftOverSheets.toList()
}

fun cut(sheet: Sheet): List<Sheet> {
    return when (sheet) {
        Sheet.A5 -> listOf(Sheet.A5) // no cut
        Sheet.A4 -> listOf(Sheet.A5, Sheet.A5)
        Sheet.A3 -> listOf(Sheet.A4, Sheet.A5, Sheet.A5)
        Sheet.A2 -> listOf(Sheet.A3, Sheet.A4, Sheet.A5, Sheet.A5)
        Sheet.A1 -> listOf(Sheet.A2, Sheet.A3, Sheet.A4, Sheet.A5, Sheet.A5)
    }
}

fun keepCutting(sheets: List<Sheet>): List<Sheet> {
    if (sheets.size == 1 && sheets[0] == Sheet.A5) return sheets
    if (sheets.distinct().size == 1 && sheets.distinct()[0] == Sheet.A5) return sheets

    val head = sheets[0]
    val tail = sheets.drop(1)
    val newList = (tail + cut(head)).toMutableList()
    newList.remove(Sheet.A5)
    return keepCutting(newList.toList())
}

enum class Sheet {
    A1, A2, A3, A4, A5
}