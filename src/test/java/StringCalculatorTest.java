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
    void upto2CommaSeperatedNumber_ShouldReturnTheirSum(String input, int expected) throws NegativeNumberException {
        int actual = calc.Add(input);
        Assertions.assertEquals(expected,actual);
    }

    //Allow the Add method to handle an unknown amount of numbers
    @ParameterizedTest
    @MethodSource("uptoNCommaSeparatedNumbers_ShouldReturnTheirSum")
    void uptoNCommaSeparatedNumbers_ShouldReturnTheirSum(String input,int expected) throws NegativeNumberException {
        int actual = calc.Add(input);
        Assertions.assertEquals(expected,actual);
    }

    //Allow the Add method to handle new lines between numbers (instead of commas).
    @ParameterizedTest
    @MethodSource("uptoNCommaOrNewlineSeparatedNumbers_ShouldReturnTheirSum")
    void uptoNCommaOrNewlineSeparatedNumbers_ShouldReturnTheirSum(String input, int expected) throws NegativeNumberException {
        int actual = calc.Add(input);
        Assertions.assertEquals(expected,actual);
    }

    //Allow the Add method to Support different delimiters
    @ParameterizedTest
    @MethodSource("uptoNCustomDelimiterSeparatedNumbers_ShouldReturnTheirSum")
    void uptoNCustomDelimiterSeparatedNumbers_ShouldReturnTheirSum(String input, int expected) throws NegativeNumberException {
        int actual = calc.Add(input);
        Assertions.assertEquals(expected,actual);
    }

    //Calling Add with a negative number will throw an exception
    @ParameterizedTest
    @MethodSource("uptoNCustomDelimitedSeparateNumbersWithNegativeNumbers_ShouldThrowException")
    void uptoNCustomDelimitedSeparateNumbersWithNegativeNumbers_ShouldThrowException(String input,Exception expected){
        Assertions.assertThrows(expected.getClass(),()->{
            int actual = calc.Add(input);
        },expected.getMessage());
    }

    @ParameterizedTest
    @MethodSource("numOver1000_ShouldBeIgnored")
    void numOver1000_ShouldBeIgnored(String input, int expected) throws NegativeNumberException {
        int actual = calc.Add(input);
        Assertions.assertEquals(expected,actual);
    }

    //Test Data Methods
    static Stream<Arguments> upto2CommaSeperatedNumber_ShouldReturnTheirSum() {
        return Stream.of(
            arguments("1,2", 3),
            arguments("3,4", 7),
            arguments("1", 1),
            arguments("",0),
            arguments("asd",0)
        );
    }

    static Stream<Arguments> uptoNCommaSeparatedNumbers_ShouldReturnTheirSum() {
        return Stream.of(
                arguments("1,2,3,4,5", 15),
                arguments("3,1,10,4,20", 38)
        );
    }

    static Stream<Arguments> uptoNCommaOrNewlineSeparatedNumbers_ShouldReturnTheirSum() {
        return Stream.of(
                arguments("1,2\n3,4,5", 15),
                arguments("3,1,10\n4,20", 38),
                arguments("1\n2\n3,4,5",15)
        );
    }

    static Stream<Arguments> uptoNCustomDelimiterSeparatedNumbers_ShouldReturnTheirSum() {
        return Stream.of(
                arguments("//:\n1,2\n3,4,5", 15),
                arguments("//;\n3;1,10\n4;20", 38),
                arguments("//a\n1\n2\n3,4a5",15)
        );
    }

    static Stream<Arguments> uptoNCustomDelimitedSeparateNumbersWithNegativeNumbers_ShouldThrowException() {
        return Stream.of(
                arguments("//:\n1,-2\n3,4,5", new NegativeNumberException("negatives not allowed -2")),
                arguments("//;\n3;1,-10\n4;-4", new NegativeNumberException("negatives not allowed -10 -4")),
                arguments("-1,-4,-5",new NegativeNumberException("negatives not allowed -1 -4 -5"))
        );
    }

    static Stream<Arguments> numOver1000_ShouldBeIgnored(){
        return Stream.of(
            arguments("//:\n1:2,3,1000",1006),
            arguments("//:\n1:2,3,100,1002",106),
            arguments("2,3444,1",3)
        );
    }
}