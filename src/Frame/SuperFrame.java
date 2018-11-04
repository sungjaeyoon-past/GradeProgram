package Frame;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SuperFrame extends JFrame{
	public SuperFrame() {
		Container c = this.getContentPane();		
		JPanel p =new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		this.setJMenuBar(new MenuBar());
		this.setTitle("己利 包府 橇肺弊伐");
		this.setSize(1200,800);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
}
