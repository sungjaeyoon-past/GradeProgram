package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import Main.GradeRatio;
import Main.Lecture;

public class LecturePanel extends TopPanel {
	private Lecture myLecture;
	private static GradeRatio myRatio;	
	
	static public GradeRatio getMyRatio() {
		return myRatio;
	}

	public LecturePanel() {
		this.myRatio = new GradeRatio(15,15,20,20,10,10,10,0,0);
		this.myLecture = new Lecture("고급 객체 지향 프로그래밍",1333,3,"전공","장희정","Y5437",40,"Java J2SE를 기반으로 하는 객체지향 이론\r\n" + 
				"객체지향 설계 원칙 및 디자인 패턴\r\n" + 
				"Swing 및 JDBC를 이용한 객체지향 설계");
		setBackground(Color.WHITE);
		setLayout(null);
		
		creatInfoPanel();    //1.강의 정보 패널
		creatIntroPanel();  //2.강의 소개 패널	
		creatRatioPanel();  //3.비율 패널	
		
	}
	
	public void creatInfoPanel() { //1.강의 정보 패널
		String str[]= {"강의 ","학점","구분","담당교수","강의실","강좌번호","학생수"};
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
		i.setFont(new Font("a개미야", Font.BOLD, 30));
		i.setBounds(560, 230, 550, 200);
		add(i);
	}
	
	public void creatRatioPanel() { //3.비율 패널
		JLabel j = new JLabel("비율 설정");
		j.setFont(new Font("a개미야", Font.BOLD, 40));
		j.setBounds(500, 470, 500, 100);
		add(j);
		
		String col[]= {"A+","A","B+","B","C+","C","D+","D","F"};
		Object[][] row= {{myRatio.getaPlusRatio(),myRatio.getaRatio(),myRatio.getbPlusRatio(),myRatio.getbRatio(),
			myRatio.getcPlusRatio(),myRatio.getcRatio(),myRatio.getdPlusRatio(),myRatio.getdRatio(),myRatio.getfRatio()}};
		JTable t=new JTable(row,col);
		JScrollPane jsp = new JScrollPane(t);
		jsp.setBounds(290, 600, 500, 42);
		add(jsp);
		
		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myRatio.setaPlusRatio(t.getValueAt(0, 0));
				myRatio.setaRatio(t.getValueAt(0, 1));
				myRatio.setbPlusRatio(t.getValueAt(0, 2));
				myRatio.setbRatio(t.getValueAt(0, 3));
				myRatio.setcPlusRatio(t.getValueAt(0, 4));
				myRatio.setcRatio(t.getValueAt(0, 5));
				myRatio.setdPlusRatio(t.getValueAt(0, 6));
				myRatio.setdRatio(t.getValueAt(0, 7));
				myRatio.setfRatio(t.getValueAt(0, 8));
			}
		};
		
		JButton b=new JButton("저장");
		b.setBounds(789, 600, 70, 42);
		b.addActionListener(l);
		add(b);
	}
	
	
}
