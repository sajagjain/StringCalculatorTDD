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

            int[] intArr = Arrays.stream(numbers.split(String.join("|",separators))).mapToInt(Integer::parseInt).toArray();

            //Check if empty string
            if(intArr.length == 0) return 0;
            //Check if only one num in string
            if (intArr.length == 1) return intArr[0];

            //Handle N Number Addition
            int sum = 0;
            for (int num:intArr) {
                 sum += num;
            }

            return sum;
        }catch(Exception e){
            return 0;
        }
    }
}
