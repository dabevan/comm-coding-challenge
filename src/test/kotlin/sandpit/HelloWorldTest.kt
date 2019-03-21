import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import sandpit.HelloWorld
import kotlin.test.assertEquals

internal class HelloWorldTest {

    private val helloWorld = HelloWorld()

    @Test
    fun `should greet user by name`() {

        assertEquals("Hello, David", helloWorld.sayHello("Davidzzz"))
    }
}