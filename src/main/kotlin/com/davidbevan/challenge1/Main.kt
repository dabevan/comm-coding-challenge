package com.davidbevan.challenge1

fun main(args: Array<String>) {
     val timeFormatter = TimeFormatter()
     println(timeFormatter.formatTime(args[0].toInt()))
}