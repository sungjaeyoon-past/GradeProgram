package Frame;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SuperFrame extends JFrame {
	public SuperFrame() {
		Container c = this.getContentPane();
		JPanel mainView = new JPanel();

		

		// 테이블 테스트

		String data[][] = { { "60142311", "윤성재", "A+" }, { "60142000", "위연정", "A" }, { "60101111", "장희정", "F" } };
		String column[] = { "학번", "이름", "학점" };
		JTable jt=new JTable(data,column);    
	    jt.setBounds(10,10,200,300);          
	    JScrollPane sp=new JScrollPane(jt);
	    mainView.add(sp);
	    
		// 테이블 테스트
	    
	    c.add(mainView);
		this.setJMenuBar(new MenuBar());
		this.setTitle("성적 관리 프로그램");
		this.setSize(1200, 800);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
}
