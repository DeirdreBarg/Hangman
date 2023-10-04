import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Hangman {
    ArrayList<String> list;
    String secretWord;
    // can just get the lenght of this
    ArrayList<Character> wrongGuesses;
    ArrayList<Character> correctGuesses;
    int numMistakes;
    Character letter;
    boolean needsMoreLetters;
    boolean hasWon;

    Hangman(ArrayList<String> list) {
        this.list = list;
        this.correctGuesses = new ArrayList<>();
        this.wrongGuesses = new ArrayList<>();
        needsMoreLetters = true;
        hasWon = false;
    }

    public void pickWord() {
        Random rand = new Random();
        secretWord = list.get(rand.nextInt(list.size()));
        System.out.println("DEBUG: secretWord is: " + secretWord);

    }
    public void printStart() {
        System.out.println("Welcome to Hangman! You're allowed 5 mistakes to guess the secret word one letter at a time. Good Luck!");
        String word = secretWord;
        for (int i = 0; i < word.length(); i++) {
            System.out.print("_");
        }
        System.out.println();
    }
          
    public Character getInput() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a letter:");
            letter = scanner.nextLine().charAt(0); 
            System.out.println("You entered: " + letter);
        } catch (Exception e) {
            System.out.println("There was a problem with the scanner :/");
            System.out.println(e);
        }
        return letter;
    }

    public void executeGuess() {
        if (secretWord.indexOf(letter) != -1) {
            System.out.println("The letter '" + letter + "' is in the word.");
            correctGuesses.add(letter);            
        } else {
            System.out.println("The letter '" + letter + "' is not in the word.");
            numMistakes++;
            wrongGuesses.add(letter);            
        }
        displayStatus();

        // End the game if they hit 5 mistakes
        if (numMistakes == 5) {
            needsMoreLetters = false;
            System.out.println("No more guesses. You failed");
        }

        // End the game if they guessed the word
        if (hasGuessedAllLetters()) {
            hasWon = true;
            System.out.println("You won!!!!");
        }
    }

    public boolean hasGuessedAllLetters() {        
        for (int i = 0; i < secretWord.length(); i++) {
            char c = secretWord.charAt(i);
            if (!correctGuesses.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public void displayStatus() {        
        for (int i = 0; i < secretWord.length(); i++) {
            // If the user has guessed the letter in the i index of the secret word
            if (correctGuesses.contains(secretWord.charAt(i))) {
                System.out.print(secretWord.charAt(i));
            } else {
                System.out.print("_");
            }
        } System.out.println();
        System.out.println("number of Mistakes is: " + numMistakes);
        System.out.println();
    }

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<String>();
        list.add("hello");
        list.add("dog");
        list.add("hungry");
        list.add("happy");
        
        Hangman game = new Hangman(list);         
        

        game.pickWord();
        game.printStart();
        while (game.needsMoreLetters && !game.hasWon) {
            game.getInput();
            game.executeGuess();
        }
    } 
}