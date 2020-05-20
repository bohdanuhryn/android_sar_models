package com.bohdanuhryn.sar.utils.csv

class CsvListRow<T>(
    private val columns: List<String>,
    private val list: List<T>
) : CsvRow {

    override fun getCsvCells(): List<String> = list.map { it.toString() }.toList()

    override fun getCsvColumns(): List<String> = columns

}

fun <T> List<List<T>>.saveToCsv(filePath: String, columns: List<String>) {
    val rows = map { CsvListRow(columns, it) }
    val writer = CsvWriter(filePath, rows)
    writer.write()
}