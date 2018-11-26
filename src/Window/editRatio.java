package Window;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DBInfo.GradeDB;
import Frame.GradePanel;
import javafx.scene.control.Label;

public class editRatio extends JFrame implements ActionListener{
	
	JTextField inputItem;
	JButton saveButton;

	GridBagLayout glay;
	GridBagConstraints gbc;
	
	GradeDB gdb;
	GradePanel gp;
	String []fieldName;
	int fieldNum;
	
	JTextField inputRatio[];
	
	public editRatio() {
		Show();
	}
	
	public editRatio(GradeDB gdb,GradePanel gp) {
		this.gdb=gdb;
		this.gp=gp;
		fieldName = gdb.getFieldName();
		fieldNum=gdb.getFieldNum();
		inputRatio=new JTextField[fieldNum];
		Show();
	}
	
	public void Show() {
		this.setTitle("비율 설정");
		glay = new GridBagLayout();
		gbc = new GridBagConstraints();
		
		setLayout(glay);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		int []arr=gdb.getGradeRatio();
		
		for(int i=4;i<fieldNum;i++) {
			JLabel jl=new JLabel(fieldName[i]+":");
			jl.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
			inputRatio[i-4]= new JTextField(""+arr[i-4],1);
			JLabel j2=new JLabel("%");
			j2.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
			gbReset(jl, 0, i-4, 1, 1);
		    gbReset(inputRatio[i-4], 1, i-4, 1, 1);
		    gbReset(j2, 2, i-4, 1, 1);
		}
		
		JPanel PButton = new JPanel();
	    saveButton = new JButton("저장");
	    saveButton.addActionListener(this);
	    PButton.add(saveButton);
	    gbReset(PButton, 0, 9, 4, 1);
	    
	    setSize(150,60+60*(fieldNum-4));
	    setVisible(true);
	    setResizable(false);
	    setLocation(800,400);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b == saveButton) {
			int sum=0;
			for(int i=0;i<fieldNum-4;i++) {
				sum+=Integer.parseInt(inputRatio[i].getText());
			}
			if(sum==100) {
				//저장
				if(gdb.setRatio(inputRatio)) {
					JOptionPane.showMessageDialog(this, "항목 저장 완료");
					dispose();							
				}else {
					JOptionPane.showMessageDialog(this, "항목 저장 오류");			
				}
			}else {	
				JOptionPane.showMessageDialog(this, "비율의 합이 100%가 되어야합니다.");
			}

		}
		gp.JTableRefresh();	
		
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
