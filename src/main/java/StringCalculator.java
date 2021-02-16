import java.util.ArrayList;
import java.util.Arrays;

public class StringCalculator {
    //Allow the Add method to Support different delimiters
    public int Add(String numbers){
        try {
            ArrayList<String> separators = new ArrayList<String>();
            separators.add(",");
            separators.add("\n");

            if(numbers.startsWith("//")) {
                String[] delimiterAndNumber = numbers.split("\n", 2);
                String customSeparator = delimiterAndNumber[0].replace("//", "");
                separators.add(customSeparator);
                numbers = delimiterAndNumber[1];
            }

            //Handle N Number Addition
            int sum = Arrays.stream(numbers.split(String.join("|",separators))).mapToInt(Integer::parseInt).sum();
            return sum;
        }catch(Exception e){
            return 0;
        }
    }
}
