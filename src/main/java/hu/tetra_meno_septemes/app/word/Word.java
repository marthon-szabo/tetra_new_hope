package hu.tetra_meno_septemes.app.word;

import java.util.List;
import java.util.Random;

public class Word {
    private List<String> words;
    private List<String> userLetters;
    private String currentWord;
    private List<String> usedWords;

    public String checkLetter(String letter) {
        if (usedWords.contains(letter)) {
            return "You have used this letter: " + letter + ", already!";
        } else if (currentWord.contains(letter)) {
            userLetters.add(letter);
            return "true";
        } else {
            userLetters.add(letter);
            return "false";
        }
    }

    public String startGame() {
        return chooseWord();
    }

    private String chooseWord() {
        String chosenWord = "";

        while (usedWords.contains(chosenWord)) {
            chosenWord = words.get(new Random().nextInt(words.size() - 1));
        }
        usedWords.add(chosenWord);
        return chosenWord;
    }

}
