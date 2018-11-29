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

public class editScore extends JFrame implements ActionListener{
	JButton saveButton;
	
	GridBagLayout glay;
	GridBagConstraints gbc;
	
	GradeDB gdb;
	GradePanel gp;
	String studentNumber;
	String studentName;
	
	int fieldNum;
	String []fieldName;
	int scoreArray[];
	
	JTextField []inputScore;
	
	public editScore() {
		Show();
	}
	
	public editScore(GradeDB gdb,GradePanel gp,String studentNumber,String studentName) {
		this.gdb=gdb;
		this.gp=gp;
		this.studentNumber=studentNumber;
		this.studentName=studentName;
		this.fieldNum=gdb.getFieldNum();
		this.fieldName=gdb.getFieldName();
		this.inputScore=new JTextField[fieldNum];
		scoreArray=gdb.getScore(studentNumber);
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
		
		JLabel number=new JLabel("학번:"+studentNumber);
		number.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
		gbReset(number, 0, 0, 1, 1);
		JLabel name=new JLabel("이름:"+studentName);
		name.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
		gbReset(name, 1, 0, 1, 1);		
		
		for(int i=4;i<fieldNum;i++) {
			JLabel jl=new JLabel(fieldName[i]+":");
			jl.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
			inputScore[i-4] = new JTextField(""+scoreArray[i-4],1);
			gbReset(jl, 0, i-3, 1, 1);
		    gbReset(inputScore[i-4], 1, i-3, 1, 1);
		}	
		
		
		JPanel PButton = new JPanel();
	    saveButton = new JButton("저장");
	    saveButton.addActionListener(this);
	    PButton.add(saveButton);
	    gbReset(PButton, 0, 9, 4, 1);	
	    
	    setSize(200,60+60*(fieldNum-4));
	    setVisible(true);
	    setResizable(false);
	    setLocation(800,400);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b == saveButton) {
			int arr[]=new int[fieldNum];
			for(int i=0;i<fieldNum-4;i++) {
				arr[i]=Integer.parseInt(inputScore[i].getText());
			}
			if(gdb.setScore(arr, studentNumber)==true) {				
				JOptionPane.showMessageDialog(this, "점수 입력 완료");
				dispose();
			}else {
				JOptionPane.showMessageDialog(this, "점수 입력 실패");				
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
