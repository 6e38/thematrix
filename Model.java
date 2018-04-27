
public class Model
{
  private int cols;
  private int rows;
  private char[][] data;

  public Model(int theCols, int theRows)
  {
    cols = theCols;
    rows = theRows;

    data = new char[rows][cols];
    for (int y = 0; y < rows; y++)
    {
      for (int x = 0; x < cols; x++)
      {
        data[y][x] = ' ';
      }
    }
  }

  public void setChar(char c, int x, int y)
  {
    if (y >= rows)
    {
      y %= rows;
    }
    data[y][x] = c;
  }

  public char[][] getData()
  {
    return data;
  }
}

