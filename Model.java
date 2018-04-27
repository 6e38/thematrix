
public class Model
{
  private static final int Green = 0xff00ff00;

  private int cols;
  private int rows;
  private char[][] data;
  private int[][] color;

  public Model(int theCols, int theRows)
  {
    cols = theCols;
    rows = theRows;

    data = new char[rows][cols];
    color = new int[rows][cols];
    for (int y = 0; y < rows; y++)
    {
      for (int x = 0; x < cols; x++)
      {
        data[y][x] = ' ';
        color[y][x] = Green;
      }
    }
  }

  public void setChar(char c, int x, int y)
  {
    setChar(c, x, y, Green);
  }

  public void setChar(char c, int x, int y, int theColor)
  {
    if (y >= rows)
    {
      y %= rows;
    }
    data[y][x] = c;
    color[y][x] = theColor;
  }

  public char[][] getData()
  {
    return data;
  }

  public int[][] getColors()
  {
    return color;
  }
}

