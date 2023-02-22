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
        } catch (FileNotFoundException e ) {
            System.out.println("File not found!");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        String targetWord = words.get(randomIndex);

        String progress = "";
        for (int i = 0; i < targetWord.length(); i++) {
            progress += "_";
        }

        int maxAttempts = 0;
        boolean validDifficulty = false;
        while (!validDifficulty) {
            System.out.println("Choose difficulty level (1-3):");
            int difficulty = sc.nextInt();
            switch (difficulty) {
                case 1:
                    maxAttempts = 8;
                    validDifficulty = true;
                    break;
                case 2:
                    maxAttempts = 6;
                    validDifficulty = true;
                    break;
                case 3:
                    maxAttempts = 4;
                    validDifficulty = true;
                    break;
                default:
                    System.out.println("Invalid difficulty level!");
                    break;
            }
        }

        int attemptsLeft = maxAttempts;

        while (attemptsLeft > 0) {
            System.out.println("Current Progress: " + progress);
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.println("Guess a letter:");
            String letter = sc.next().toLowerCase();

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
                System.out.println("You win! The word was: " + targetWord);
                break;
            }
        }

        if (attemptsLeft == 0) {
            System.out.println("Game over. The word was: " + targetWord);
        }

    }
}
