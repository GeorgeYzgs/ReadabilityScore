/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author George.Giazitzis
 */
public class ArgumentHandling {

    private String[] args;

    public ArgumentHandling(String[] args) {
        this.args = args;
    }

    public String argsHandler() {
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(new File(args[0]))) {
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not Found!");
        }
        return sb.toString();
    }
}
