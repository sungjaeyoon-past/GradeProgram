package Frame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AttendPanel extends TopPanel{
	public AttendPanel() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		creatComponentPanel();
		
	}
	
	public void creatComponentPanel() {
		String[] str = {"학번","이름"};
		
		JComboBox combo = new JComboBox(str);
		combo.setFont(new Font("a개미야", Font.BOLD, 25));
		combo.setBounds(80, 40, 90, 40);
		
		JTextField jt = new JTextField("dd");
		jt.setFont(new Font("a개미야", Font.BOLD, 25));
		jt.setBounds(170, 40, 140, 40);
		
		JButton jb = new JButton("검색");
		jb.setFont(new Font("a개미야", Font.BOLD, 25));
		jb.setBounds(310, 40, 90, 40);
		
		add(combo);
		add(jt);
		add(jb);	
	}
	
}
