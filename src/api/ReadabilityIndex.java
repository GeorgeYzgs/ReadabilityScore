/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.util.Scanner;

/**
 *
 * @author George.Giazitzis
 */
public class ReadabilityIndex {

    private TextEvaluator text;

    public ReadabilityIndex(TextEvaluator text) {
        this.text = text;
    }

    private double automatedReadabilityIndex() {
        double score = 4.71 * text.getCharCounter() / text.getWordCounter() + 0.5 * text.getWordCounter() / text.getSentenceCounter() - 21.43;
        System.out.printf("Automated Readability Index: %.2f (about %s  year olds).\n", score, ageGroup((int) score));
        return score;
    }

    private double fleschKincaidReadabilityTests() {
        double score = 0.39 * text.getWordCounter() / text.getSentenceCounter() + 11.8 * text.getSyllables() / text.getWordCounter() - 15.59;
        System.out.printf("Flesch–Kincaid readability tests: %.2f (about %s  year olds).\n", score, ageGroup((int) score));
        return score;
    }

    private double smogIndex() {
        double score = 1.043 * Math.sqrt(text.getPolysyllables() * 30.0 / text.getSentenceCounter()) + 3.1291;
        System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s  year olds).\n", score, ageGroup((int) score));
        return score;
    }

    private double colemanLiauIndex() {
        double L = ((double) text.getCharCounter() / text.getWordCounter()) * 100.0;
        double S = ((double) text.getSentenceCounter() / text.getWordCounter()) * 100.0;
        double score = (0.0588 * L) - (0.296 * S) - 15.8;
        System.out.printf("Coleman–Liau index: %.2f (about %s  year olds).\n", score, ageGroup((int) score));
        return score;
    }

    private double showAllApproaches() {
        return (smogIndex() + colemanLiauIndex() + fleschKincaidReadabilityTests() + automatedReadabilityIndex()) / 4.0;
    }

    private String ageGroup(int score) {

        switch (score) {
            case 1:
                return "5-6";
            case 2:
                return "6-7";
            case 3:
                return "7-9";
            case 4:
                return "9-10";
            case 5:
                return "10-11";
            case 6:
                return "11-12";
            case 7:
                return "12-13";
            case 8:
                return "13-14";
            case 9:
                return "14-15";
            case 10:
                return "15-16";
            case 11:
                return "16-17";
            case 12:
                return "17-18";
            case 13:
                return "18-24";
            case 14:
            default:
                return "24+";

        }
    }

    public void printResults() {
        System.out.println("The text is:");
        System.out.println(text);
        System.out.println("\nWords: " + text.getWordCounter());
        System.out.println("Sentences: " + text.getSentenceCounter());
        System.out.println("Characters: " + text.getCharCounter());
        System.out.println("Syllables: " + text.getSyllables());
        System.out.println("Polysyllables: " + text.getPolysyllables() + "\n");
        System.out.println("This text should be understood in average by " + ageGroup(determineTypeOfScore()) + " year olds.");
    }

    private int determineTypeOfScore() {
        System.out.println("Enter the score you want to calculate (ATI, FK, SMOG, CL, all): ");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine().toUpperCase()) {
            case "ATI":
                return (int) automatedReadabilityIndex();
            case "FK":
                return (int) fleschKincaidReadabilityTests();
            case "SMOG":
                return (int) smogIndex();
            case "CL":
                return (int) colemanLiauIndex();
            case "ALL":
                return (int) showAllApproaches();
            default:
                return 0;
        }
    }
}
