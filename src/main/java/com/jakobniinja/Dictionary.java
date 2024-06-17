package com.jakobniinja;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dictionary {

  private static final String FILENAME = "enable1_3-15.txt";

  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  @SuppressWarnings("unchecked")
  private ArrayList<String>[] wordList = (ArrayList<String>[]) new ArrayList[26];


  public Dictionary() {
    for (int i = 0; i < 26; i++) {
      wordList[i] = new ArrayList<String>();
    }

    try (BufferedReader in = new BufferedReader(new FileReader(FILENAME))) {
      String word = in.readLine();

      while (word != null) {
        char letter = word.charAt(0);
        int list = ALPHABET.indexOf(letter);
        wordList[list].add(word);
        word = in.readLine();

      }
    } catch (FileNotFoundException e) {
      String message = "File " + FILENAME + " was not found.";
      JOptionPane.showMessageDialog(null, message);


    } catch (IOException e) {
      String message = "File " + FILENAME + " could not be opened.";
      JOptionPane.showMessageDialog(null, message);
    }
  }

  public boolean isAWord(String word) {
    boolean found = false;
    word = word.toUpperCase();
    char letter = word.charAt(0);
    int list = ALPHABET.indexOf(letter);
    int index = 0;
    String word2 = "";

    while (index < wordList[list].size()
        && word2.compareTo(word) < 0
        && !found) {
      word2 = wordList[list].get(index);
      if (word2.equals(word)) {
        found = true;
      }
      index++;
    }

    return found;
  }

  public static void main(String[] args) {
    Dictionary dictionary = new Dictionary();
    String word = "Hello";
    if (dictionary.isAWord(word)) {
      System.out.println("word is " + word);
    } else {
      System.out.println(word + " is not a word.");
    }
    // TODO: Complete Listing 11-70
  }
}
