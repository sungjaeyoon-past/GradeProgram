package Frame;

import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import DBInfo.StudentDB;
import Main.Student;
import Window.AdditStudent;

public class StudentPanel extends TopPanel implements ActionListener, MouseListener{
	private JScrollPane studentT;
	Vector stulist; //학생 리스트
	Vector cols; // 테이블 헤더
	DefaultTableModel model;
	JTable table;
	
	JComboBox search_jcb; //검색하려는 콤보박스
	JButton btn, mbtn, dbtn, search_btn; //등록, 수정, 삭제, 검색버튼
	JButton resetB, stuNumSort, nameSort; // 리셋, 학번, 이름순으로 정렬하는 버튼
	JLabel topic;
	JTextField search_Text;
	
	StudentDB s;
	Student stu;
	AdditStudent aClick, bClick; //delete 메소드 부르기위해(additStudent)
	String studentNumber;
	
	public StudentPanel(){
		s = new StudentDB(); // db에 저장된 데이터를 가져오기 위해
		stu = new Student(); //search할때 값 가져오기 위해
		stulist = s.getMemberList();
		
		this.setLayout(new BorderLayout());
		JPanel topS;
		topS = makesT();
		this.add(topS, BorderLayout.NORTH);
		
		JPanel midS = new JPanel();
		studentT = makeStudentTable();
		midS.add(studentT);
		this.add(midS, BorderLayout.CENTER);
		
		JPanel botS;
		botS = makesB();
		this.add(botS, BorderLayout.SOUTH);
	}

