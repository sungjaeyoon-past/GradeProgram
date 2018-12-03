package Frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Object;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import DBInfo.GradeDB;
import Main.GradeP;

public class StaticPanel extends JPanel implements ActionListener {
	private String[] fieldName = new String[15]; // 항목 들을 넣을 배열
	private int fieldNum; // 항목 갯수
	private GradePanel gp;
	private GradeDB gdb;
	private String clikedField;
	private CardLayout cLayout;
	private JPanel chartPanel;
	public final String[] PAGE= {"sum","item1","item2","item3","item4","item5","item6","item7","item8","item9"};

	public StaticPanel(GradePanel gp) {
		this.gp = gp;
		this.gdb = gp.getG();
		this.fieldName = gdb.getFieldName();
		this.fieldNum = gdb.getFieldNum();
		
		setLayout(new BorderLayout());
		cLayout = new CardLayout(10,10);

		JPanel top = createTop();
		this.add(top, BorderLayout.NORTH);

		chartPanel = new JPanel();
		chartPanel.setLayout(cLayout);
		
		for(int i=0;i<fieldNum-3;i++) {
			JPanel drawChart = createChartByItem(i);
			chartPanel.add(drawChart,PAGE[i]);		
		}
		
		cLayout.show(chartPanel,PAGE[0]);	
		add(chartPanel,BorderLayout.CENTER); 
	}

	// 라디오 버튼 : 총점 점수분포, 각 성적 평가 항목 점수분포(평균 포함)
	public JPanel createTop() {
		JPanel j = new JPanel();
		ButtonGroup bg = new ButtonGroup();
		JRadioButton rbSum = new JRadioButton("총점 점수 분포");
		rbSum.setFont(new Font("a개미야", Font.BOLD, 20));
		rbSum.setPreferredSize(new Dimension(150, 50));
		rbSum.addActionListener(this);
		rbSum.setSelected(true);
		bg.add(rbSum);
		j.add(rbSum, BorderLayout.CENTER);
		for (int i = 4; i < fieldNum; i++) {
			JRadioButton rb = new JRadioButton(fieldName[i] + " 분포");
			rb.setFont(new Font("a개미야", Font.BOLD, 20));
			rb.setPreferredSize(new Dimension(150, 50));
			rb.addActionListener(this);
			bg.add(rb);
			j.add(rb, BorderLayout.CENTER);
		}
		return j;
	}

	public JPanel createChartByItem(int number) {
		JPanel j = new JPanel();
		double[] values = gdb.countNumberByItem(number);
		String[] names = { "0~10", "10~20","20~30", "30~40", "40~50","50~60", "60~70", "70~80","80~90", "90~100" };
		ChartPanel ch;
		if(number==0) {
			ch = new ChartPanel(values, names,"총점 분포 그래프");
		}else {
			ch = new ChartPanel(values, names, fieldName[number+3]+"분포 그래프");
		}
		ch.setPreferredSize(new Dimension(1100, 530));
		j.add(ch, BorderLayout.CENTER);
				
		for(int i=0;i<10;i++) {	
			JLabel jb2 = new JLabel((int)values[i]+"명");
			jb2.setFont(new Font("a개미야", Font.BOLD, 25));
			jb2.setHorizontalAlignment(JTextField.CENTER);
			jb2.setPreferredSize(new Dimension(105, 30));
			j.add(jb2, BorderLayout.SOUTH);
		}
		
		JTextField jb = new JTextField("평균 : " + (Math.round(gdb.getAverageItem()*100))/100);
		jb.setFont(new Font("a개미야", Font.BOLD, 25));
		jb.setHorizontalAlignment(JTextField.CENTER);
		jb.setPreferredSize(new Dimension(200, 50));
		jb.setEditable(false);
		j.add(jb, BorderLayout.CENTER);
		return j;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equals("총점 점수 분포")) {
			cLayout.show(chartPanel,PAGE[0]);	
		}
		for (int i = 4; i < fieldNum; i++) {
			if (s.equals(fieldName[i] + " 분포")) {
				cLayout.show(chartPanel,PAGE[i-3]);	
			}
		}
	}

}
