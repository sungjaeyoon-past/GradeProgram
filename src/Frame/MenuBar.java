package Frame;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MenuBar extends JMenuBar implements ActionListener{
	JMenuItem open, save, close;
	KeyStroke key;
	public MenuBar() {
		
		//this.setSize(1200,200);
		//파일관련
		JMenu fileMenu = new JMenu("파일");
		open = new JMenuItem("열기");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK);
		open.setAccelerator(key);
		open.addActionListener(this);
		fileMenu.add(open);
		
		save = new JMenuItem("저장");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK);
		save.setAccelerator(key);
		save.addActionListener(this);
		fileMenu.add(save);
		
		fileMenu.addSeparator();
		close = new JMenuItem("종료");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK);
		close.setAccelerator(key);
		close.addActionListener(this);
		fileMenu.add(close);
		
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
		//JMenuitem mi = (JMeunitem)(e.getSource());
		Object mi = e.getSource();
		if(mi==open) {
			System.out.println("hi");
			System.exit(1);
		}else if(mi==save) {
			System.out.println("hi");
			System.exit(1);
		}else {
			
		}
		/*switch(mi.getText()) {
		case "열기":
			System.exit(0);
			//열기 관련 이벤트
			break;
		case "저장":
			System.exit(0);
			//저장 관련 이벤트
			break;
		case "종료":
			System.out.println("hi");
			System.exit(0);
			//종료 관련 이벤트
			break;
		}*/
	}
}
