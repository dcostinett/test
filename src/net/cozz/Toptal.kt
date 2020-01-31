package net.cozz

class Toptal {

    fun getChange( M: Double,  P: Double): IntArray {
        val amount: Int = (M * 100).toInt()
        val price: Int = (P * 100).toInt()

        val response = intArrayOf(0,0,0,0,0,0)

        var change = amount - price

        val dollars = change / 100

        if (dollars > 0) {
            response[5] = dollars
            change -= (dollars * 100)
        }

        val fifties = (change / 50 )
        if (fifties > 0) {
            response[4] = fifties
            change -= (fifties * 50)
        }

        val tf = (change / 25 )
        if (tf > 0) {
            response[3] = tf
            change  -= (tf * 25)
        }

        val t = (change / 10)
        if (t > 0) {
            response[2] = tf
            change -= (t * 10)
        }

        val f = (change / 5 )
        if (f > 0) {
            response[1] = tf
            change -= (f * 5)
        }

        if (change > 0) {
            response[0] = change
        }

        response.forEach {
            print(it)
        }

        return response
    }
}
