package Frame;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Main.GradeP;

public class SuperFrame extends JFrame{	
	private AttendPanel AttendP;
	private GradePanel GradeP;
	private StudentPanel StudentP;
	private LecturePanel LectureP;
	private StaticPanel StaticP;
	
	public SuperFrame() {		
		AttendP = new AttendPanel();
		GradeP = new GradePanel();
		StudentP = new StudentPanel();
		LectureP = new LecturePanel();
		StaticP = new StaticPanel();
		
		this.add(AttendP, BorderLayout.CENTER);
		//this.add(GradeP, BorderLayout.CENTER);
		//this.add(StudentP, BorderLayout.CENTER);
		//this.add(LectureP, BorderLayout.CENTER);
		//this.add(StaticP, BorderLayout.CENTER);
		
		this.setJMenuBar(new MenuBar());
		this.setTitle("己利贸府");
		this.setSize(1200, 800);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
}
