package Window;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DBInfo.GradeDB;
import Frame.GradePanel;

public class editScore extends JFrame implements ActionListener{
	GridBagLayout glay;
	GridBagConstraints gbc;
	
	GradeDB gdb;
	GradePanel gp;
	public editScore() {
		Show();
	}
	
	public editScore(GradeDB gdb,GradePanel gp) {
		this.gdb=gdb;
		this.gp=gp;
		Show();
	}
	
	public void Show() {
		this.setTitle("점수 입력");
		glay = new GridBagLayout();
		gbc = new GridBagConstraints();
		
		setLayout(glay);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		int fieldNum=gdb.getFieldNum();
		String []fieldName=gdb.getFieldName();
		
	    
	    setSize(400,400);
	    setVisible(true);
	    setResizable(false);
	    setLocation(800,400);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
