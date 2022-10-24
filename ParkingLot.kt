class ParkingLot {
    private var input = ""
    private var n = 0
    private var state = false
    private var parking = mutableListOf<Car>()
    private fun parkingIsEmpty(): Boolean {
        for (i in parking) {
            if (i.color != "" && i.number != "") {
                return false
            }
        }
        return true
    }
    private fun spotIsEmpty(i: Int): Boolean {
        return parking[i].color == "" && parking[i].number == ""
    }
    private fun takeCar(line: List<String>): Car {
        return Car(line[1], line[2])
    }
    private fun create(line: List<String>) {
        val nTemp = line[1].toInt()
        n = nTemp
        parking = MutableList(n) {Car("", "")}
        state = true
        println("Created a parking lot with $n spots.")
    }
    private fun leave(line: List<String>) {
        if (!state) {
            println("Sorry, a parking lot has not been created.")
            return
        }
        val number = line[1].toInt() - 1
        parking[number] = Car("", "")
        println("Spot ${number + 1} is free.")
    }
    private fun park(line: List<String>) {
        if (!state) {
            println("Sorry, a parking lot has not been created.")
            return
        }
        for (i in parking.indices) {
            if (spotIsEmpty(i)) {
                parking[i] = takeCar(line)
                println("${line[2]} car parked in spot ${i + 1}.")
                return
            }
        }
        println("Sorry, the parking lot is full.")
    }
    private fun status() {
        if (!state) {
            println("Sorry, a parking lot has not been created.")
            return
        }
        if (parkingIsEmpty()) {
            println("Parking lot is empty.")
            return
        }
        for (i in parking.indices) {
            if (spotIsEmpty(i)) {
                val car = parking[i]
                println("${i + 1} ${car.number} ${car.color}")
            }
        }
    }
    private fun spotByReg(number: String) {
        if (!state) {
            println("Sorry, a parking lot has not been created.")
            return
        }
        for (i in parking.indices) {
            if (parking[i].number == number) {
                println(i + 1)
                return
            }
        }
        println("No cars with registration number $number were found.")
        return
    }
    private fun spotByColor(color: String) {
        if (!state) {
            println("Sorry, a parking lot has not been created.")
            return
        }
        val spots = mutableListOf<Int>()
        for (i in parking.indices) {
            if (parking[i].color.lowercase() == color.lowercase()) spots.add(i + 1)
        }
        if (spots.isEmpty()) {
            println("No cars with color $color were found.")
        } else {
            println(spots.joinToString(", "))
        }
        return
    }
    private fun regByColor(color: String) {
        if (!state) {
            println("Sorry, a parking lot has not been created.")
            return
        }
        val spots = mutableListOf<String>()
        for (i in parking.indices) {
            if (parking[i].color.lowercase() == color.lowercase()) spots.add(parking[i].number)
        }
        if (spots.isEmpty()) {
            println("No cars with color $color were found.")
        } else {
            println(spots.joinToString(", "))
        }
        return
    }
    fun chat() {
        while (true) {
            input = readln()
            val line = input.split(" ")
            when (line[0]) {
                "create" -> create(line)
                "status" -> status()
                "leave" -> leave(line)
                "park" -> park(line)
                "spot_by_reg" -> spotByReg(line[1])
                "spot_by_color" -> spotByColor(line[1])
                "reg_by_color" -> regByColor(line[1])
                "exit" -> break
            }
        }
    }

}