import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;;

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

        int attemptsLeft = 6; // number of attempts allowed
        
        System.out.println("Current Progress: " + progress);
        int numGuesses = 0;
        while (numGuesses < 6 && !progress.equals(targetWord)) {
            System.out.println("Guess a letter: ");
            String guess = sc.nextLine();
            // Add logic to update progress based on guess
            numGuesses++;
        }

    }
}