	//Top패널 부분 
	public JPanel makesT() {
		cols = getColumn();//학생 검색하기 위해 데이터 받아옴
		
		JPanel sTop = new JPanel();
		JPanel sTop_search = new JPanel();
		JPanel sTop_mid = new JPanel();
		JPanel sTop_mod = new JPanel();
		
		sTop.setLayout(new BorderLayout());
		sTop_search.setLayout(new FlowLayout());
		sTop_mid.setLayout(new FlowLayout());
		sTop_mod.setLayout(new FlowLayout());
		
		//search_jcb.setSelectedItem(cols);
		topic = new JLabel("Student");
		topic.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 40));
		
		
		search_Text = new JTextField("");//나중에 디비로 이어지게
		search_Text.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		search_Text.setPreferredSize(new Dimension(150, 50));
		
		String[] head = {"학번","이름"};
		search_jcb = new JComboBox(head);
		search_jcb.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		search_jcb.setPreferredSize(new Dimension(80, 50));
		
		search_btn = new JButton("학생 검색");
		search_btn.setPreferredSize(new Dimension(120, 50));
		search_btn.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		search_btn.addActionListener(this);
		
		btn = new JButton("학생 등록");
		btn.setPreferredSize(new Dimension(130, 50));
		btn.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		btn.addActionListener(this);
		
		mbtn = new JButton("정보 수정");
		mbtn.setPreferredSize(new Dimension(130, 50));
		mbtn.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		mbtn.addActionListener(this);
		
		dbtn = new JButton("학생 삭제");
		dbtn.setPreferredSize(new Dimension(130, 50));
		dbtn.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		dbtn.addActionListener(this);
		
	
		sTop_search.add(search_jcb);
		sTop_search.add(search_Text);
		sTop_search.add(search_btn);
		sTop_mid.add(topic);
		sTop_mod.add(btn);
		sTop_mod.add(mbtn);
		sTop_mod.add(dbtn);
		
		sTop.add(sTop_search, BorderLayout.WEST);
		sTop.add(sTop_mid, BorderLayout.CENTER);
		sTop.add(sTop_mod,BorderLayout.EAST);

		return sTop;
	}
	
	//mid 패널부분
	public JScrollPane makeStudentTable() {
		StudentDB studb = new StudentDB();
		Font f = new Font("HY엽서L", Font.BOLD, 20);//테이블 헤더 설정
		
		cols = getColumn(); //헤더 데이터 받음
		model = new DefaultTableModel(stulist, cols);
		table = new JTable(model);
		
		table.getTableHeader().setReorderingAllowed(false);//column 위치변경x
		//table.getTableHeader().setResizingAllowed(false);//크기조절 불가
		table.setFont(new Font("HY엽서L", 1, 15));//테이블 폰트변경
		table.setRowHeight(50);//테이블 높이변경
		table.addMouseListener(this);
		JScrollPane pane = new JScrollPane(table);
		
		//테이블 스크롤 정책(넣어야 스크롤 생김)
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JTableHeader header = table.getTableHeader();
		header.setFont(f);
		
		pane.setPreferredSize(new java.awt.Dimension(1185, 601));//테이블 사이즈 조정
		table.getColumn("번호").setPreferredWidth(1);
		table.getColumn("이름").setPreferredWidth(10);
		table.getColumn("학년").setPreferredWidth(10);
		table.getColumn("성별").setPreferredWidth(5);
		table.getColumn("휴대폰번호").setPreferredWidth(100);
		table.getColumn("특이사항").setPreferredWidth(300);
		table.getColumn("성적").setPreferredWidth(10);
		
		return pane;
	}
	
	public Vector getColumn() {
		Vector col = new Vector();
		col.add("번호");
		col.add("학번");
		col.add("이름");
		col.add("학년");
		col.add("성별");
		col.add("휴대폰번호");
		col.add("생년월일");
		col.add("특이사항");
		col.add("성적");
		return col;
	}
	
	public void JTableRefresh() {
		StudentDB stdb = new StudentDB();
		DefaultTableModel model = new DefaultTableModel(stdb.getMemberList(), getColumn());//DB데이터 다시 받아 테이블 초기화
		this.model = model;
		table.setModel(model); // 테이블 새로고침
		
		studentNumber = null;
		
		//버그 발생시 여기부분이 제일 큼
		table.getColumn("번호").setPreferredWidth(1);
		table.getColumn("이름").setPreferredWidth(10);
		table.getColumn("학년").setPreferredWidth(10);
		table.getColumn("성별").setPreferredWidth(5);
		table.getColumn("휴대폰번호").setPreferredWidth(100);
		table.getColumn("특이사항").setPreferredWidth(300);
		table.getColumn("성적").setPreferredWidth(10);
		
	}
	//bottom부분
	public JPanel makesB() {
		
		JPanel sBot_sort = new JPanel();
	
		sBot_sort.setLayout(new FlowLayout(FlowLayout.LEFT));
		resetB = new JButton("▼  번호정렬");
		stuNumSort = new JButton("▼  학번정렬");
		nameSort = new JButton("▼  이름정렬");
		//stuNumSort.setBounds(0,0,0,0);
		resetB.addActionListener(this);
		stuNumSort.addActionListener(this);
		nameSort.addActionListener(this);
		
		sBot_sort.add(resetB);
		sBot_sort.add(stuNumSort);
		sBot_sort.add(nameSort);
		
		
		return sBot_sort;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 JButton b = (JButton) e.getSource();
		 int selectedIndex = search_jcb.getSelectedIndex();
		 //String name = (String)jcb.getSelectedItem(); // 선택한 콤보박스 string 값을 가져옴

		 if (b.getText().equals("학생 등록")) {
		 	System.out.println("학생등록을 누름");
		 	new AdditStudent(this);
		 	//studentNumber = null;
		 	
		 }else if(b.getText().equals("정보 수정")) {
			if(studentNumber == null) {
				JOptionPane.showMessageDialog(this, "정보 수정 실패 : 수정 하려는 줄을 선택하시오.");
			}else {
				bClick = new AdditStudent(studentNumber, this, 2);
			}
			
		 }else if(b.getText().equals("학생 삭제")) {
			System.out.println("학생삭제");
			if(studentNumber == null) {
				JOptionPane.showMessageDialog(this, "삭제실패 : 삭제하려는 줄을 선택하시오.");
			}else {
				aClick.deleteStudent(studentNumber);
				this.JTableRefresh();//action부분에 넣어야함.
				//studentNumber = null;
			}
		 }else if(b.getText().equals("▼  이름정렬")) {
			 s.studentCheck(model);
			 studentNumber=null;
		 }else if(b.getText().equals("▼  학번정렬")) {
			 s.stuNumCheck(model);
			 studentNumber=null;
		 }else if(b.getText().equals("▼  번호정렬")) {
			 s.numberCheck(model);
			 studentNumber=null;
		 }else if(b.getText().equals("학생 검색")) {
			 if(selectedIndex == 0) {
				 String getString = search_Text.getText();
				 System.out.println(getString);
				 s.stuNumberSearch(model, getString);
				 studentNumber=null;
			 }else if(selectedIndex == 1) {
				 String getString = search_Text.getText();
				 System.out.println(getString);
				 s.nameSearch(model, getString);
				 studentNumber=null;
			 }
		 }
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int rowSelect = table.getSelectedRow();
		studentNumber = (String)table.getValueAt(rowSelect,1); //2번째 row에 있는 학번 가져옴
		System.out.println(studentNumber);//몇번째 줄 클릭했는지 위치 확인
		aClick = new AdditStudent(studentNumber, this, 1);//수정할때 클릭한 데이터 넘기기 위해
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {	
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
