import java.util.Arrays;

public class StringCalculator {
    public int Add(String numbers){
        try {
            String[] stringArr = numbers.split(",", 2);

            //Check if empty string
            if(stringArr.length == 0) return 0;
            //Check if only one num in string
            if (stringArr.length == 1) return Integer.parseInt(stringArr[0]);

            //Handle 2 Number Addition
            return Integer.parseInt(stringArr[0]) + Integer.parseInt(stringArr[1]);
        }catch(Exception e){
            return 0;
        }
    }
}
