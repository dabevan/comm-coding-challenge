import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sandpit.HelloWorld

internal class HelloWorldTest {

    private val helloWorld = HelloWorld()

    @Test
    fun `should greet David by name`() {

        assertEquals("Hello, David", helloWorld.sayHello("David"))
    }

    @Test
    fun `should greet Fred by name`() {

        assertEquals("Hello, Fred", helloWorld.sayHello("Fred"))
    }
}