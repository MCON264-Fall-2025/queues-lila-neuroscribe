package junit_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(2, 3);
        assertEquals(5, result, "2 + 3 should equal 5");

    }

    @RepeatedTest(3)
    @DisplayName("Repeated multiplication test")
    public void testMultiply() {
        Calculator calculator = new Calculator();
        int result = calculator.multiply(4, 5);
        assertEquals(20, result, "4 * 5 should equal 20");
    }

    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
        "6, 2, 3",
        "10, 5, 2",
        "9, 3, 3"
    })
    @DisplayName("Testing division with multiple inputs from CSV Parameter Source")
    public void testDivide1(int a, int b, int expected) {
        Calculator calculator = new Calculator();
        int result = calculator.divide(a, b);
        assertEquals(expected, result, () -> a + " / " + b + " should equal " + expected);
    }

    static List<Arguments> divisionProvider() {
        return Arrays.asList(
                Arguments.of(6, 2, 3),
                Arguments.of(10, 5, 2),
                Arguments.of(9, 3, 3)
        );
    }

    @ParameterizedTest(name = "{0} / {1} = {2}")
    @MethodSource("divisionProvider")
    @DisplayName("Testing division with multiple inputs from MethodSource")
    void testDivide2(int a, int b, int expected) {
        Calculator calculator = new Calculator();
        int result = calculator.divide(a, b);
        assertEquals(expected, result, () -> a + " / " + b + " should equal " + expected);
    }
}

