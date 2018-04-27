
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Matrix extends JPanel
{
  private static final Color[] ColorMap = {
    new Color(0xff00ff00),
    new Color(0xff00f800),
    new Color(0xff00ef00),
    new Color(0xff00e800),
    new Color(0xff00df00),
    new Color(0xff00d800),
    new Color(0xff00cf00),
    new Color(0xff00c800),
    new Color(0xff00bf00),
    new Color(0xff00b800),
    new Color(0xffee0000),
  };

  private int width;
  private int height;
  private int charWidth;
  private int charHeight;
  private int offsetX;
  private int offsetY;
  private int rows;
  private int cols;

  private Model model;
  private Drop[] drop;

  private Font mainFont;

  public Matrix(JFrame parent)
  {
    super();

    width = parent.getWidth();
    height = parent.getHeight();

    mainFont = new Font(Font.MONOSPACED, Font.BOLD, 30);

    Graphics g = parent.getGraphics();
    g.setFont(mainFont);
    FontMetrics metrics = g.getFontMetrics();
    charWidth = metrics.charWidth('A');
    charHeight = metrics.getHeight();

    cols = width / charWidth;
    rows = height / charHeight;
    offsetX = (width % charWidth) / 2;
    offsetY = (height % charHeight) / 2 + metrics.getAscent();

    model = new Model(cols, rows);

    drop = new Drop[cols / 3];
    for (int i = 0; i < drop.length; i++)
    {
      drop[i] = new Drop(cols, rows, model);
    }
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    update();
    draw(g2d);
    g2d.dispose();
  }

  private void draw(Graphics2D g)
  {
    g.setColor(Color.DARK_GRAY);
    g.fillRect(0, 0, width, height);

    int lastColor = Model.Green;
    g.setColor(ColorMap[lastColor]);
    g.setFont(mainFont);

    char[][] data = model.getData();
    int[][] color = model.getColors();
    int cols = data[0].length;
    int rows = data.length;

    for (int y = 0; y < rows; y++)
    {
      for (int x = 0; x < cols; x++)
      {
        if (color[y][x] != lastColor)
        {
          lastColor = color[y][x];
          g.setColor(ColorMap[lastColor]);
        }

        g.drawChars(data[y], x, 1, x * charWidth + offsetX, y * charHeight + offsetY);
      }
    }
  }

  private void update()
  {
    for (int i = 0; i < drop.length; i++)
    {
      if (drop[i].update())
      {
        drop[i] = new Drop(cols, rows, model);
      }
    }
  }
}

