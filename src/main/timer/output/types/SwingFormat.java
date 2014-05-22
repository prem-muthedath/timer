package timer.output.types;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridLayout;

public class SwingFormat extends TextFormat {
	private JFrame frame=new JFrame("Test Timings");

	public SwingFormat() {
		super();
	}

	public void print() {
		resetDisplay();
		super.print();
		frame.pack();
		frame.setVisible(true);
	}

	private void resetDisplay() {
		frame.getContentPane().removeAll();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
		frame.getContentPane().setLayout(new GridLayout(table().size(), 0));  //? how to get the column count?
	}

	protected void printHeading(String item)  {
		frame.getContentPane().add(new JLabel(item));
	}

	protected void printText(String item)  {
		frame.getContentPane().add(new JTextField(item));
	}
}
