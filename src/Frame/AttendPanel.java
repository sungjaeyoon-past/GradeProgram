package Frame;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class AttendPanel extends TopPanel{
	private String tHead[] = {"순번", "학번", "이름", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
			"출석", "지각", "결석", "비고"};
	private String tBody[][] = new String[36][23];
	private JButton nameSort;
	private JButton search;
	private JButton modify;
	private JScrollPane sp;
	public AttendPanel() {
//		this.add(new JButton("출석 패널"));
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
		JPanel topP;
		JPanel midP = new JPanel();
		JPanel botP;
		
		topP = makeTop();
		sp = makeAttendTable();
		//이미 Table에 스크롤이 존재하는데 paging을 할 필요가 있을까
		botP = makePaging();
		
		midP.add(sp);
		
		this.add(topP, BorderLayout.NORTH);
		this.add(midP, BorderLayout.CENTER);
		this.add(botP, BorderLayout.SOUTH);
	}
	
	public JPanel makeTop() {
		JPanel topP = new JPanel();
		JPanel topP_search = new JPanel();
		JPanel topP_mod = new JPanel();
		
		topP.setLayout(new BorderLayout());
		topP_search.setLayout(new FlowLayout());
		topP_mod.setLayout(new FlowLayout());
		
		nameSort = new JButton("▼  이 름");
		search = new JButton("검 색");
		topP_search.add(nameSort);
		topP_search.add(new JTextField(20));
		topP_search.add(search);
		modify = new JButton("출석 수정");
		topP_mod.add(modify);
		
		topP.add(topP_search, BorderLayout.WEST);
		topP.add(topP_mod, BorderLayout.EAST);
		
		return topP;
	}
	
	public JScrollPane makeAttendTable() {
		DefaultTableModel model = new DefaultTableModel(tBody, tHead);
		JTable table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		sc.setPreferredSize(new java.awt.Dimension(1185, 601));
		// 테이블 가운데 정렬
		DefaultTableCellRenderer tRenderer = new DefaultTableCellRenderer();
		tRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcolumn = table.getColumnModel();
		for(int i=0; i<tcolumn.getColumnCount(); i++) {
			tcolumn.getColumn(i).setCellRenderer(tRenderer);
		}
		// 각 셀 간격 늘리기
		table.getColumn("학번").setPreferredWidth(200);
		table.getColumn("이름").setPreferredWidth(100);
		table.getColumn("비고").setPreferredWidth(300);
		
		return sc;
	}
	
	public JPanel makePaging() {
		JPanel botP = new JPanel();
		JPanel paging = new JPanel();
		
		botP.setLayout(new BorderLayout());
		paging.setLayout(new FlowLayout());
		
		paging.add(new JButton("◀ "));
		paging.add(new JLabel(" Page "));
		paging.add(new JButton("▶ "));
		
		botP.add(paging, BorderLayout.CENTER);
		
		return botP;

	}
}