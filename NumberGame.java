import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalRounds = 0;
        int totalScore = 0;
        int maxRounds = 2; // Only allow 2 rounds

        while (totalRounds < maxRounds) {
            int numberToGuess = random.nextInt(100) + 1;
            int maxAttempts = 5;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nWelcome to the Number Guessing Game!");
            System.out.println("Round " + (totalRounds + 1) + " of " + maxRounds);
            System.out.println("Guess a number between 1 and 100. You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println(" Correct! You guessed the number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    totalScore += (maxAttempts - attempts + 1) * 10;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println(" Sorry! You've used all attempts. The correct number was: " + numberToGuess);
            }

            totalRounds++;
        }

        System.out.println("\n Game Over!");
        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Your final score: " + totalScore);
        scanner.close();
    }
}
