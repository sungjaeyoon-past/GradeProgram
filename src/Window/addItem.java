package Window;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DBInfo.GradeDB;
import Frame.GradePanel;
import Frame.StudentPanel;

public class addItem extends JFrame implements ActionListener{
	JTextField inputItem;
	JButton saveButton;

	GridBagLayout glay;
	GridBagConstraints gbc;
	
	GradeDB gdb;
	GradePanel gp;
	
	public addItem() {
		Show();
	}
	
	public addItem(GradeDB gdb,GradePanel gp) {
		this.gdb=gdb;
		this.gp=gp;
		Show();
	}
	
	public void Show() {
		this.setTitle("항목 추가");
		glay = new GridBagLayout();
		gbc = new GridBagConstraints();
		
		setLayout(glay);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
	    JLabel st1 = new JLabel("항목 이름: ");
	    st1.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
	    inputItem = new JTextField(5);
	    gbReset(st1, 0, 0, 1, 1);
	    gbReset(inputItem, 1, 0, 3, 1);
	    
	    JPanel PButton = new JPanel();
	    saveButton = new JButton("저장");
	    saveButton.addActionListener(this);
	    PButton.add(saveButton);
	    gbReset(PButton, 0, 9, 4, 1);
	    
	    setSize(150,150);
	    setVisible(true);
	    setResizable(false);
	    setLocation(800,400);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b == saveButton) {
			gdb.addColumn(inputItem.getText());
			JOptionPane.showMessageDialog(this, "항목 저장 완료");
			dispose();
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
