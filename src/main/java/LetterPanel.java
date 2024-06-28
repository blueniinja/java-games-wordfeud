import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LetterPanel extends JPanel {

  private static final long serialVersionUID = 1L;

  private static final Color BROWN = new Color(49, 22, 3);

  private static final String IMAGE_NAME = "/WoodTile.jpg";

  private String letter = "";

  private int points = -1;

  private int column = -1;

  private int size = 40;

  private transient BufferedImage image;

  private Font bigFont = new Font(Font.DIALOG, Font.BOLD, size * 3 / 4);

  private Font smallFont = new Font(Font.DIALOG, Font.BOLD, size * 3 / 10);

  private FontMetrics bigFM;

  private FontMetrics smallFM;

  public LetterPanel(String letter, int points) {
    this.letter = letter;
    this.points = points;
    initPanel();
  }

  public LetterPanel() {
    initPanel();
  }

  private void initPanel() {
    if (image == null) {
      try {
        InputStream input = getClass().getResourceAsStream(IMAGE_NAME);
        if (input != null) {

          image = ImageIO.read(input);
        }
      } catch (IOException e) {
        String message = "Couldn't open file " + IMAGE_NAME + ".";
        JOptionPane.showMessageDialog(null, message);
      }
    }
    bigFM = getFontMetrics(bigFont);
    smallFM = getFontMetrics(smallFont);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (letter.isEmpty()) {
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
      int letterWidth = bigFM.stringWidth(letter);
      int x = (size - letterWidth) / 2;
      int y = size * 3 / 4;
      g.drawString(letter, x, y);
      g.setFont(smallFont);
      letterWidth = smallFM.stringWidth("" + points);
      x = size - letterWidth - 2;
      y = size * 17 / 20;
      g.drawString("" + points, x, y);
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(size, size);
  }

  public String getLetter() {
    return letter;
  }

  public int getPoints() {
    return points;
  }

  public int getColumn() {
    return column;
  }

  public int getPanelSize() {
    return size;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public void setEmpty() {
    letter = "";
    points = -1;
    repaint();
  }

  public boolean isEmpty() {
    return (points == -1);
  }

  public void copy(LetterPanel letterPanel2) {
    letter = letterPanel2.getLetter();
    points = letterPanel2.getPoints();
    column = letterPanel2.getColumn();
    repaint();
  }

  public void resize(int size) {
    this.size = size;
    bigFont = new Font(Font.DIALOG, Font.BOLD, size * 3 / 4);
    smallFont = new Font(Font.DIALOG, Font.BOLD, size * 3 / 10);
    bigFM = getFontMetrics(bigFont);
    smallFM = getFontMetrics(smallFont);
    repaint();
    revalidate();
  }
}