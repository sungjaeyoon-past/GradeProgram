package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	private GradeRatio myRatio;
	
	public LecturePanel() {
		this.myRatio = new GradeRatio(15,15,20,20,10,10,0,0,0);
		this.myLecture = new Lecture("고급 객체 지향 프로그래밍",1333,3,"전공","장희정","Y5437",40,"Java J2SE를 기반으로 하는 객체지향 이론\r\n" + 
				"객체지향 설계 원칙 및 디자인 패턴\r\n" + 
				"Swing 및 JDBC를 이용한 객체지향 설계");
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel infoP = creatInfoPanel();    //1.강의 정보 패널
		JPanel introP = creatIntroPanel();  //2.강의 소개 패널	
		JPanel ratioP = creatRatioPanel();  //3.비율 패널	

		this.add(infoP);
		this.add(introP);
		this.add(ratioP);
	}
	
	public JPanel creatInfoPanel() { //1.강의 정보 패널
		JPanel jp=new JPanel();
		String str[]= {"강의 ","학점","구분","담당교수","강의실","강좌번호","학생수"};
		Object obj[]= {myLecture.getName(),myLecture.getScore(),myLecture.getDivision(),
				myLecture.getProfessor(),myLecture.getRoomNumber(),myLecture.getLectureNumber(),myLecture.getCount()};
		
		for(int i=0;i<7;i++) {			
			JLabel j = new JLabel(str[i]+"- "+obj[i]);
			j.setFont(new Font("a개미야", Font.BOLD, 35));
			j.setBounds(100, 50*i+ 150 , 500, 50);
			add(j);
		}	
		
		return jp;
	}

	public JPanel creatIntroPanel() {  //2.강의 소개 패널
		JPanel jp=new JPanel();
		JLabel j = new JLabel("강의 소개");
		j.setFont(new Font("a개미야", Font.BOLD, 45));
		j.setBounds(500, 50, 500, 50);
		add(j);
		
		JTextArea i = new JTextArea(myLecture.getIntroduce());
		i.setEditable(false);
		i.setFont(new Font("a개미야", Font.BOLD, 30));
		i.setBounds(560, 230, 550, 200);
		add(i);
			
		return jp;
	}
	
	public JPanel creatRatioPanel() { //3.비율 패널
		JPanel jp=new JPanel();

		JLabel j = new JLabel("비율 설정");
		j.setFont(new Font("a개미야", Font.BOLD, 40));
		j.setBounds(500, 470, 500, 100);
		add(j);
		
		String col[]= {"A+","A","B+","B","C+","C","D+","D","F"};
		Object[][] row= {{myRatio.getaPlusRatio(),myRatio.getaRatio(),myRatio.getbPlusRatio(),myRatio.getbRatio(),
			myRatio.getcPlusRatio(),myRatio.getcRatio(),myRatio.getdPlusRatio(),myRatio.getdRatio(),myRatio.getfRatio()}};
		JTable t=new JTable(row,col);
		JScrollPane jsp = new JScrollPane(t);
		jsp.setBounds(310, 600, 500, 42);
		add(jsp);
		
		JButton b=new JButton("저장");
		b.setBounds(809, 600, 70, 42);
		add(b);
		
		return jp;
	}
	
}
