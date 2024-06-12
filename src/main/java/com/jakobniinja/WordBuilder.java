package com.jakobniinja;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class WordBuilder extends JFrame {

  private static final long serialVersionUID = 1L;

  private static final int ROWS = 8;

  private static final int COLS = 12;

  private static final int MAX = 15;

  private LetterPanel[][] board = new LetterPanel[ROWS][COLS];

  private JPanel mainPanel = new JPanel();

  private JPanel boardPanel = new JPanel();

  public WordBuilder() {
    initGUI();

    setTitle("Word Builder");
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  private void initGUI() {
    JLabel titleLabel = new JLabel("Word Builder");
    add(titleLabel, BorderLayout.PAGE_START);

    // main panel
    add(mainPanel, BorderLayout.CENTER);

    // score panel

    // play panel

    // board panel
    boardPanel.setBackground(Color.BLACK);
    boardPanel.setLayout(new GridLayout(ROWS, COLS));
    mainPanel.add(boardPanel);

    BagOfLetters letters = new BagOfLetters();
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        LetterPanel letterPanel = letters.pickALetter();
        board[row][col] = letterPanel;
        boardPanel.add(letterPanel);

      }
    }
    
    // button panel

    // listeners

  }

  public static void main(String[] args) {
    String className = UIManager.getCrossPlatformLookAndFeelClassName();
    try {
      UIManager.setLookAndFeel(className);
    } catch (Exception e) {
      //
    }
    EventQueue.invokeLater(WordBuilder::new);
  }
}
