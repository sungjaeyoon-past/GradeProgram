package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class IntroPanel extends TopPanel{
	IntroPanel(){
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(5, 5, 5, 5)); // 처음 간격 설정
		setLayout(null);
		
		JLabel labelImage = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/mainImage.PNG")).getImage();
		labelImage.setIcon(new ImageIcon(img));
		
		labelImage.setBounds(100, 190, 430, 320);//이미지 위치랑 크기 조정
		add(labelImage);
		
		JLabel projectName = new JLabel("1. 프로젝트 명 : ");
		projectName.setFont(new Font("a개미야", Font.BOLD, 35)); // 개미야 폰트 있어야 적용 ^^
		projectName.setBounds(670, 260, 380, 29);
		add(projectName);
		
		JLabel version = new JLabel("2. 버젼 정보 : ");
		version.setFont(new Font("a개미야", Font.BOLD, 35));
		version.setBounds(670, 330, 380, 29);
		add(version);
		
		JLabel developer = new JLabel("3. 제작자 : ");
		developer.setFont(new Font("a개미야", Font.BOLD, 35));
		developer.setBounds(670, 400, 380, 29);
		add(developer);
		
		JLabel example = new JLabel("나중에 이쁘게 꾸밉시다 ..");
		example.setFont(new Font("맑은고딕", Font.BOLD, 25));
		example.setBounds(670, 580, 380, 29);
		add(example);
	}
}
