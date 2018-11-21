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
	MenuBar bar;
	public SuperFrame() {		
		bar = new MenuBar();
		IntroP = new IntroPanel();
		AttendP = new AttendPanel();
		GradeP = new GradePanel();
		StudentP = new StudentPanel();
		LectureP = new LecturePanel();
		StaticP = new StaticPanel();
		
		this.setJMenuBar(new MenuBar());
		this.setTitle("己利贸府 橇肺弊伐 version.1");
		this.setSize(1200, 800);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.add(IntroP);
		//this.add(AttendP, BorderLayout.CENTER);
		//this.add(GradeP, BorderLayout.CENTER);

		//this.add(LectureP, BorderLayout.CENTER);
		//this.add(StaticP, BorderLayout.CENTER);

	}
	public void checkPanel() {
		System.out.println(bar.test);
		if(bar.test==1) {
			System.out.println("test");
			this.add(StudentP, BorderLayout.CENTER);
		}
	}
}
