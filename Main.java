
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main
{
  public static void main(String args[])
  {
    new Main();
  }

  public Main()
  {
    SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run()
        {
          GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
          GraphicsDevice[] gd = ge.getScreenDevices();

          JFrame frame = new JFrame("");

          gd[0].setFullScreenWindow(frame);

          Matrix matrix = new Matrix(frame);
          frame.getContentPane().add(matrix);
          frame.getContentPane().setCursor(BlankCursor.getCursor());
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setVisible(true);

          Thread thread = new Engine(matrix);
          thread.start();
        }
    });
  }
}

