package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JButton;

import Window.AdditStudent;

public class StudentPanel extends TopPanel{

	//AdditStudent s = new AdditStudent();
	public StudentPanel(){
		JButton btn = new JButton("학생 등록");
		btn.setPreferredSize(new Dimension(130, 50));
		btn.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		btn.addActionListener(new MyActionListener());
		add(btn);
		
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		setBackground(Color.WHITE);
	}
	
	private class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if (b.getText().equals("학생 등록")) {
            	System.out.println("학생등록을 누름");
            	new AdditStudent();
            }
		}
	}
}
