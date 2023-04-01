package cinema

import java.util.*

var countOfRows = 0
var countOfSeats = 0
var currentIncome = 0
var countOfBoughtTickets = 0
var totalIncome = 0
var percentage: Double = 0.0
const val CHEAP_TICKET = 8
const val EXPENSIVE_TICKET = 10
var cinema = MutableList(countOfRows) { MutableList(countOfSeats) {'S'} }

fun showTheSeats(cinema: MutableList<MutableList<Char>>) {
    print("Cinema:\n  ")
    for (i in 1..cinema[0].size)
        print("$i ")
    for (i in cinema.indices)
        print("\n${i + 1} ${cinema[i].joinToString(" ")}")
    println("\n")
}

fun buyTicket() {
        println("Enter a row number:")
        val rowNumber = readln().toInt()
        println("Enter a seat number in that row:")
        val seatNumber = readln().toInt()

        if (rowNumber !in 1..countOfRows || seatNumber !in 1..countOfSeats) {
            println("Wrong input!\n ")
            return buyTicket()
        }

        if (cinema[rowNumber - 1][seatNumber - 1] == 'B') {
            println("That ticket has already been purchased!\n ")
            return buyTicket()
        }

        countOfBoughtTickets++
        val cost = if (countOfRows * countOfSeats <= 60) EXPENSIVE_TICKET else if (rowNumber > countOfRows / 2) CHEAP_TICKET else EXPENSIVE_TICKET
        currentIncome += cost
        println("Ticket price: $$cost")
        cinema[rowNumber - 1][seatNumber - 1] = 'B'

}

fun statistics(){
    percentage = (countOfBoughtTickets.toDouble() / (countOfRows * countOfSeats)) * 100
    println("Number of purchased tickets: $countOfBoughtTickets")
    println("Percentage: ${"%.2f".format(Locale("en", "US"), percentage)}%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")

}



fun main() {

    println("Enter the number of rows:")
    countOfRows = readln().toInt()

    println("Enter the number of seats in each row:")
    countOfSeats = readln().toInt()
    cinema = MutableList(countOfRows) { MutableList(countOfSeats) {'S'} }
    totalIncome = if (countOfRows * countOfSeats <= 60) countOfRows * countOfSeats *10 else ((countOfRows / 2) * countOfSeats * 10 + (countOfRows / 2 + countOfRows % 2) * countOfSeats * 8)
    do {
        println("1. Show the seats\n" + "2. Buy a ticket\n" + "3. Statistics\n" + "0. Exit")
        val userInput = readln().toInt()
        when (userInput) {
            1 -> showTheSeats(cinema)
            2 -> buyTicket()
            3 -> statistics()
            0 -> println()
            else -> println("Invalid input!")
        }
    }while (userInput != 0)


}




