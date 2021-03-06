package day01

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/main/kotlin", "$name.txt").readLines()

/**
 * Converts string to Day01.md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun <T> Array<Array<T>>.print2DArray() {
    forEach { row ->
        row.forEach { item: T ->
            print(item.toString())
        }
        println()
    }
}
