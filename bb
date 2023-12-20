import java.util.Scanner;

public class ReverseAndFrequency {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        // Get the digit to find its frequency
        System.out.print("Enter the digit to find its frequency: ");
        int digitToFind = scanner.nextInt();

        // Close the scanner to prevent resource leak
        scanner.close();

        // Print the reverse of the number
        int reversedNumber = reverseNumber(number);
        System.out.println("Reversed number: " + reversedNumber);

        // Find and print the frequency of the specified digit
        int frequency = findDigitFrequency(number, digitToFind);
        System.out.println("Frequency of digit " + digitToFind + ": " + frequency);
    }

    // Method to reverse a number
    private static int reverseNumber(int num) {
        int reversed = 0;
        while (num != 0) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
        }
        return reversed;
    }

    // Method to find the frequency of a digit in a number
    private static int findDigitFrequency(int num, int digit) {
        int frequency = 0;
        while (num != 0) {
            int lastDigit = num % 10;
            if (lastDigit == digit) {
                frequency++;
            }
            num /= 10;
        }
        return frequency;
    }
}
