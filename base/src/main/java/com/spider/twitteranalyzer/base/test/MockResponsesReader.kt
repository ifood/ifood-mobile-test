package com.spider.twitteranalyzer.base.test

import java.io.File
import java.io.FileNotFoundException
import java.util.*


object MockResponsesReader {

    @Throws(FileNotFoundException::class)
    fun readFile(fileName: String): String {

        val result = StringBuilder()

        val classLoader = MockResponsesReader::class.java.classLoader
        val file = File(classLoader!!.getResource(fileName).file)

        val scanner = Scanner(file)

        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()
            result.append(line).append("\n")
        }

        scanner.close()

        return result.toString()

    }
}
