import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //We create a Scaner object for reading from the keyboard
        Scanner scanner = new Scanner(System.in);

        //Let the user enter the number N from the keyboard
        System.out.println("Введіть N: ");
        int n = Integer.parseInt(scanner.nextLine());

        //We check that the number N is not negative
        if (n < 0) {
            System.out.println("N має бути додатнім цілим числом.");
            return;
        }

        //We create an array where we will store the number of correct expressions for 'i' pairs of staples.
        BigInteger[] dp = new BigInteger[n + 1];

        //We fill the array with zeros
        for (int i = 0; i <= n; i++) {
            dp[i] = BigInteger.ZERO;
        }

        //For 0 pairs of staples, there is only 1 correct expression
        dp[0] = BigInteger.ONE;

        //Fill the array from 1 to n
        for (int i = 1; i <= n; i++) {
            //For each and we count dp[i] according to Catalan's formula:
            //dp[i] = dp[0] * dp[i-1] + dp[1] * dp[i-2] + ... + dp[i-1] * dp[0]
            for (int j = 0; j < i; j++) {
                dp[i] = dp[i].add(dp[j].multiply(dp[i - 1 - j]));
            }
        }

        //output the result
        System.out.println("Кількість правильних виразів: " + dp[n]);
        scanner.close();
    }
}
