package Frame;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MenuBar extends JMenuBar implements ActionListener{
	public MenuBar() {
		JMenuItem item;
		KeyStroke key;
		//this.setSize(1200,200);
		//파일관련
		JMenu fileMenu = new JMenu("파일");
		item = new JMenuItem("열기");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
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
		
		JMenu gradeMenu = new JMenu("성적");
		JMenu attendMenu = new JMenu("출결");
		JMenu staticMenu = new JMenu("통계");
		JMenu studentMenu = new JMenu("학생");
		JMenu lectureMenu = new JMenu("강의");
		
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
		JMenuItem mi = (JMenuItem)(e.getSource());
		switch(mi.getText()) {
		case "열기":
			//열기 관련 이벤트
			break;
		case "저장":
			//저장 관련 이벤트
			break;
		case "종료":
			//종료 관련 이벤트
			break;
		}
	}
}
