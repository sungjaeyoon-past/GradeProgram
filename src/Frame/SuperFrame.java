package Frame;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Main.GradeP;

public class SuperFrame extends JFrame{	
	public SuperFrame() {
		//Container c = this.getContentPane();	
		//JPanel p = new JPanel();
		//p.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		this.setJMenuBar(new MenuBar());
		this.setTitle("己利贸府");
		this.setSize(1200, 800);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
}
