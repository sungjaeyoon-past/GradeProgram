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

import Main.Lecture;

public class LecturePanel extends TopPanel {
	private Lecture myLecture;
	private JPanel ratio;
	public LecturePanel() {
		myLecture = new Lecture("고급 객체 지향 프로그래밍",1333,3,"전공","장희정","Y5437",40,"강의소개");
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
		String str[]= {"강의 이름 - ","학점 - ","구분 - ","담당교수 - ","강의실 - ","강좌번호 - ","학생수 - "};
		Object obj[]= {myLecture.getName(),myLecture.getScore(),myLecture.getDivision(),
				myLecture.getProfessor(),myLecture.getRoomNumber(),myLecture.getLectureNumber(),myLecture.getCount()};
		
		for(int i=0;i<7;i++) {			
			JLabel j = new JLabel(str[i]+ obj[i]);
			j.setFont(new Font("a개미야", Font.BOLD, 35));
			j.setBounds(100, 50*i+ 100 , 500, 50);
			add(j);
		}
		
		return jp;
	}

	public JPanel creatIntroPanel() {  //2.강의 소개 패널
		JPanel jp=new JPanel();
		
		JLabel j = new JLabel("강의 소개");
		j.setFont(new Font("a개미야", Font.BOLD, 35));
		j.setBounds(100, 50, 500, 50);
		
		return jp;
	}
	
	public JPanel creatRatioPanel() { //3.비율 패널
		JPanel jp=new JPanel();
		jp.add(new JButton("2"));
		return jp;
	}
	
}
