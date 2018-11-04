package Frame;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{
	public MenuBar() {
		this.setSize(1200,200);
		JMenu fileMenu = new JMenu("파일");
		fileMenu.add(new JMenuItem("열기"));
		fileMenu.add(new JMenuItem("저장"));
		fileMenu.add(new JMenuItem("종료"));
		
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
}
