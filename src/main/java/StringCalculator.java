import java.util.Arrays;

public class StringCalculator {
    //Allow the Add method to handle new lines between numbers (instead of commas).
    public int Add(String numbers){
        try {
            int[] intArr = Arrays.stream(numbers.split(",|\n")).mapToInt(Integer::parseInt).toArray();

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
