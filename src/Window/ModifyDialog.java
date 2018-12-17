package Window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import DBInfo.AttendDB;
import Frame.AttendPanel;

public class ModifyDialog extends JFrame implements ActionListener {
	JFrame modifier;
	AttendPanel att_pane;
	AttendDB att_db;
	String target;
	boolean isApplied;
	
	// 학번, 이름, 1~16 (하나하나 전부), 출석, 지각, 결석, 비고
	JTextField studentNumber;
	JTextField name;
	JTextField attend1;
	JTextField attend2;
	JTextField attend3;
	JTextField attend4;
	JTextField attend5;
	JTextField attend6;
	JTextField attend7;
	JTextField attend8;
	JTextField attend9;
	JTextField attend10;
	JTextField attend11;
	JTextField attend12;
	JTextField attend13;
	JTextField attend14;
	JTextField attend15;
	JTextField attend16;
	int att;
	int late;
	int abs;
	JTextField extra;
	JButton apply;
	JButton cancel;
	public ModifyDialog() {
		modifier = new JFrame("출결수정");
		modifier.setLayout(new BorderLayout());
		modifier.setSize(300, 280);
		modifier.setLocation(600, 300);
		modifier.setResizable(false);	//사이즈 조절
		modifier.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		modifier.setVisible(true);
		addPanel();
	}
	public ModifyDialog(String column, AttendDB db, AttendPanel pane) {
		att_pane = pane;
		att_db = db;
		target = column;
		modifier = new JFrame("출결수정");
		modifier.setLayout(new BorderLayout());
		modifier.setSize(300, 280);
		modifier.setLocation(600, 300);
		modifier.setResizable(false);	//사이즈 조절
		modifier.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		modifier.setVisible(true);
		addPanel();
	}
	public void addPanel() {
		// 순번은 정해져있으니 고칠 필요 없
		// 학번, 이름, 1~16 (하나하나 전부), 출석, 지각, 결석, 비고
		JPanel mainMod = new JPanel();
		
		mainMod.setLayout(new FlowLayout());
		mainMod = addComponent(mainMod);
		
		modifier.add(mainMod, BorderLayout.CENTER);
	}
	
	public JPanel addComponent(JPanel mainMod) {
		JLabel lstuNum;
		JLabel lname;
		JLabel lattend;
		JLabel latt1start, latt1end;
		JLabel latt2start, latt2end;
		JLabel latt3start, latt3end;
		JLabel latt4start, latt4end;
		JLabel ex;
		
//		mainMod.add(lstuNum = new JLabel("학번 : "));
//		lstuNum.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
//		mainMod.add(studentNumber = new JTextField(6));
//		
//		mainMod.add(lname = new JLabel("이름 : "));
//		lname.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
//		mainMod.add(name = new JTextField(6)); 
		
		mainMod.add(lattend = new JLabel("출석 체크(o, v, x)"));
		lattend.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 30));
		
		mainMod.add(latt1start = new JLabel("      [1  :  "));
		latt1start.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
		mainMod.add(attend1 = new JTextField(2)); 
		mainMod.add(attend2 = new JTextField(2));
		mainMod.add(attend3 = new JTextField(2));
		mainMod.add(attend4 = new JTextField(2));
		attend1.setText("x"); attend2.setText("x");
		attend3.setText("x"); attend4.setText("x");
		mainMod.add(latt1end = new JLabel("  ]          "));
		latt1end.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
		
		mainMod.add(latt2start = new JLabel("   [2  :  "));
		latt2start.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
		mainMod.add(attend5 = new JTextField(2)); 
		mainMod.add(attend6 = new JTextField(2));
		mainMod.add(attend7 = new JTextField(2));
		mainMod.add(attend8 = new JTextField(2));
		attend5.setText("x"); attend6.setText("x");
		attend7.setText("x"); attend8.setText("x");
		mainMod.add(latt2end = new JLabel("  ]      "));
		latt2end.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
		
		mainMod.add(latt3start = new JLabel("   [3  :  "));
		latt3start.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
		mainMod.add(attend9 = new JTextField(2)); 
		mainMod.add(attend10 = new JTextField(2));
		mainMod.add(attend11 = new JTextField(2));
		mainMod.add(attend12 = new JTextField(2));
		attend9.setText("x"); attend10.setText("x");
		attend11.setText("x"); attend12.setText("x");
		mainMod.add(latt3end = new JLabel("  ]      "));
		latt3end.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
		
		mainMod.add(latt4start = new JLabel("   [4  :  "));
		latt4start.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
		mainMod.add(attend13 = new JTextField(2)); 
		mainMod.add(attend14 = new JTextField(2));
		mainMod.add(attend15 = new JTextField(2));
		mainMod.add(attend16 = new JTextField(2));
		attend13.setText("x"); attend14.setText("x");
		attend15.setText("x"); attend16.setText("x");
		mainMod.add(latt4end = new JLabel("  ]      "));
		latt4end.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
		
		mainMod.add(ex = new JLabel("   비  고"));
		ex.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		mainMod.add(extra = new JTextField(12));
		extra.setText("없음");
		
		mainMod.add(apply = new JButton("확   인"));
		apply.addActionListener(this);
		mainMod.add(cancel = new JButton("취   소"));
		cancel.addActionListener(this);
		
		return mainMod;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton event = (JButton)e.getSource();		
		att = 0; late = 0; abs = 0; 
		boolean recursion = false;
		if(event == apply) {
			String attendString = attend1.getText() + attend2.getText() + attend3.getText()
					+ attend4.getText() + attend5.getText() + attend6.getText()
					+ attend7.getText() + attend8.getText() + attend9.getText()
					+attend10.getText() + attend11.getText() + attend12.getText()
					+attend13.getText() + attend14.getText() + attend15.getText()
					+attend16.getText();
			for(int i=0; i<16; i++) {
				if(attendString.substring(i, i+1).equals("o")) {
					att++;
					recursion = true;
				}else if(attendString.substring(i, i+1).equals("v")) {
					late++;
					recursion = true;
				}else if(attendString.substring(i, i+1).equals("x") ) {
					abs++;
					recursion = true;
				}
				else {
					JOptionPane.showMessageDialog(null, "o, v, x 중 하나를 선택해주세요", 
							"출결 에러", JOptionPane.ERROR_MESSAGE);
					att = 0;
					late = 0;
					abs = 0;
					recursion = false;
					break;
				}
			}
			if(recursion) {
				String[] attendStatus = {attendString, Integer.toString(att), 
						Integer.toString(late), Integer.toString(abs), extra.getText()
				};
					
				att_db.modifyAttendData(target, attendStatus);
				isApplied = true;
				modifier.dispose();
			}
		}
		else if(event == cancel) {
			isApplied = false;
			modifier.dispose();
		}
	}

	public boolean getIsApplied() {
		return isApplied;
	}
}
