// package cinema
const val PRICE = 10
const val BACK_HALF_PRICE = 8

fun constructorList(rows: Int, seatsOfRows: Int): MutableList<MutableList<Char>> {
    val list = MutableList(rows + 1) {
        "S".repeat(seatsOfRows + 1).toMutableList()
    }

    list[0][0] = ' '
    for (i in 1 .. rows) {
        for (j in 1 .. seatsOfRows) {
            list[0][j] = Character.forDigit(j, 10)
            list[i][0] = Character.forDigit(i, 10)
        }
    }

    return list
}

fun printCinema(list: MutableList<MutableList<Char>>, rows: Int) {
    println("\nCinema:")
    for (i in 0 .. rows) {
        println(list[i].joinToString(" "))
    }
}

fun buyTicket(list: MutableList<MutableList<Char>>, rows: Int, seatsOfRows: Int): Int {
    do {
        try {
            println("\nEnter a row number:")
            print("> ")
            val rowNumber = readln().toInt()

            println("Enter a seat number in that row:")
            print("> ")
            val seatsOfRowsNumber = readln().toInt()

            if (list[rowNumber][seatsOfRowsNumber] != 'B') {
                list[rowNumber][seatsOfRowsNumber] = 'B'

                print("\nTicket price: ")
                print("$")

                return if (rows * seatsOfRows > 60) {
                    if (rowNumber >= rows / 2 + rows % 2) {
                        println(BACK_HALF_PRICE)
                        BACK_HALF_PRICE
                    }
                    else {
                        println(PRICE)
                        PRICE
                    }
                } else {
                    println(PRICE)
                    PRICE
                }

            } else {
                println("\nThat ticket has already been purchased!")
            }

        } catch (e: IndexOutOfBoundsException) {
            println("\nWrong input!")
        }

    } while (true)

}

fun statistics(rows: Int, seatsOfRows: Int, purchasedTicket: Int, currentIncome: Int) {
    val percentage = (purchasedTicket.toDouble() * 100) / (rows * seatsOfRows)

    println("\nNumber of purchased tickets: $purchasedTicket")
    println("Percentage: ${"%.2f".format(percentage)}%")
    println("Current income: $$currentIncome")

    if ((rows - 1) * (seatsOfRows - 1) > 60) {
        println ("Total income: $${((rows / 2 + rows % 2) * seatsOfRows * BACK_HALF_PRICE) + (rows / 2 * seatsOfRows * PRICE)}")
    } else {
        println ("Total income: $${rows * seatsOfRows  * PRICE}")
    }
}

fun main() {
    var purchasedTicket = 0
    var currentIncome = 0

    println("Enter the number of rows:")
    print("> ")
    val rows = readln().toInt()

    println("Enter the number of seats in each row:")
    print("> ")
    val seatsOfRows = readln().toInt()

    val list = constructorList(rows, seatsOfRows)

    do {
        print("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n> ")
        val choice = readln().toInt()

        when(choice) {
            1 -> printCinema(list, rows)
            2 -> {
                currentIncome += buyTicket(list, rows, seatsOfRows)
                purchasedTicket++
            }
            3 -> statistics(rows, seatsOfRows, purchasedTicket, currentIncome)
        }
    } while (choice != 0)
}