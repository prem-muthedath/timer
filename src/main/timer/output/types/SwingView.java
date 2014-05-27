package timer.output.types;

import timer.output.base.View;
import timer.output.base.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridLayout;

public class SwingView extends View {
	private JFrame frame=new JFrame("Test Timings");

	public SwingView() {
		super(new TextFormat());
	}
	
	public void render(Component component)  {
		resetDisplay(component);
		super.render(component);
		frame.pack();
		frame.setVisible(true);
	}	

	private void resetDisplay(Component component) {
		frame.getContentPane().removeAll();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
		frame.getContentPane().setLayout(new GridLayout(component.size(), 0));  //? how to get the column count?
	}

	public void renderHeading(String heading) {
		frame.getContentPane().add(new JLabel(heading));
	}

	public void renderText(String text) {
		frame.getContentPane().add(new JTextField(text));
	}	
}