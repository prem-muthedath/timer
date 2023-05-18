import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridLayout;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * this is an example of Grid Layout usage.
 *
 * author: Prem Muthedath
 */
public class  Grid {
  private JFrame frame=new JFrame("Test Timings");

  public void render()  {
    resetDisplay();
    renderHeading("   ");
    renderHeading("size=1");
    renderHeading("size=10");
    renderHeading("size=100");
    renderHeading("size=1000");
    renderHeading("size=10000");
    renderHeading("size=100000");

    renderHeading("arrayListIteration");
    renderText("3.40");
    renderText("3.61");
    renderText("3.66");
    renderText("3.43");
    renderText("4.23");
    renderText("4.27");

    renderHeading("arrayListMembership");
    renderText("6.38");
    renderText("25.74");
    renderText("214.62");
    renderText("2074.60");
    renderText("28534.43");
    renderText("822573.24");

    renderHeading("arrayListModification");
    renderText("9.41");
    renderText("25.85");
    renderText("165.06");
    renderText("2003.07");
    renderText("34228.37");
    renderText("1279668.95");

    renderHeading("setIteration");
    renderText("7.01");
    renderText("46.15");
    renderText("698.90");
    renderText("8141.00");
    renderText("115864.56");
    renderText("11325546.88");

    renderHeading("setMembership");
    renderText("9.13");
    renderText("10.72");
    renderText("11.49");
    renderText("11.78");
    renderText("13.31");
    renderText("13.33");

    renderHeading("setModification");
    renderText("23.38");
    renderText("23.74");
    renderText("24.55");
    renderText("24.99");
    renderText("23.46");
    renderText("22.53");

    frame.pack();
    frame.setVisible(true);
  }

  private void resetDisplay() {
    frame.getContentPane().removeAll();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new GridLayout(7, 0));
  }

  public void renderHeading(String heading) {
    frame.getContentPane().add(new JLabel(heading));
  }

  public void renderText(String text) {
    frame.getContentPane().add(new JTextField(text));
  }

  public static void main(String args[]) {
    new Grid().render();
  }
}

/* sample test timings
 *                          size=1                   size=10                  size=100                 size=1000                size=10000               size=100000
 * arrayListIteration       3.40                     3.61                     3.66                     3.43                     4.23                     4.27
 * arrayListMembership      6.38                     25.74                    214.62                   2074.60                  28530.43                 822573.24
 * arrayListModification    9.41                     24.85                    165.06                   2003.07                  34220.37                 1279668.95
 * setIteration             7.01                     46.15                    698.90                   8141.00                  115864.56                1325546.88
 * setMembership            9.13                     10.72                    11.49                    11.78                    13.31                    13.33
 * setModification          23.38                    23.74                    24.55                    24.99                    23.46                    22.53 */
