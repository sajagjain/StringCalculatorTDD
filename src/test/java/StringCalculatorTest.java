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

    //Allow the Add method to handle upto 2 numbers
    @ParameterizedTest
    @MethodSource("upto2CommaSeperatedNumber_ShouldReturnTheirSum")
    void upto2CommaSeperatedNumber_ShouldReturnTheirSum(String input, int expected) {
        int actual = calc.Add(input);
        Assertions.assertEquals(expected,actual);
    }

    //Allow the Add method to handle an unknown amount of numbers
    @ParameterizedTest
    @MethodSource("uptoNCommaSeparatedNumbers_ShouldReturnTheirSum")
    void uptoNCommaSeparatedNumbers_ShouldReturnTheirSum(String input,int expected){
        int actual = calc.Add(input);
        Assertions.assertEquals(expected,actual);
    }

    static Stream<Arguments> upto2CommaSeperatedNumber_ShouldReturnTheirSum() {
        return Stream.of(
            arguments("1,2", 3),
            arguments("-3,4", 1),
            arguments("1", 1),
            arguments("",0),
            arguments("asd",0)
        );
    }

    static Stream<Arguments> uptoNCommaSeparatedNumbers_ShouldReturnTheirSum() {
        return Stream.of(
                arguments("1,2,3,4,5", 15),
                arguments("-3,-1,-10,4,20", 10)
        );
    }
}