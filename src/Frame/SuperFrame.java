package Frame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	private IntroPanel IntroP;
	
	public SuperFrame() {		
		IntroP = new IntroPanel();
		AttendP = new AttendPanel();
		GradeP = new GradePanel();
		StudentP = new StudentPanel();
		LectureP = new LecturePanel();
		StaticP = new StaticPanel();
			
		getContentPane().add(IntroP);
		this.setJMenuBar(new MenuBar(this));
		
		//this.add(AttendP, BorderLayout.CENTER);
		//this.add(GradeP, BorderLayout.CENTER);
		//this.add(LectureP, BorderLayout.CENTER);
		//this.setVisible(true);
		//this.add(StaticP, BorderLayout.CENTER);
		
	}
	public void change(String panelName) {
		if(panelName.equals("gradeP")) { //己利贸府
			getContentPane().removeAll();
			getContentPane().add(GradeP);
			revalidate();
			repaint();
			
		}else if(panelName.equals("attendP")){ //免籍贸府
			getContentPane().removeAll();
			getContentPane().add(AttendP);
			revalidate();
			repaint();
			
		}else if(panelName.equals("staticP")){ //烹拌贸府
			getContentPane().removeAll();
			getContentPane().add(StaticP);
			revalidate();
			repaint();
			
		}else if(panelName.equals("studentP")){ //切积贸府
			getContentPane().removeAll();
			getContentPane().add(StudentP);
			revalidate();
			repaint();
			
		}else if(panelName.equals("lectureP")){ //碍狼贸府
			getContentPane().removeAll();
			getContentPane().add(LectureP);
			revalidate();
			repaint();
		}
	}
}

