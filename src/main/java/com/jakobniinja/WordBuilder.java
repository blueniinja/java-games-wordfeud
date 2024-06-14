package com.jakobniinja;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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

  private static final String FILENAME = "highScore.txt";

  private static final Color TAN = new Color(222, 191, 168);

  private static final Font SMALLFONT = new Font(Font.DIALOG, Font.PLAIN, 12);

  private static final Font BIGFONT = new Font(Font.DIALOG, Font.BOLD, 30);

  private LetterPanel[][] board = new LetterPanel[ROWS][COLS];

  private LetterPanel[] played = new LetterPanel[MAX];

  private int points = 0;

  private int score = 0;

  private String word = "";

  Dictionary dictionary = new Dictionary();

  private JPanel mainPanel = new JPanel();

  private JPanel boardPanel = new JPanel();

  private JPanel scorePanel = new JPanel();

  private JPanel playPanel = new JPanel();

  private JLabel pointsTitleLabel = new JLabel("Points: ");

  private JLabel scoreTitleLabel = new JLabel("Score: ");

  private JLabel pointsLabel = new JLabel("0");

  private JLabel scoreLabel = new JLabel("0");

  private JButton acceptButton = new JButton("Accept");

  private JButton undoButton = new JButton("Undo");

  private JButton clearButton = new JButton("Clear");


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
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
    mainPanel.setBackground(TAN);
    add(mainPanel, BorderLayout.CENTER);

    // score panel
    scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
    scorePanel.setBackground(TAN);
    mainPanel.add(scorePanel);

    pointsTitleLabel.setFont(SMALLFONT);
    scorePanel.add(pointsTitleLabel);

    pointsLabel.setFont(BIGFONT);
    scorePanel.add(pointsLabel);

    Dimension boxSize = new Dimension(20, 0);
    Component box = Box.createRigidArea(boxSize);
    scorePanel.add(box);

    scoreTitleLabel.setFont(SMALLFONT);
    scorePanel.add(scoreTitleLabel);

    scoreLabel.setFont(BIGFONT);
    scorePanel.add(scoreLabel);

    // play panel
    playPanel.setLayout(new GridLayout(1, MAX));
    playPanel.setBackground(TAN);
    mainPanel.add(playPanel);

    for (int i = 0; i < MAX; i++) {
      LetterPanel letterPanel = new LetterPanel();
      played[i] = letterPanel;
      playPanel.add(letterPanel);
    }

    // board panel
    boardPanel.setBackground(Color.BLACK);
    boardPanel.setLayout(new GridLayout(ROWS, COLS));
    int panelSize = played[0].getPanelSize();
    Dimension maxSize = new Dimension(COLS * panelSize, ROWS * panelSize);
    boardPanel.setMaximumSize(maxSize);
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
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.BLACK);
    mainPanel.add(buttonPanel, BorderLayout.PAGE_END);

    acceptButton.setEnabled(false);
    acceptButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        accept();
      }
    });

    buttonPanel.add(acceptButton);

    undoButton.setEnabled(false);
    buttonPanel.add(undoButton);

    clearButton.setEnabled(false);
    buttonPanel.add(clearButton);

    JButton endButton = new JButton("End Game");
    buttonPanel.add(endButton);

    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        LetterPanel letterPanel = letters.pickALetter();
        letterPanel.setColumn(col);
        board[row][col] = letterPanel;
        boardPanel.add(letterPanel);
      }
    }

    for (int col = 0; col < COLS; col++) {
      board[0][col].addMouseListener(new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {
          LetterPanel letterPanel = (LetterPanel) e.getSource();
          click(letterPanel);
        }
      });

    }

    // listeners

  }

  private void accept() {
    int newPoints = points * word.length();
    score += newPoints;
    scoreLabel.setText(""+score);

    for (int i = 0; i < word.length(); i++) {
      played[i].setEmpty();
    }
    points =0;
    word = "";
    updateButtonsAndPoints();
  }

  private void click(LetterPanel letterPanel) {
    int wordLength = word.length();
    if (!letterPanel.isEmpty() && wordLength < MAX) {
      played[wordLength].copy(letterPanel);
      word += letterPanel.getLetter();
      points += letterPanel.getPoints();

      int col = letterPanel.getColumn();
      for (int row = 0; row < ROWS - 1; row++) {
        board[row][col].copy(board[row + 1][col]);
      }
      board[ROWS - 1][col].setEmpty();
      updateButtonsAndPoints();
    }
  }

  private void updateButtonsAndPoints() {
    if (word.length() == 0) {
      acceptButton.setEnabled(false);
      undoButton.setEnabled(false);
      clearButton.setEnabled(false);
      pointsLabel.setText("0");
    } else if (word.length() < 3) {
      acceptButton.setEnabled(false);
      undoButton.setEnabled(true);
      clearButton.setEnabled(true);
      pointsLabel.setText("0");
    } else {
      if (dictionary.isAWord(word)) {
        acceptButton.setEnabled(true);
      } else {
        acceptButton.setEnabled(false);
      }

      undoButton.setEnabled(true);
      clearButton.setEnabled(true);
      int newPoints = points * word.length();
      pointsLabel.setText(""+ newPoints);
    }
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
