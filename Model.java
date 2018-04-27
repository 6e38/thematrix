
public class Model
{
  public static final int Green = 0;
  public static final int Red = 1;
  private static final int MaxColors = 2;

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
    color[y][x] = theColor % MaxColors;
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

