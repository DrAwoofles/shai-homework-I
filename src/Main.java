import java.util.Random;
import java.util.Scanner;

public class Main {
    public static char[] generalCharArrayCreation(String stringForArray){
        char[] charArray = new char[stringForArray.length()];
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = stringForArray.charAt(i);
        }
        return charArray;
    }

    //Homework 1 - Israeli ID Validator
    public static boolean isValidId(String id){
        boolean validId = false;
        if(id.length() > 9){
            return false;
        }
        else if (id.length() < 9) {
            String neededZeroAmount = "";
            for (int i = 0; i < 9 - id.length(); i++) {
                neededZeroAmount += "0";
            }
            id = neededZeroAmount + id;
        }
        char[] idDigits = generalCharArrayCreation(id);
        int sum = 0;
        for (int i = 0; i < idDigits.length; i++) {
            int digit = Integer.parseInt(String.valueOf(idDigits[i]));
            if((i + 1) % 2 == 0){
                sum += digit * 2 > 9 ? digit * 2 - 9 : digit * 2;
            }
            else{
                sum += digit;
            }
        }
        if(sum % 10 == 0){
            System.out.println("This is a valid ID number");
            validId = true;
        }
        else {
            System.out.println("This is an invalid ID number");
        }
        return validId;
    }

    public static void israeliIdNumberValidator() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome! This here is the Israeli ID number validator");
        String id;
        do {
            System.out.println("Please enter a valid ID number: ");
            id = scanner.nextLine();
        } while (!isValidId(id));
    }


    //Homework 2 - Wordle
    public static boolean isCorrect(String guess, String wordToGuess){
        if(guess.length() != 5){
            System.out.println("The word you inputted isn't 5 letters long :(");
            return false;
        }
        char[] guessedWordLetters = generalCharArrayCreation(guess);
        char[] correctWordLetters = generalCharArrayCreation(wordToGuess);
        String placement = "";
        int correctPlacementsNum = 0;

        for (int i = 0; i < guessedWordLetters.length; i++) {
            if(String.valueOf(guessedWordLetters[i]).equalsIgnoreCase(String.valueOf(correctWordLetters[i]))){
                placement += "G";
                correctPlacementsNum++;
            }
            else if(guessedWordLetters[i] != correctWordLetters[i]){
                if(wordToGuess.contains(String.valueOf(guessedWordLetters[i]))){
                    placement += 'Y';
                }
                else{
                    placement += "_";
                }
            }
        }
        if(correctPlacementsNum != 5){
            System.out.println(placement);
            return false;
        }
        return true;
    }

    public static void wordle(){
        Scanner scanner = new Scanner(System.in);
        Random wordRandomiser = new Random();
        String[] wordList = {"Apple", "Grind", "Break", "Trust", "Judge", "Rides",
                "Weary", "Mourn", "Quick", "Hazed", "Enter", "Print", "Valid", "Guess",
                "Place", "Death", "Alive", "Heart", "Great"};
        System.out.println("Welcome to the Wordle! \n" +
                "It's kind of a bootleg version of it, but it still works! \n" +
                "Here's how it works: a random word will be chosen for you to guess in 6 tries.\n" +
                "Anytime you guess a letter right, the output would be G.\n" +
                "Anytime you guess a letter right but in the wrong place, the output would be Y.\n" +
                "Anytime you guess a letter wrong, the output would be... Nothing! \n" +
                "That's it, really!");
        String randomWord = wordList[wordRandomiser.nextInt(wordList.length)];
        System.out.println("The random word has been chosen! \n" +
                "Good luck!");
        int tries = 6;
        String guess;
        do{
            System.out.println("What do you think the word is?");
            guess = scanner.nextLine();
            if(guess.length() == 5){
                tries--;
            }
        }while(!isCorrect(guess, randomWord) && tries != 0);
        if(tries == 0){
            System.out.println("The word was " + randomWord + ".\n" +
                    "I'm sorry you couldn't guess it :(");
        }
        else{
            System.out.println("Congrats! You guessed the word! :)");
        }
    }
    public static void main(String[] args) {
        israeliIdNumberValidator();
        wordle();
    }
}