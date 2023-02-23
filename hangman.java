import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class hangman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Let's Play Hangman!");

        List<String> words = new ArrayList<String>();
        try {
            File file = new File("input/dictionary.txt");
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine();
                words.add(word);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        System.out.println("Select Difficulty Level: ");
        System.out.println("1. Easy (12 lives)");
        System.out.println("2. Medium (9 lives)");
        System.out.println("3. Hard (7 lives)");
        int lives = 0;
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                lives = 12;
                break;
            case 2:
                lives = 9;
                break;
            case 3:
                lives = 7;
                break;
            default:
                System.out.println("Invalid choice. Setting difficulty to Easy.");
                lives = 12;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        String targetWord = words.get(randomIndex);

        String progress = "";
        for (int i = 0; i < targetWord.length(); i++) {
            progress += "_";
        }

        int attemptsLeft = lives; // number of attempts allowed
        int score = 0;

        while (attemptsLeft > 0) {

            // add stickman representation
            String[] stickman = new String[7];
            stickman[0] = "  _____ ";
            stickman[1] = "  |    |";
            stickman[2] = "       |";
            stickman[3] = "       |";
            stickman[4] = "       |";
            stickman[5] = "       |";
            stickman[6] = "  _____|";

            if (attemptsLeft < 7) {
                stickman[2] = "  O    |";
            }
            if (attemptsLeft < 6) {
                stickman[3] = "  |    |";
            }
            if (attemptsLeft < 5) {
                stickman[3] = " /|    |";
            }
            if (attemptsLeft < 4) {
                stickman[3] = " /|\\   |";
            }
            if (attemptsLeft < 3) {
                stickman[4] = "  |    |";
            }
            if (attemptsLeft < 2) {
                stickman[5] = " /     |";
                stickman[6] = "  _____|";
            }
            if (attemptsLeft < 1) {
                stickman[5] = " /\\     |";
                stickman[6] = "  _____|";
            }

            String stickmanString = String.join("\n", stickman);
            System.out.println(stickmanString);

            System.out.println("Current Progress: " + progress);
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.println("Guess a letter:");
            String letter = sc.next();

            // check if the letter is in the target word
            boolean foundLetter = false;
            for (int i = 0; i < targetWord.length(); i++) {
                if (targetWord.charAt(i) == letter.charAt(0)) {
                    // update the progress if the letter is found
                    StringBuilder sb = new StringBuilder(progress);
                    sb.setCharAt(i, letter.charAt(0));
                    progress = sb.toString();
                    foundLetter = true;
                }
            }

            if (foundLetter) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect.");
                attemptsLeft--;
            }

            // check if the player has won
            if (progress.equals(targetWord)) {
                score = attemptsLeft * 10; // calculate score
                System.out.println("You win! The word was: " + targetWord);
                System.out.println("Your score is: " + score);
                break;
            }
        }

        if (attemptsLeft == 0) {
            System.out.println("  _____ ");
            System.out.println("  |    |");
            System.out.println("  O    |");
            System.out.println(" /|\\   |");
            System.out.println("  |    |");
            System.out.println(" / \\   |");
            System.out.println("  _____|");
            System.out.println("Game over. The word was: " + targetWord);
        }

    }
}
