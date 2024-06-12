package com.jakobniinja;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
    initPanel();
  }

  public LetterPanel(String letter, int points) throws HeadlessException {
    this.letter = letter;
    this.points = points;
    initPanel();
  }

  private void initPanel() {
    if (image == null) {
      try {
        image = ImageIO.read(new File(IMAGE_NAME));
      } catch (IOException e) {
        String message = "Couldn't open file name: " + IMAGE_NAME;
        JOptionPane.showMessageDialog(null, message);
      }
    }
  }

  public void paintComponent(Graphics g) {
    if (letter.length() == 0) {
      g.setColor(BROWN);
      g.fillRect(0, 0, size, size);
    } else {

      if (image == null) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, size, size);
      } else {
        g.drawImage(image, 0, 0, this);
      }
      g.setColor(Color.BLACK);
      g.drawRect(0, 0, size - 1, size - 1);

      g.setFont(bigFont);
      int x = 5;
      int y = size * 3 / 4;
      g.drawString(letter, x, y);

      g.setFont(smallFont);
      x = size - 12;
      y = size * 17 / 20;
      g.drawString(""+points, x,y);
    }
  }

  public Dimension getPreferredSize() {
    Dimension dimension = new Dimension(size, size);

    return dimension;
  }
}
