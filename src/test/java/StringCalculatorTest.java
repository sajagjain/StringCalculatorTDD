import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class StringCalculatorTest {
    StringCalculator calc = new StringCalculator();

    @ParameterizedTest
    @MethodSource("stringInputWithOutput")
    void add(String input, int expected) {
        int actual = calc.Add(input);
        Assertions.assertEquals(expected,actual);
    }

    static Stream<Arguments> stringInputWithOutput() {
        return Stream.of(
            arguments("1,2", 3),
            arguments("-3,4", 1),
            arguments("1", 1),
            arguments("",0),
            arguments("asd",0)
        );
    }
}