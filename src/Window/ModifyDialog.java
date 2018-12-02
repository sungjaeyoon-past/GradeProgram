package Window;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ModifyDialog extends JFrame implements ActionListener {

	public ModifyDialog() {
		JFrame modifier = new JFrame("출결 수정창");
		modifier.setLayout(new FlowLayout());
		modifier.setSize(360, 430);
		modifier.setLocation(600, 300);
		modifier.setResizable(true);	//사이즈 조절
		modifier.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		modifier.setVisible(true);
		
		addPanel(modifier);
	}
	
	public void addPanel(JFrame modifier) {
		// 순번은 정해져있으니 고칠 필요 없
		// 학번, 이름, 1~16 (하나하나 전부), 출석, 지각, 결석, 비고
		JPanel mainMod = new JPanel();
		
		mainMod.setLayout(new FlowLayout());
		mainMod = addComponent(mainMod);
		
		modifier.add(mainMod);
	}
	
	public JPanel addComponent(JPanel mainMod) {
		
		
		return mainMod;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
