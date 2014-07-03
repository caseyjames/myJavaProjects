package other;

/**
 * Created by Casey on 7/1/2014.
 */
public class modOperator {
    static int[] numerator = {12,15,17,46,89,90};
    static int denominator = 10;

    public static void main(String[] args) {
        int[] output = new int[6];
        for (int i = 0; i < 6; i++) {
            output[i] = numerator[i]%denominator;
            System.out.println(output[i]);
        }
    }
}
