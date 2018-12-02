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
import Main.GradeRatio;

public class editGradeRatio extends JFrame implements ActionListener{
	JButton saveButton;
	JTextField inputItem[];
	GridBagLayout glay;
	GridBagConstraints gbc;
	GradeDB gdb;
	GradePanel gp;
	
	public editGradeRatio() {
		Show();
	}
	
	public editGradeRatio(GradeDB gdb,GradePanel gp) {
		this.gdb=gdb;
		this.gp=gp;
		Show();
	}
	public void Show() {
		this.setTitle("등급 비율");
		glay = new GridBagLayout();
		gbc = new GridBagConstraints();
		
		setLayout(glay);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		String str[]= {"A+","A","B+","B","C+","C","D+","D","F"};
		inputItem=new JTextField[10];
		int rate[]=gdb.getGradeRate();
		for(int i=0;i<9;i++) {		
			JLabel st1 = new JLabel(str[i]+" :");
			st1.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
			inputItem[i] = new JTextField(""+rate[i],5);
			JLabel j2=new JLabel("%");
			gbReset(st1, 0, i, 1, 1);
			gbReset(inputItem[i], 1, i, 1, 1);
			gbReset(j2, 2, i, 1, 1);
		}
		
		JPanel PButton = new JPanel();
		saveButton = new JButton("저장");
		saveButton.addActionListener(this);
		PButton.add(saveButton);
		gbReset(PButton, 0, 9, 4, 1);
		
		setSize(150,500);
		setVisible(true);
		setResizable(false);
		setLocation(800,250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b.getText().equals("저장")) {
			int sum=0;
			for(int i=0;i<9;i++) {
				sum+=Integer.parseInt(inputItem[i].getText());
			}
			if(sum==100) {
				gdb.setGradeRate(new GradeRatio(Integer.parseInt(inputItem[0].getText()), Integer.parseInt(inputItem[1].getText()),Integer.parseInt(inputItem[2].getText()),
						Integer.parseInt(inputItem[3].getText()), Integer.parseInt(inputItem[4].getText()), Integer.parseInt(inputItem[5].getText()), 
						Integer.parseInt(inputItem[6].getText()), Integer.parseInt(inputItem[7].getText()), Integer.parseInt(inputItem[8].getText())));				
				JOptionPane.showMessageDialog(this, "항목 저장 완료");
				gp.JTableRefresh();	
				dispose();							
			}else {
				JOptionPane.showMessageDialog(this, "비율의 합이 100%가 되어야합니다.");
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
