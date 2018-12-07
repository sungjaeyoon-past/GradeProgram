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
		LectureP = new LecturePanel(GradeP);
		StaticP = new StaticPanel(GradeP);
			
		getContentPane().add(IntroP);
		this.setJMenuBar(new MenuBar(this));

	}
	public void change(String panelName) {	
		if(panelName.equals("gradeP")) {	
			getContentPane().removeAll();
			GradeP = new GradePanel();
			getContentPane().add(GradeP);	
			revalidate();	
			repaint();	
		}else if(panelName.equals("attendP")){	
			getContentPane().removeAll();	
			AttendP = new AttendPanel();
			getContentPane().add(AttendP);	
			revalidate();	
			repaint();	
		}else if(panelName.equals("staticP")){	
			getContentPane().removeAll();	
			StaticP = new StaticPanel(GradeP);
			getContentPane().add(StaticP);	
			revalidate();	
			repaint();	
		}else if(panelName.equals("studentP")){	
			getContentPane().removeAll();
			StudentP = new StudentPanel();
			getContentPane().add(StudentP);	
			revalidate();	
			repaint();	
		}else if(panelName.equals("lectureP")){	
			getContentPane().removeAll();
			LectureP = new LecturePanel(GradeP);
			getContentPane().add(LectureP);	
			revalidate();	
			repaint();	
		}else if(panelName.equals("IntroP")){	
			getContentPane().removeAll();	
			getContentPane().add(IntroP);	
			revalidate();	
			repaint();	
		}		
	}
}