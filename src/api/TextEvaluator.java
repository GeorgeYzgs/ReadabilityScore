/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author George.Giazitzis
 */
public class TextEvaluator {

    private int wordCounter = 0;
    private int charCounter = 0;
    private int sentenceCounter = 0;
    private int syllables = 0;
    private int polysyllables = 0;

    public TextEvaluator(String text) {
        calculate(text);
    }

    public int getWordCounter() {
        return wordCounter;
    }

    public int getCharCounter() {
        return charCounter;
    }

    public int getSentenceCounter() {
        return sentenceCounter;
    }

    public int getSyllables() {
        return syllables;
    }

    public int getPolysyllables() {
        return polysyllables;
    }

    private void calculate(String text) {
        String[] sentences = text.split("[.!?]");
        sentenceCounter = sentences.length;
        for (String sentence : sentences) {
            String[] words = sentence.trim().split(" ");
            wordCounter += words.length;
            for (String word : words) {
                int syllablesInWord = countSyllables(word);
                syllables += syllablesInWord;
                if (syllablesInWord > 2) {
                    polysyllables++;
                }
            }
        }
        for (char c : text.toCharArray()) {
            if (c != '\n' && c != '\t' && c != ' ') {
                charCounter++;
            }
        }
    }

    private int countSyllables(String word) {
        if (word.length() == 1) {
            return 1;
        }
        int count = 0;
        boolean prevIsVowel = false;
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('y');
        word = word.toLowerCase();
        if (word.charAt(word.length() - 1) == 'e') {
            word = word.substring(0, word.length() - 1);
        }
        for (char c : word.toCharArray()) {
            boolean isVowel = vowels.contains(c);
            if (isVowel && !prevIsVowel) {
                count++;
            }
            prevIsVowel = isVowel;
        }
        return count;
    }
}
