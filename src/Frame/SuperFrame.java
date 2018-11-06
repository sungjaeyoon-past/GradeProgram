package Frame;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SuperFrame extends JFrame{
	ImageIcon icon;
	JScrollPane scrollPane;
	
	public SuperFrame() {
		//Container c = this.getContentPane();		
		//JPanel p = new JPanel();
		//p.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		icon = new ImageIcon("../images/mainImage.PNG");
		JPanel p1 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		scrollPane = new JScrollPane(p1);
		setContentPane(scrollPane);


		this.setJMenuBar(new MenuBar());
		this.setTitle("성적 관리 프로그램");
		this.setSize(1200, 800);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
}
