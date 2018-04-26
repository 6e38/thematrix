
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Matrix extends JPanel
{
  private int width;
  private int height;

  public Matrix(int theWidth, int theHeight)
  {
    super();

    width = theWidth;
    height = theHeight;
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    draw(g2d);
    g2d.dispose();
  }

  private void draw(Graphics2D g)
  {
    g.setColor(Color.DARK_GRAY);
    g.fillRect(0, 0, width - 10, height - 10);
    g.setColor(Color.LIGHT_GRAY);
    g.drawString("foo", 40, 40);
  }
}

