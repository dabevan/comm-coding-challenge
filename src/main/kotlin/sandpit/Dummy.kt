package sandpit

import io.vavr.control.Either


fun main() {
   val a = myFun()
   //if (a.isRight) println("Right=${a.get()}")
   println("Right=${a.orNull}")

}

fun myFun(): Either<Int, String> {
   //return Either.right("Hello")
   return Either.left(3)
}




