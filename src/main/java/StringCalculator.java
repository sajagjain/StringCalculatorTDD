import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.IntStream;

public class StringCalculator {
    //Allow multiple delimiters of any length 8 & 9
    public int Add(String numbers) throws NegativeNumberException {
        try {
            ArrayList<String> separators = new ArrayList<String>();
            separators.add(",");
            separators.add("\n");

            //Handle Multiple Delimiters with [][] syntax first
            if(numbers.startsWith("//[")){
                String[] delimiterAndNumber = numbers.split("\n", 2);
                String[] customSeparator = (delimiterAndNumber[0]
                        .replace("//[", "")
                        .replace("][",",")
                        .replace("]",""))
                        .replace("*","\\*")
                        .split(",");

                Collections.addAll(separators,customSeparator);
                numbers = delimiterAndNumber[1];
            }
            if(numbers.startsWith("//")) {
                String[] delimiterAndNumber = numbers.split("\n", 2);
                String customSeparator = delimiterAndNumber[0].replace("//", "");
                separators.add(customSeparator);
                numbers = delimiterAndNumber[1];
            }

            //Handle N Number Addition
            String[] nums = numbers.split(String.join("|",separators));
            String negativeNums = "";
            for (String num:nums) {
                if(num.contains("-")){
                    negativeNums += " "+num;
                }
            }
            if(negativeNums.length() > 0){
                throw new NegativeNumberException("negatives not allowed"+negativeNums);
            }
            else{
                int sum = Arrays.stream(nums).mapToInt(Integer::parseInt).filter(a->a <= 1000).sum();
                return sum;
            }
        }
        catch (NegativeNumberException e){
            throw e;
        }
        catch(Exception e){
            return 0;
        }
    }
}
