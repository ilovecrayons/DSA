import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class WordleProgram {
    private static final int MAX_ATTEMPTS = 6;
    private static final int WORD_LENGTH = 5;
    
    public static void main(String[] args) throws Exception {
        Set<String> allWords = getWordList();
        
        String[] wordArray = allWords.toArray(new String[0]);
        Random random = new Random();
        String answer = wordArray[random.nextInt(wordArray.length)];
        
        Set<String> possibleWords = new TreeSet<>(allWords);
        
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        boolean won = false;
        
        while (attempts < MAX_ATTEMPTS && !won) {
            System.out.println("\nAttempt " + (attempts + 1) + "/" + MAX_ATTEMPTS);
            System.out.print("Enter your guess: ");
            String guess = scanner.nextLine().toLowerCase().trim();
            
            if (guess.length() != WORD_LENGTH) {
                System.out.println("Please enter a " + WORD_LENGTH + "-letter word.");
                continue;
            }
            
            if (!allWords.contains(guess)) {
                System.out.println("Not a valid word in our dictionary. Try again.");
                continue;
            }
            
            attempts++;
            
            if (guess.equals(answer)) {
                won = true;
                System.out.println("Congratulations! You've guessed the word: " + answer);
                break;
            }
            
            String feedback = getFeedback(guess, answer);
            System.out.println("Feedback: " + feedback);
            
            possibleWords = updatePossibleWords(possibleWords, guess, feedback);
            
            System.out.println("Possible words remaining: " + possibleWords.size());
            if (possibleWords.size() <= 20) {
                System.out.println("Possible words: " + possibleWords);
            } else {
                System.out.println("Too many possible words to display them all.");
                List<String> sampleWords = new ArrayList<>(possibleWords);
                System.out.println("Sample of possible words: " + sampleWords.subList(0, 20) + "...");
            }
        }
        
        if (!won) {
            System.out.println("\nGame over! The word was: " + answer);
        }
        
        scanner.close();
    }

    private static String getFeedback(String guess, String answer) {
        char[] feedbackArray = new char[WORD_LENGTH];
        boolean[] answerUsed = new boolean[WORD_LENGTH];
        
        for (int i = 0; i < WORD_LENGTH; i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                feedbackArray[i] = 'G';
                answerUsed[i] = true;
            } else {
                feedbackArray[i] = 'X';
            }
        }
        
        for (int i = 0; i < WORD_LENGTH; i++) {
            if (feedbackArray[i] == 'X') {  
                char guessChar = guess.charAt(i);
                for (int j = 0; j < WORD_LENGTH; j++) {
                    if (!answerUsed[j] && answer.charAt(j) == guessChar) {
                        feedbackArray[i] = 'Y';
                        answerUsed[j] = true;
                        break;
                    }
                }
            }
        }
        
        return new String(feedbackArray);
    }

    private static Set<String> updatePossibleWords(Set<String> possibleWords, String guess, String feedback) {
        Set<String> updatedWords = new TreeSet<>();
        
        for (String word : possibleWords) {
            if (isConsistentWithFeedback(word, guess, feedback)) {
                updatedWords.add(word);
            }
        }
        
        return updatedWords;
    }
    
    
    private static boolean isConsistentWithFeedback(String word, String guess, String feedback) {
        String hypotheticalFeedback = getFeedback(guess, word);
        return hypotheticalFeedback.equals(feedback);
    }

    public static Set<String> getWordList() throws FileNotFoundException {
        File f = new File("C:\\Users\\huang\\Desktop\\Prog\\DSA\\wordle\\wordle_words.txt");
        Scanner s = new Scanner(f);
        Set<String> words = new TreeSet<>();
        while(s.hasNext()){
            words.add(s.next().toLowerCase());
        }
        s.close();
        return words;
    }
}
