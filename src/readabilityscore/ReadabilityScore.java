/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readabilityscore;

import api.*;

/**
 *
 * @author George.Giazitzis
 */
public class ReadabilityScore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String textInput = new ArgumentHandling(args).argsHandler();
        TextEvaluator textEval = new TextEvaluator(textInput);
        ReadabilityIndex readIndex = new ReadabilityIndex(textEval);
        readIndex.printResults();
    }
}
