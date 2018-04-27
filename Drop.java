
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Drop
{
  private static final String charset =
    "abcdefghijklmnopqrstuvwxyz" +
    "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
    "0123456789" +
    "!@#$%^&*()" +
    "`~-_=+[{]}\\|;:'\",<.>/?" +
    "\u00a1\u00a2\u00a3\u00a3\u00a4\u00a5\u00a6\u00a7" +
    "\u00a8\u00a9\u00aa\u00ab\u00ac\u00ad\u00ae\u00af" +
    "\u00b1\u00b2\u00b3\u00b3\u00b4\u00b5\u00b6\u00b7" +
    "\u00b8\u00b9\u00ba\u00bb\u00bc\u00bd\u00be\u00bf" +
    "\u00c1\u00c2\u00c3\u00c3\u00c4\u00c5\u00c6\u00c7" +
    "\u00c8\u00c9\u00ca\u00cb\u00cc\u00cd\u00ce\u00cf" +
    "\u00d1\u00d2\u00d3\u00d3\u00d4\u00d5\u00d6\u00d7" +
    "\u00d8\u00d9\u00da\u00db\u00dc\u00dd\u00de\u00df" +
    "\u00e1\u00e2\u00e3\u00e3\u00e4\u00e5\u00e6\u00e7" +
    "\u00e8\u00e9\u00ea\u00eb\u00ec\u00ed\u00ee\u00ef" +
    "\u00f1\u00f2\u00f3\u00f3\u00f4\u00f5\u00f6\u00f7" +
    "\u00f8\u00f9\u00fa\u00fb\u00fc\u00fd\u00fe\u00ff";
  private static ArrayList<String> specialStrings;
  private static boolean hasSpecial = false;

  private int updates;
  private int x;
  private int y;
  private int cols;
  private int rows;
  private int index;
  private int duration;
  private char[] message;
  private Model model;
  private boolean isSpecial;
  private int color;

  public Drop(int theCols, int theRows, Model theModel)
  {
    readSpecialStrings("special.txt");

    updates = 0;
    cols = theCols;
    rows = theRows;
    model = theModel;
    x = (int)(cols * Math.random());
    y = (int)(rows * Math.random());
    index = 0;
    isSpecial = false;
    color = Model.Green;

    if (!hasSpecial && specialStrings.size() > 0 && Math.random() < 0.05)
    {
      hasSpecial = true;
      isSpecial = true;
      color = Model.Red;

      message = specialStrings.get((int)(specialStrings.size() * Math.random())).toCharArray();
    }
    else
    {
      boolean blank = Math.random() < 0.50 ? true : false;

      int length = (int)((rows - 3) * Math.random()) + 3;
      message = new char[length];
      for (int i = 0; i < length; ++i)
      {
        if (blank)
        {
          message[i] = ' ';
        }
        else
        {
          message[i] = charset.charAt((int)(charset.length() * Math.random()));
        }
      }
    }

    duration = (int)(50 * Math.random()) + message.length;
  }

  public boolean update()
  {
    boolean complete = false;

    if (updates < message.length)
    {
      model.setChar(message[index++], x, y++, color);
    }
    else if (updates < duration)
    {
      model.setChar(charset.charAt((int)(charset.length() * Math.random())), x, y);
    }
    else
    {
      if (isSpecial)
      {
        hasSpecial = false;
      }
      complete = true;
    }

    ++updates;

    return complete;
  }

  private static void readSpecialStrings(String filename)
  {
    if (specialStrings == null)
    {
      specialStrings = new ArrayList<String>();

      try
      {
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        for (String line = reader.readLine(); line != null; line = reader.readLine())
        {
          specialStrings.add(line);
        }
      }
      catch (Exception e)
      {
        // Ignore all
      }
    }
  }
}

