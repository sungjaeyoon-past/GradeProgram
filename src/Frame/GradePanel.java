package Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import DBInfo.StudentDB;
import DBInfo.GradeDB;
import Main.GradeRatio;
import Main.Student;
import Window.AdditStudent;
import Window.addItem;
import Window.editItemRatio;
import Window.editScore;
import Window.removeItem;

public class GradePanel extends TopPanel implements ActionListener, MouseListener {
	private JScrollPane studentT;
	Vector stulist; // 학생 리스트
	Vector cols; // 테이블 헤더
	DefaultTableModel model;
	JTable table;

	JComboBox search_jcb;// 검색 종류
	JButton search_btn;// 검색하기버튼
	JButton editGradeRatio;// 검색하기버튼
	JButton addItem;// 항목추가버튼
	JButton renameItem;// 항목수정버튼
	JButton inputScore;// 점수수정버튼
	JButton ratioScore;// 비율설정버튼
	JTextField search_Text;// 검색버튼
	String studentNumber;
	String studentName;

	GradeDB g;

	public GradePanel() {
		g = new GradeDB();
		stulist = g.getMemberList();
		this.setLayout(new BorderLayout());
		JPanel top = createTop();
		this.add(top, BorderLayout.NORTH);

		JPanel mid = new JPanel();
		studentT = makeStudentTable();
		mid.add(studentT);
		this.add(mid, BorderLayout.CENTER);

		JPanel botS;
		botS = makesB();
		this.add(botS, BorderLayout.SOUTH);

		g.getGradeRatio();
	}

	public JPanel makesB() {
		JPanel sBot_sort = new JPanel();

		sBot_sort.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton backList = new JButton("목록");
		backList.addActionListener(this);
		sBot_sort.add(backList);

		return sBot_sort;
	}

	public JPanel createTop() {
		JPanel j = new JPanel();
		JPanel jWest = new JPanel();
		JPanel jEast = new JPanel();

		j.setLayout(new BorderLayout());
		jWest.setLayout(new FlowLayout());
		jEast.setLayout(new FlowLayout());

		search_Text = new JTextField("");// 나중에 디비로 이어지게
		search_Text.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		search_Text.setPreferredSize(new Dimension(150, 50));

		String[] head = { "학번", "이름", "성적" };
		search_jcb = new JComboBox(head);
		search_jcb.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		search_jcb.setPreferredSize(new Dimension(80, 50));

		search_btn = new JButton("학생 검색");
		search_btn.setPreferredSize(new Dimension(120, 50));
		search_btn.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		search_btn.addActionListener(this);

		editGradeRatio = new JButton("등급 비율");
		editGradeRatio.setPreferredSize(new Dimension(130, 50));
		editGradeRatio.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		editGradeRatio.addActionListener(this);

		inputScore = new JButton("점수 입력");
		inputScore.setPreferredSize(new Dimension(130, 50));
		inputScore.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		inputScore.addActionListener(this);

		addItem = new JButton("항목 추가");
		addItem.setPreferredSize(new Dimension(130, 50));
		addItem.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		addItem.addActionListener(this);

		renameItem = new JButton("항목 삭제");
		renameItem.setPreferredSize(new Dimension(130, 50));
		renameItem.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		renameItem.addActionListener(this);

		ratioScore = new JButton("항목 비율");
		ratioScore.setPreferredSize(new Dimension(130, 50));
		ratioScore.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		ratioScore.addActionListener(this);

		jWest.add(search_jcb);
		jWest.add(search_Text);
		jWest.add(search_btn);

		jEast.add(editGradeRatio);
		jEast.add(inputScore);
		jEast.add(addItem);
		jEast.add(renameItem);
		jEast.add(ratioScore);

		j.add(jWest, BorderLayout.WEST);
		j.add(jEast, BorderLayout.EAST);

		return j;
	}

	public JScrollPane makeStudentTable() {
		GradeDB gradedb = new GradeDB();
		Font f = new Font("HY엽서L", Font.BOLD, 20);// 테이블 헤더 설정

		cols = getColumn(); // 헤더 데이터 받음
		model = new DefaultTableModel(stulist, cols);
		table = new JTable(model);

		table.getTableHeader().setReorderingAllowed(false);// column 위치변경x
		table.setFont(new Font("HY엽서L", 1, 15));// 테이블 폰트변경
		table.setRowHeight(50);// 테이블 높이변경
		table.addMouseListener(this);
		JScrollPane pane = new JScrollPane(table);

		JTableHeader header = table.getTableHeader();
		header.setFont(f);

		pane.setPreferredSize(new java.awt.Dimension(1185, 601));// 테이블 사이즈 조정
		String fieldName[] = g.getFieldName();

		return pane;
	}

	public Vector getColumn() {
		Vector col = new Vector();
		int fieldNum = g.getFieldNum();
		int ratio[] = g.getRatio();
		String fieldName[] = g.getFieldName();
		col.add("번호");
		for (int i = 1; i < 4; i++) {
			col.add(fieldName[i]);
		}
		for (int i = 4; i < fieldNum; i++) {
			col.add(fieldName[i] + "(" + ratio[i - 4] + "%)");
		}
		col.add("합계");
		return col;
	}

	public void JTableRefresh() {
		g = new GradeDB();
		model = new DefaultTableModel(g.getMemberList(), getColumn());// DB데이터 다시 받아 테이블 초기화
		table.setModel(model); // 테이블 새로고침
	}

	public void addColumn(String newCol) {
		g.addColumn(newCol);
	}

	public void removeColumn(String removeCol) {
		g.removeColumn(removeCol);
	}

	public void setGradeRatio(GradeRatio gr) {
		g.setGradeRate(gr);
	}

	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("점수 입력")) {
			if (studentNumber == null) {
				JOptionPane.showMessageDialog(this, "점수를 입력 하려는 학생을 선택하세요.");
			} else {
				new editScore(g, this, studentNumber, studentName);
			}
		} else if (b.getText().equals("등급 비율")) {
			new Window.editGradeRatio(g,this);
		} else if (b.getText().equals("항목 추가")) {
			new addItem(g, this);
		} else if (b.getText().equals("항목 삭제")) {
			new removeItem(g, this);
		} else if (b.getText().equals("항목 비율")) {
			new editItemRatio(g, this);
		} else if (b.getText().equals("학생 검색")) {
			model = new DefaultTableModel(
					g.searchStudentByNameOrNumber(search_Text.getText(), search_jcb.getSelectedIndex()), getColumn());
			table.setModel(model);
		} else if (b.getText().equals("목록")) {
			JTableRefresh();
		}
	}

	public GradeDB getG() {
		return g;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int rowSelect = table.getSelectedRow();
		studentNumber = (String) table.getValueAt(rowSelect, 1);
		studentName = (String) table.getValueAt(rowSelect, 2);
		// aClick = new AdditStudent(studentNumber, this, 1);//수정할때 클릭한 데이터 넘기기 위해
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
