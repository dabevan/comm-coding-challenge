import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RectangleTest {
    @Test
    fun `should calculate area`() {
        assertEquals(6.0, Rectangle(2.0,3.0).area())
    }
}