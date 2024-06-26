import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TitleLabel extends JLabel {
  private static final long serialVersionUID = 1L;
  private static final Font TITLE_FONT = new Font(Font.DIALOG, Font.BOLD, 36);
  private static final Color TITLE_COLOR = new Color(50, 50, 50); // Dark gray color

  public TitleLabel(String text) {
    super(text);
    setFont(TITLE_FONT);
    setForeground(TITLE_COLOR);
    setHorizontalAlignment(SwingConstants.CENTER);
    setVerticalAlignment(SwingConstants.CENTER);
  }
}
