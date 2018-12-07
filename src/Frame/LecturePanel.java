package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import Main.GradeRatio;
import Main.Lecture;

public class LecturePanel extends JPanel implements ActionListener{
	private Lecture myLecture;
	GradePanel gpp;
	

	public LecturePanel(GradePanel gpp) {
		this.gpp=gpp;
		this.myLecture = new Lecture("고급 객체 지향 프로그래밍",1333,3,"전공","장희정","Y5437",gpp.getG().getCountStudent()-1,"Java J2SE를 기반으로 하는 객체지향 이론\r\n" + 
				"객체지향 설계 원칙 및 디자인 패턴\r\n" + 
				"Swing 및 JDBC를 이용한 객체지향 설계");
		setLayout(null);
		setBackground(Color.white);
		
		creatInfoPanel();    //1.강의 정보 패널
		creatIntroPanel();  //2.강의 소개 패널	
	
	}
	
	public void creatInfoPanel() { //1.강의 정보 패널
		String str[]= {"강의 ","학점","구분","담당교수","강의실","강좌번호","학생 수"};
		Object obj[]= {myLecture.getName(),myLecture.getScore(),myLecture.getDivision(),
				myLecture.getProfessor(),myLecture.getRoomNumber(),myLecture.getLectureNumber(),myLecture.getCount()};
		
		for(int i=0;i<7;i++) {			
			JLabel j = new JLabel(str[i]+"- "+obj[i]);
			j.setFont(new Font("a개미야", Font.BOLD, 35));
			j.setBounds(100, 50*i+ 150 , 500, 50);
			add(j);
		}	
	}

	public void creatIntroPanel() {  //2.강의 소개 패널
		JLabel j = new JLabel("강의 소개");
		j.setFont(new Font("a개미야", Font.BOLD, 45));
		j.setBounds(500, 50, 500, 50);
		add(j);
		
		JTextArea i = new JTextArea(myLecture.getIntroduce());
		i.setEditable(false);
		i.setEditable(false);
		i.setFont(new Font("a개미야", Font.BOLD, 30));
		i.setBounds(560, 150, 550, 130);
		add(i);
		
		ImageIcon ic = new ImageIcon("image/presentation.png");
		JLabel icimage=new JLabel(ic);
		icimage.setBounds(540, 300, 300, 300);
		add(icimage);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
