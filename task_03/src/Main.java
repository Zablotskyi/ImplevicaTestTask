import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        // Variable for storing the factorial value
        BigInteger factorial = BigInteger.ONE;

        // Calculate factorial of 100
        for (int i = 2; i <= 100 ; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        // Convert the factorial result to a string
        String number = factorial.toString();

        int sum = 0;

        // Go through each digit and add it to the sum
        for (int i = 0; i < number.length(); i++) {
            sum += number.charAt(i) - '0';
        }

        // Print the result
        System.out.println("Сума цифр у числі 100! = " + sum);
    }
}
