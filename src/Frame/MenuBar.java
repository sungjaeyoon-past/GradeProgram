package Frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuBar extends JMenuBar implements ActionListener{
	JMenuItem item, grade, student;

	static public int test = 0;
	KeyStroke key;
	public MenuBar() {
//파일관련
		JMenu fileMenu = new JMenu(" 파일");
		item = new JMenuItem("열기");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		fileMenu.setPreferredSize(new Dimension(80, 50));
		fileMenu.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		//fileMenu.setSize(100,50);
		fileMenu.add(item);
		
		item = new JMenuItem("저장");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		fileMenu.add(item);
		
		fileMenu.addSeparator();
		item = new JMenuItem("종료");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		fileMenu.add(item);
//성적
		JMenu gradeMenu = new JMenu(" 성적");
		gradeMenu.setPreferredSize(new Dimension(80, 50));
		gradeMenu.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		
		
		grade = new JMenuItem("성적 열기");
		grade.addActionListener(this);
		gradeMenu.add(grade);
		
		/*grade = new JMenuItem("성적 닫기");
		grade.addActionListener(this);
		gradeMenu.add(grade);*/
		
				
//출결		
		JMenu attendMenu = new JMenu(" 출결");
		attendMenu.setPreferredSize(new Dimension(80, 50));
		attendMenu.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		
//통계		
		JMenu staticMenu = new JMenu(" 통계");
		staticMenu.setPreferredSize(new Dimension(80, 50));
		staticMenu.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		
//학생
		JMenu studentMenu = new JMenu(" 학생");
		studentMenu.setPreferredSize(new Dimension(80, 50));
		studentMenu.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		student = new JMenuItem("학생 열기");
		student.addActionListener(this);
		studentMenu.add(student);
		
//강의
		JMenu lectureMenu = new JMenu(" 강의");
		lectureMenu.setPreferredSize(new Dimension(80, 50));
		lectureMenu.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
	
		
		gradeMenu.setToolTipText("클릭하면 성적을 엽니다.");
		attendMenu.setToolTipText("클릭하면 출결을 엽니다.");
		staticMenu.setToolTipText("클릭하면 통계를 엽니다.");
		studentMenu.setToolTipText("클릭하면 학생을 엽니다.");
		lectureMenu.setToolTipText("클릭하면 강의를 엽니다.");
		
		this.add(fileMenu);
		this.add(gradeMenu);	
		this.add(attendMenu);	
		this.add(staticMenu);	
		this.add(studentMenu);	
		this.add(lectureMenu);	
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem mi = (JMenuItem) e.getSource();
		Object menu = e.getSource();
		
		switch(mi.getText()) {
		case "열기":
			//열기 관련 이벤트
			JOptionPane.showMessageDialog(this, "열기 처리");
			break;
		case "저장":
			//저장 관련 이벤트
			JOptionPane.showMessageDialog(this, "저장 처리");
			break;
		case "종료":
			System.exit(0);
			//종료 관련 이벤트
			break;
		}
		
		if(menu == grade) {
			JOptionPane.showMessageDialog(this, "성적 처리");
			//성적 열기 부분 (메뉴바에 버튼을 넣을지, menuItem을 넣을지 고민)
		}else if(menu == student) {
			System.out.println("hi");
			test = 1;
			//System.out.println(test);
			new SuperFrame();

		}
		
	}
}
