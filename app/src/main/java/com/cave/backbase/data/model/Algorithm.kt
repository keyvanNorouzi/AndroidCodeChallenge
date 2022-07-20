package com.cave.backbase.data.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    getData()
}

fun getData() {
    val path = "/home/keyvan/AndroidStudioProjects/BackBase/app/src/main/assets/cities.txt"
//    measureTime(name = "readAllLines", ::readAllLines, path = path)
//    measureTime(name = "bufferReaderToLinkedList", ::bufferReaderToLinkedList, path = path)
//    measureTime(name = "bufferReaderToArrayList", ::bufferReaderToArrayList, path = path)
//    val jsonReader = Json.decodeFromString<List<City>>("")
    measureTime2(name = "stringBuffer", ::readLargeJson, path = path)
}

private fun readAllLines(path: String): List<String?>? {
    try {
        return Files.readAllLines(Paths.get(path))
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}

private fun measureTime(name: String, fn: (String) -> List<String?>?, path: String) {
    println("-----------------------------------------------------------")
    println("run: $name")
    val startTime = System.nanoTime()
    val l: List<String?>? = fn.invoke(path)
    val estimatedTime = System.nanoTime() - startTime
    println(("lines: ") ?: -1)
    println("estimatedTime: " + estimatedTime / 1000000000.0)
}

private fun measureTime2(name: String, fn: (String) -> List<City>, path: String) {
    println("-----------------------------------------------------------")
    println("run: $name")
    val startTime = System.nanoTime()
    val l = fn.invoke(path)
    val estimatedTime = System.nanoTime() - startTime
    println(("lines: " + l.size) ?: -1)
    println("estimatedTime: " + estimatedTime / 1000000000.0)
}

private fun bufferReaderToLinkedList(path: String): List<String?>? {
    return bufferReaderToList(path, LinkedList())
}

fun bufferReaderToArrayList(path: String): List<String?>? {
    return bufferReaderToList(path, ArrayList())
}

private fun bufferReaderToList(path: String, list: MutableList<String>): List<String>? {
    try {
        val `in` = BufferedReader(
            InputStreamReader(FileInputStream(path), StandardCharsets.UTF_8)
        )
        val iterator = `in`.lineSequence().iterator()
        while (iterator.hasNext()) {
            val line = iterator.next()
            list.add(line)
        }
        `in`.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return list
}

@OptIn(ExperimentalSerializationApi::class)
private fun readLargeJson(path: String): List<City> {
    val list: List<City>
    Files.newInputStream(Paths.get(path)).use { inputStream ->
        list = (Json.decodeFromStream<List<City>>(inputStream))
    }
    return list.sortedWith(compareBy({ it.city }, { it.country }))
}
