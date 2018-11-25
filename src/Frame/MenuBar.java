package Frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javafx.scene.paint.Color;

public class MenuBar extends JMenuBar implements ActionListener, MouseListener{
	JMenuItem item;

	private JMenu gradeMenu;
	private JMenu studentMenu;
	private JMenu lectureMenu;
	private JMenu attendMenu;
	private JMenu staticMenu;
	private JMenu IntroMenu;
	
	SuperFrame s;//슈퍼프레임 받기위해
	KeyStroke key;
	
	public MenuBar(SuperFrame s) {
		this.s = s;
	
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
		gradeMenu = new JMenu(" 성적");
		gradeMenu.setPreferredSize(new Dimension(80, 50));
		gradeMenu.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		gradeMenu.addMouseListener(this);
				
//출결		
		attendMenu = new JMenu(" 출결");
		attendMenu.setPreferredSize(new Dimension(80, 50));
		attendMenu.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		attendMenu.addMouseListener(this);
//통계		
		staticMenu = new JMenu(" 통계");
		staticMenu.setPreferredSize(new Dimension(80, 50));
		staticMenu.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		staticMenu.addMouseListener(this);
//학생
		studentMenu = new JMenu(" 학생");
		studentMenu.setPreferredSize(new Dimension(80, 50));
		studentMenu.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		studentMenu.addMouseListener(this);
		
//강의
		lectureMenu = new JMenu(" 강의 ");
		lectureMenu.setPreferredSize(new Dimension(80, 50));
		lectureMenu.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		lectureMenu.addMouseListener(this);
//제작정보
		IntroMenu = new JMenu(" Help");
		IntroMenu.setPreferredSize(new Dimension(80, 50));
		IntroMenu.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		IntroMenu.addMouseListener(this);
		
		gradeMenu.setToolTipText("클릭하면 성적을 엽니다.");
		attendMenu.setToolTipText("클릭하면 출결을 엽니다.");
		staticMenu.setToolTipText("클릭하면 통계를 엽니다.");
		studentMenu.setToolTipText("클릭하면 학생을 엽니다.");
		lectureMenu.setToolTipText("클릭하면 강의를 엽니다.");
		IntroMenu.setToolTipText("클락하면 제작정보를 엽니다.");
		
		this.add(fileMenu);
		this.add(gradeMenu);	
		this.add(attendMenu);	
		this.add(staticMenu);	
		this.add(studentMenu);	
		this.add(lectureMenu);	
		this.add(IntroMenu);
		setVisible(true);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JMenu m = (JMenu)e.getSource();
		if(m == gradeMenu) {
			s.change("gradeP");
			
			
		}else if(m == attendMenu) {
			s.change("attendP");
			
		}else if(m == staticMenu) {
			s.change("staticP");
			
		}else if(m == studentMenu) {
			s.change("studentP");
			
		}else if(m == lectureMenu) {
			s.change("lectureP");
			
		}else if(m == IntroMenu) {
			s.change("IntroP");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem mi = (JMenuItem) e.getSource();
		
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
			
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}
