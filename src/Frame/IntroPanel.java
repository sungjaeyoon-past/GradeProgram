package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class IntroPanel extends TopPanel{
	IntroPanel(){
		setBackground(Color.BLACK);
		//setBorder(new EmptyBorder(5, 5, 5, 5)); // 처음 간격 설정
		setLayout(null);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel labelImage = new JLabel(""); //이미지 위치를 지정할 라벨 설정
		Image img = new ImageIcon(this.getClass().getResource("/mainImage.PNG")).getImage();
		labelImage.setIcon(new ImageIcon(img));
		
		labelImage.setBounds(100, 190, 430, 320);//이미지 위치랑 크기 조정
		add(labelImage);
		
		JLabel projectName = new JLabel("1. 프로젝트 명 : lalavla");
		projectName.setFont(new Font("a개미야", Font.BOLD, 35)); // 개미야 폰트 있어야 적용 ^^
		projectName.setForeground(Color.WHITE);
		projectName.setBounds(670, 260, 380, 29);
		add(projectName);
		
		JLabel version = new JLabel("2. 버젼 정보 : ");
		version.setFont(new Font("a개미야", Font.BOLD, 35));
		version.setForeground(Color.WHITE);
		version.setBounds(670, 330, 380, 29);
		add(version);
		
		JLabel developer = new JLabel("3. 제작자 : 로션");
		developer.setFont(new Font("a개미야", Font.BOLD, 35));
		developer.setForeground(Color.WHITE);
		developer.setBounds(670, 400, 380, 29);
		add(developer);
		
		JLabel example = new JLabel("# 고급 객체지향 프로그래밍 #");
		example.setFont(new Font("a개미야", Font.BOLD, 25));
		example.setForeground(Color.WHITE);
		example.setBounds(470, 650, 380, 29);
		add(example);

	}
	//배경설정
	Image bg = new ImageIcon("Image/background.jpg").getImage();
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, null);
	}

}
