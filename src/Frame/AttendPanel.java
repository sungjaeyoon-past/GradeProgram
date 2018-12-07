package Frame;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import DBInfo.AttendDB;
import Main.Student;
import Window.ModifyDialog;
import DBInfo.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendPanel extends TopPanel {
	private ModifyDialog modifier;
	private JScrollPane sp;
	private JTable table;
	private JPanel midP;
	
	private String tHead[] = {"순번", "학번", "이름", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
				"출석", "지각", "결석", "비고"};
	private Vector<String> memberList;
	private String members[][];
	// DB에 입력되어있는 학생 수만큼 받는 것으로 바꾸어야 한다.
	
	// for Paging
	private int currentEntryIndex;
	private int numberOfEntries;
	// 이전 페이지의 데이터 10개
	private String curDatas[][];
	private JTextField indexTextField;
	private JTextField maxTextField;
	
	DefaultTableModel model;
	private JComboBox nameSort;
	private JButton search;
	private JButton modify;
	private JButton previousPage;
	private JButton nextPage;
	private JTextField typeName;
	
	AttendDB stu_db;	//StudentDB 자식
	Student stu;
	Connection con;
	ResultSet rs;
//	PreparedStatement pstmt;
	
	public AttendPanel() {
		//DB가 있을 때 없을 때를 나누어야 할까
		stu_db = new AttendDB(this);
		
		memberList = stu_db.getMemberList(); // 2nd dim Vector
		if(memberList == null) {
			currentEntryIndex = 0;
			numberOfEntries = 0;
		}
		// table에 넣기 위해 필요한 세팅
		members = new String[memberList.size()][23];
		System.out.print("check");
		members = setProperty(memberList.size(), 23);
		// members(전체)에서 curDatas(10개만)
		curDatas = new String[10][23];
		curDatas = getMembers(0);
		
		JPanel topP = makeTop();
		midP = new JPanel();
		JPanel botP;
		
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
		sp = makeAttendTable();
		midP.add(sp);
		
		midP.add(sp);
		// 1PAGE = rs.next() 10번 읽는다. 
		botP = makePaging();
		
		this.add(topP, BorderLayout.NORTH);
		this.add(midP, BorderLayout.CENTER);
		this.add(botP, BorderLayout.SOUTH);
	}
	
	// setProperty(int x, int y) : 순번, 학번, 이름 부분만 값을 넣어준다.
	public String[][] setProperty(int x, int y) {
		String[][] mem = new String[x][y];
		try {
			con = stu_db.getConnection().makeConnection();
			String sql = "SELECT * FROM student.attend";
			PreparedStatement pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		
			for(int i=0; i<x; i++) {
				rs.next();
				mem[i][0] = rs.getString("number");
				mem[i][1] = rs.getString("studentNumber");
				mem[i][2] = rs.getString("name");
				mem[i][3] = rs.getString("attendString").substring(0, 1); // 1
				mem[i][4] = rs.getString("attendString").substring(1, 2); // 2
				mem[i][5] = rs.getString("attendString").substring(2, 3); // 3
				mem[i][6] = rs.getString("attendString").substring(3, 4); // 4
				mem[i][7] = rs.getString("attendString").substring(4, 5); // 5
				mem[i][8] = rs.getString("attendString").substring(5, 6); // 6
				mem[i][9] = rs.getString("attendString").substring(6, 7); // 7
				mem[i][10] = rs.getString("attendString").substring(7, 8); // 8
				mem[i][11] = rs.getString("attendString").substring(8, 9); // 9
				mem[i][12] = rs.getString("attendString").substring(9, 10); // 10
				mem[i][13] = rs.getString("attendString").substring(10, 11); // 11
				mem[i][14] = rs.getString("attendString").substring(11, 12); // 12
				mem[i][15] = rs.getString("attendString").substring(12, 13); // 13
				mem[i][16] = rs.getString("attendString").substring(13, 14); // 14
				mem[i][17] = rs.getString("attendString").substring(14, 15); // 15
				mem[i][18] = rs.getString("attendString").substring(15, 16); // 16
				mem[i][19] = rs.getString("att");
				mem[i][20] = rs.getString("late");
				mem[i][21] = rs.getString("abs");
				mem[i][22] = rs.getString("extra");
				if(i%10 == 0) {
					numberOfEntries++;
				}
			}
			rs.close();
			pstmt.close();
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return mem;
	}
	
	// getMembers(int start) : members에서 start~start+10까지의 data를 가져온다.
	public String[][] getMembers(int start){
		String[][] mem = new String[10][23];
		int check = start/10;
		
		for(int i=start; i<start+10; i++) {
			for(int j=0; j<23; j++) {
				if(memberList.size() > i)
				mem[i-(check*10)][j] = members[i][j];
			}
		}
		
		return mem;
	}
	
	// makeTop() : Top부분 패널이다.
	public JPanel makeTop() {
		JPanel topP = new JPanel();
		JPanel topP_search = new JPanel();	// 검색을 누르면 setDefault로 해당 레코드 하나만 보여준다. 이름쪽 박스변경. 이름 또는 학번으로 검색. 크기조정 필요
		JPanel topP_mod = new JPanel();	// 출결DB필요. 학생DB와 갯수를 맞추자. 테이블 수정불가 설정, 특정 레코드 클릭 후 수정 클릭시, 해당 레코드에 대한 별도의 입력창 보여줌. 변경 후 확인 누르면 변경사항이 출결DB에 기록.
		
		topP.setLayout(new BorderLayout());
		topP_search.setLayout(new FlowLayout());
		topP_mod.setLayout(new FlowLayout());
		
		String head[] = {"학 번", "이 름"};
		nameSort = new JComboBox(head);
		nameSort.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		nameSort.setPreferredSize(new Dimension(80, 50));
		search = new JButton("검 색");
		search.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		search.setPreferredSize(new Dimension(90, 50));
		typeName = new JTextField("");
		typeName.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		typeName.setPreferredSize(new Dimension(150, 50));
		topP_search.add(nameSort);
		topP_search.add(typeName);
		topP_search.add(search);
		modify = new JButton("출석 수정");
		modify.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 20));
		modify.setPreferredSize(new Dimension(150, 50));
		topP_mod.add(modify);
		
		topP.add(topP_search, BorderLayout.WEST);
		topP.add(topP_mod, BorderLayout.EAST);
		
		//검색 기능
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		
		//출석수정 기능
		modify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modifyActionPerformed(e);
			}
		});
		
		return topP;
	}
	
	private void searchActionPerformed(ActionEvent e) {
		//textField에 정보가 입력되었는지, 또 올바르게 입력되었는지 확인한다.
		//입력되었으면, 콤보박스를 확인한다. ResultSet으로 이름만 또는 학번만 가져와서 콤보박스의 설정과 동일한지 확인한다.
		//확인이 되면 DefaultTableModel을 리셋한다.
		//AttendDB를 만들어야할지도 모르겠다. 결국 학생이 등록되어야 출결도 체크할 수 있는 것이므로 StudentDB의 정보를 가져다 써야한다. 연동이 되어야한다.
		//AttendDB를 StudentDB로부터 상속을 받을까?
		//Schema AttendTable (순번, 학번, 이름, 1~16주, 출석, 지각, 결석, 비고)
		if(typeName.getText().equals("")) {
			System.out.println("check");
			JOptionPane.showMessageDialog(null, "텍스트필드에 검색 내용을 입력하시오.");
		}
		else{
			System.out.println(nameSort.getSelectedIndex());
			if(nameSort.getSelectedIndex() == 0 && stu_db.isNum(typeName.getText())) {
				//typeName.getText()와 AttendDB의 studentFound를 이용해 
				stu_db.searchAttendData(model, typeName.getText());
				
				
			}
			else if(nameSort.getSelectedIndex() == 1 && !(stu_db.isNum(typeName.getText()))) {
				stu_db.searchAttendData(model, typeName.getText());
			}
		}
	}
	
	private void modifyActionPerformed(ActionEvent e) {
		//선결적으로 table이 수정불가하여져야한다.
		//특정 레코드를 선택한 후(드래그는 기본기능 완료 후 고려) 출석 수정버튼을 클릭하면
		//수정윈도우가 뜨게되고 해당 레코드의 현재 정보가 텍스트필드로 출력된다. 아래에는 확인과 취소 버튼이 존재한다.
		//텍스트필드에서 수정을 하고 나면 확인을 누른다. 확인이 눌리면 현재 세팅이 갱신되고, Table이 리셋된다. 데이터베이스도 리셋된다.
		boolean check=false;
		String num = new String();
		for(int i=0; i<table.getRowCount(); i++) {
			for(int j=0; j<table.getColumnCount(); j++) {
				if(table.isCellSelected(i, j)) {
					check = true;
					num = (String) table.getValueAt(i, 1);
					break;
				}
			}
			if(check) break;
		}
		if(check) {
			System.out.println(num);
			modifier = new ModifyDialog(num, stu_db, this);
		}
		else {
			JOptionPane.showMessageDialog(null, "수정할 셀을 선택하십시오.");
		}
	}
	public void resetting() {
		System.out.println("리셋");
		numberOfEntries = 0;
		members = setProperty(memberList.size(), 23);
		curDatas = getMembers(10*currentEntryIndex);
		model.setDataVector(curDatas, tHead);
		table.getColumn("학번").setPreferredWidth(150);
	}

	// 출결 Table을 만든다.
	public JScrollPane makeAttendTable() {
		model = new DefaultTableModel(curDatas, tHead) {
			//수정 불가, 선택 가능
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);//column 위치변경x
		table.setFont(new Font("HY엽서L", 1, 15));//테이블 폰트변경
		table.setRowHeight(50);//테이블 높이변경
		table.getColumn("학번").setPreferredWidth(150);
		//재배열 및 크기조정 불가
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
//		table.addMouseListener(this);
		JScrollPane sc = new JScrollPane(table);
		sc.setPreferredSize(new java.awt.Dimension(1185,525));
		sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		return sc;
	}
	
	// makePaging() : Bottom패널을 만들고 기능을 구현한다.
	public JPanel makePaging() {
		JPanel botP = new JPanel();
		JPanel paging = new JPanel();
		previousPage = new JButton("◀ ");
		nextPage = new JButton("▶ ");
		indexTextField = new JTextField(2);
		maxTextField = new JTextField(2);
		
		botP.setLayout(new BorderLayout());
		paging.setLayout(new FlowLayout());
		
		paging.add(previousPage);
		paging.add(new JLabel(" Page "));
		paging.add(indexTextField);
		paging.add(new JLabel(" / "));
		paging.add(maxTextField);
		paging.add(nextPage);
		
		indexTextField.setText("1");
		maxTextField.setText(Integer.toString(numberOfEntries));
		previousPage.setEnabled(false);
		// previousPage event
		previousPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				previousPageActionPerformed(e);
			}
		});
		
		if(numberOfEntries > 0) {
			nextPage.setEnabled(true);
		}
		else {
			nextPage.setEnabled(false);
		}
		nextPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nextPageActionPerformed(e);
			}
		});
		
		botP.add(paging, BorderLayout.CENTER);
		
		return botP;
	}
	private void previousPageActionPerformed(ActionEvent e) {
		currentEntryIndex--;
		
		if(currentEntryIndex <= 0) {
			previousPage.setEnabled(false);
		}
		nextPage.setEnabled(true);
		indexTextField.setText("" + (currentEntryIndex + 1));
		// actual actionPerform
		indexTextFieldActionPerformed(e);
	}
	private void indexTextFieldActionPerformed(ActionEvent e) {
		currentEntryIndex = (Integer.parseInt(indexTextField.getText()) - 1);
		
		if(numberOfEntries != 0 && currentEntryIndex < numberOfEntries) {
			curDatas = getMembers(10*currentEntryIndex);
			model.setDataVector(curDatas, tHead);
			table.getColumn("학번").setPreferredWidth(150);
		}
	}
	
	private void nextPageActionPerformed(ActionEvent e) {
		currentEntryIndex++;
		if(currentEntryIndex+1 >= numberOfEntries) {
			nextPage.setEnabled(false);
		}
		previousPage.setEnabled(true);
		indexTextField.setText("" + (currentEntryIndex + 1));
		// actual actionPerform
		indexTextFieldActionPerformed(e);
	}
}
