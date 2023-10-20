package net.cozz.hackerrank

fun main(args: Array<String>) {
    val user1 = User("Dan", 1)
    val user2 = user1.copy(id = 2)

    print(user1)
    print(user2)
}

class CopyTest {

}

data class User(val name: String, val id: Int)
