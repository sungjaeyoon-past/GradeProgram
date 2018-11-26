package Window;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import DBInfo.GradeDB;
import Frame.GradePanel;

public class removeGrade extends JFrame implements ActionListener {
	JTextField inputItem;
	JButton removeButton;

	GridBagLayout glay;
	GridBagConstraints gbc;
	
	GradeDB gdb;
	GradePanel gp;
	String []fieldName;
	int fieldNum;
	
	String clikedField;
	
	public removeGrade() {
		
	}

	public removeGrade(GradeDB gdb,GradePanel gp) {
		this.gdb=gdb;
		this.gp=gp;
		fieldName = gdb.getFieldName();
		fieldNum=gdb.getFieldNum();
		Show();
	}
	
	public void Show() {
		this.setTitle("항목 삭제");
		glay = new GridBagLayout();
		gbc = new GridBagConstraints();
		
		setLayout(glay);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		ButtonGroup bg = new ButtonGroup();
		
		for(int i=4;i<fieldNum;i++) {	
			JRadioButton rb = new JRadioButton(fieldName[i]);
			gbReset(rb, 0, i-4, 1, 1);
			bg.add(rb);
			rb.addActionListener(this);
		}	    
	    
	    JPanel PButton = new JPanel();
	    removeButton = new JButton("삭제");
	    removeButton.addActionListener(this);
	    PButton.add(removeButton);
	    gbReset(PButton, 0, 9, 4, 1);    
		
	    setSize(200,50*(fieldNum-5));
	    setVisible(true);
	    setResizable(false);
	    setLocation(750,450);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(clikedField==null) {
			String s = e.getActionCommand();		
			for(int i=0;i<fieldNum;i++) {			
				if(s.equals(fieldName[i])){
					clikedField=fieldName[i];			
				}
			}
		}else {
			JButton b = (JButton) e.getSource();
			if(b == removeButton) {
				gdb.removeColumn(clikedField);
				clikedField=null;
				JOptionPane.showMessageDialog(this, "항목 삭제 완료");
				dispose();
				gp.JTableRefresh();
			}
		}
	}
	
	private void gbReset(JComponent c, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        glay.setConstraints(c, gbc);
        gbc.insets = new Insets(2, 2, 2, 2);
        add(c, gbc);
    }
	

}
