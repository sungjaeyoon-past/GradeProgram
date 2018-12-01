package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import DBInfo.GradeDB;
import Main.GradeP;

public class StaticPanel extends TopPanel implements ActionListener {
	private String[] fieldName = new String[15]; // 항목 들을 넣을 배열
	private int fieldNum; // 항목 갯수
	private GradePanel gp;
	private GradeDB gdb;

	// 각 성적 평가 항목에 대한 평균을 계산하는 기능
	// 학생의 총점에 대한 점수 분포 그래프를 확인하는 기능
	// 각 성적 평가 항목에 대한 점수 분포 그래프를 확인하는 기능

	public StaticPanel(GradePanel gp) {
		this.gp = gp;
		this.gdb = gp.getG();
		this.fieldName = gdb.getFieldName();
		this.fieldNum = gdb.getFieldNum();
		this.add(new JButton("통계 패널"));
		this.setLayout(new BorderLayout());

		JPanel top = createTop();
		this.add(top, BorderLayout.NORTH);

		JPanel mid = createChart();
		this.add(mid, BorderLayout.CENTER);

	}

	// 라디오 버튼 : 총점 점수분포, 각 성적 평가 항목 점수분포(평균 포함)
	public JPanel createTop() {
		JPanel j = new JPanel();
		ButtonGroup bg = new ButtonGroup();
		JRadioButton rbSum = new JRadioButton("총점 점수 분포");
		rbSum.setFont(new Font("a개미야", Font.BOLD, 25));
		rbSum.setPreferredSize(new Dimension(200, 50));
		bg.add(rbSum);
		j.add(rbSum, BorderLayout.CENTER);
		for (int i = 4; i < fieldNum; i++) {
			JRadioButton rb = new JRadioButton(fieldName[i] + " 분포");
			rb.setFont(new Font("a개미야", Font.BOLD, 25));
			rb.setPreferredSize(new Dimension(200, 50));
			bg.add(rb);
			j.add(rb, BorderLayout.CENTER);
		}
		return j;
	}
	
	//차트 그리기
	public JPanel createChart() {
		JPanel j = new JPanel();
		double[] values = gdb.countNumberBySum();
		String[] names = {"0~10("+(int)values[0]+"명)","10~20("+(int)values[1]+"명)","20~30("+(int)values[2]+"명)",
				"30~40("+(int)values[3]+"명)","40~50("+(int)values[4]+"명)","50~60("+(int)values[5]+"명)",
				"60~70("+(int)values[6]+"명)","70~80("+(int)values[7]+"명)","80~90("+(int)values[8]+"명)","90~100("+(int)values[9]+"명)"};
		ChartPanel ch = new ChartPanel(values, names, "총점 분포 그래프");
		ch.setPreferredSize(new Dimension(1100, 500));
		j.add(ch, BorderLayout.CENTER);
		return j;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
