package lesson4;






import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TriangleTest {
    private static Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @BeforeAll
    static void beforeAll() {
        //System.out.println("Метод выполнился перед всеми тестами 1 раз");
        logger.info("Метод выполнился перед всеми тестами 1 раз");
        logger.trace("trace level log");
        logger.trace("trace level log");
        logger.error("error log message");
    }

    @BeforeEach
    void beforeEach() {
        //System.out.println("Метод выполнился перед каждым тестом");
        logger.info("Метод выполнился перед каждым тесом 1 раз");
    }


    @Test
    void successSquareCalculation() throws Exception {
        assertEquals(TriangleArea.calcArea(3, 4, 5), 6);
        assertTrue(Math.abs(TriangleArea.calcArea(3, 4, 5) - 6) < 0.0001);

    }



    @Test
    void negativeTriangleTest() {
        Assertions.assertThrows(Exception.class, () -> TriangleArea.calcArea(-3, 4, 5));
    }

    @ParameterizedTest
    @MethodSource("triangleDataProvider")
    public void triangle1(int expected, double sideA, double sideB, double sideC) throws Exception {
        assertEquals(expected, TriangleArea.calcArea(sideA, sideB, sideC));
    }

    @ParameterizedTest
    @MethodSource("triangleDataProvider2")
    public void triangle2(int expected, double sideA, double sideB, double sideC) throws Exception {
        assertNotEquals(expected, TriangleArea.calcArea(sideA, sideB, sideC));
    }

    static Stream<Arguments> triangleDataProvider() {
        return Stream.of(
                arguments(6, 5, 4, 3),
                arguments(96, 20, 16, 12)
        );
    }

    static Stream<Arguments> triangleDataProvider2() {
        return Stream.of(
                arguments(10, 5, 4, 8)

        );
    }

    @Test
    @DisplayName("Не образует треугольник")
    void doesNotFormTriangleTest() throws Exception {
        assertEquals(TriangleArea.calcArea(1, 2, 3), 0);
        Assertions.assertNotEquals(3, TriangleArea.calcArea(1, 2, 3));
    }

    @AfterEach
    void afterEach() {
        //driver.quit()
        //System.out.println("Метод выполнится после каждого теста");
        logger.info("Метод выполнится после каждого теста");
    }

    @AfterAll
    static void afterAll() {
        //System.out.println("Метод выполнится после всех тестов");
        logger.info("Метод выполнится после всех тестов");
    }
}
