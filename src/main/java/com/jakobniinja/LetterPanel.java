package com.jakobniinja;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class LetterPanel extends JFrame {

  private static final long serialVersionUID = 1L;

  private static final Color BROWN = new Color(49, 22, 3);

  private static final String IMAGE_NAME = "WoodTile.jpg";

  private String letter = "";

  private int points = -1;

  private BufferedImage image = null;

  private int size = 40;

  private Font bigFont = new Font(Font.DIALOG, Font.BOLD, 30);

  private Font smallFont = new Font(Font.DIALOG, Font.BOLD, 12);


  public LetterPanel() {
  }
}
