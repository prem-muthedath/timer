package timer.reporting.rendering;

import timer.reporting.base.View;
import timer.reporting.base.Component;

import timer.reporting.composition.TextDocument;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridLayout;

public class SwingView extends View {
	private JFrame frame=new JFrame("Test Timings");

	public SwingView() {
		super(new TextDocument());
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
		frame.getContentPane().setLayout(new GridLayout(component.size(), 0));  
	}

	public void renderHeading(String heading) {
		frame.getContentPane().add(new JLabel(heading));
	}

	public void renderText(String text) {
		frame.getContentPane().add(new JTextField(text));
	}	
}