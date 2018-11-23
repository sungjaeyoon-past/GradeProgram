package Frame;

import java.awt.event.*;

import javax.swing.JButton;

import Window.AdditStudent;

public class StudentPanel extends TopPanel{

	//AdditStudent s = new AdditStudent();
	public StudentPanel(){
		JButton btn = new JButton("학생패널");
		btn.addActionListener(new MyActionListener());
		add(btn);
	}
	
	private class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if (b.getText().equals("학생패널")) {
            	System.out.println("학생패널을 누름");
            	new AdditStudent();
            }
		}
	}
}
