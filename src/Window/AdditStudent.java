package Window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import DBInfo.StudentDB;
import Frame.StudentPanel;
import Main.Student;

//새 창에서 학생을 추가해주는 클래스
public class AdditStudent extends JFrame implements ActionListener{
	JTextField tnumber, tStuNum, tname, tphoneNum;//숫자, 학생, 이름, 휴대폰
	JTextField tratio;// 성적
	JTextArea tremark;//특이사항
	JTextField tYear, tMonth, tDate;//생일
	JComboBox cbGrade; //1~4학년 선택
	JRadioButton rbMan, rbWoman;//남여 성별
	JButton bSave, bExit, bModify;//학생등록, 취소
	
	StudentPanel Pstd;//실제 보여줄 패널
	
	GridBagLayout glay; //gridbagLayout사용, 컴포넌트 위치 및 크기 직접설정 가능
	GridBagConstraints gbc;//컴포넌트에 조건을 설정하기 위해
	
	public AdditStudent() {
		Show();
	}
	
	public AdditStudent(StudentPanel Pstd) {
		Show();
		bModify.setEnabled(false);	
        bModify.setVisible(false);
		this.Pstd = Pstd;
	}
	public AdditStudent(String stuNum, StudentPanel sPanel, int i) {
		if(i == 1) {
			this.Pstd = sPanel;
			StudentDB stdb = new StudentDB();
			Student st = new Student();
			st = stdb.getStudent(stuNum);
		}else {
			this.Pstd = sPanel;
			Show();
			bSave.setEnabled(false);	
	        bSave.setVisible(false);
			StudentDB stdb = new StudentDB();
			Student st = new Student();
			st = stdb.getStudent(stuNum);
			inputData(st);
			
		}
	}
	
	//데이터 받아 화면에 보여주려고..(나중에 디비에서 가져옴)
	public void inputData(Student stu) {
		String number = stu.getNumber();
		String studentNumber = stu.getStudentNumber();
		String name = stu.getName();
		String grade = stu.getGrade();
		String gender = stu.isGender();
		String phoneNumber = stu.getPhoneNumber();
		String birthday = stu.getBirthday();
		String remarks = stu.getRemarks();
		String ratio = stu.getRatio();
		
		//System.out.println(number); //테스트
		//System.out.println(studentNumber); //테스트
		tnumber.setText(number);
		//tnumber.setEditable(false);//수정x
		tStuNum.setText(studentNumber);
		tStuNum.setEditable(false);//수정x
		tname.setText(name);
		tphoneNum.setText(phoneNumber);
		tremark.setText(remarks);
		tratio.setText(ratio);
		
		tYear.setText(birthday.substring(0,4));//4개식 끊어서 읽기
		tMonth.setText(birthday.substring(4,6));
		tDate.setText(birthday.substring(6,8));
		
		cbGrade.setSelectedItem(grade);
		
		if(gender.equals("M")) {
			rbMan.setSelected(true);
		}else if(gender.equals("W")) {
			rbWoman.setSelected(true);
		}
		
	}
	
	//실제 보여지는 창 메소드
	public void Show() {
		this.setTitle("학생 등록");
		glay = new GridBagLayout();
		gbc = new GridBagConstraints();
		
		setLayout(glay);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
	    JLabel st1 = new JLabel("번호 : ");
	    st1.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
	    tnumber = new JTextField(5);
	    gbReset(st1, 0, 0, 1, 1);
	    gbReset(tnumber, 1, 0, 3, 1);
	    
	    JLabel st2 = new JLabel("학번 :");
	    st2.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
	    tStuNum = new JTextField(20);
	    gbReset(st2, 0, 1, 1, 1);
	    gbReset(tStuNum, 1, 1, 3, 1);//위,아래, 공간
	    
	    JLabel st3 = new JLabel("이름 :");
	    st3.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
	    tname = new JTextField(20);
	    gbReset(st3, 0, 2, 1, 1);
	    gbReset(tname, 1, 2, 3, 1);
	    
	    JLabel ygrade = new JLabel("학년 :");
	    ygrade.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
	    String[] aygrade = {"1 학년","2 학년","3 학년","4 학년"};
	    cbGrade = new JComboBox(aygrade);
	    JPanel pgrade = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    pgrade.add(cbGrade);
	    gbReset(ygrade, 0, 3, 1, 1);
	    gbReset(pgrade, 1, 3, 3, 1);
	    
	    JLabel Jgender = new JLabel("성별 :");
	    Jgender.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
	    JPanel Pgender = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    rbMan = new JRadioButton("남", true);
	    rbWoman = new JRadioButton("여", true);
	    ButtonGroup group = new ButtonGroup();
	    group.add(rbMan);
	    group.add(rbWoman);
	    Pgender.add(rbMan);
	    Pgender.add(rbWoman);
	    gbReset(Jgender, 0,4,1,1);
	    gbReset(Pgender, 1,4,3,1);
	    
	    JLabel st4 = new JLabel("연락처 :");
	    st4.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
	    tphoneNum = new JTextField(20);
	    gbReset(st4, 0, 5, 1, 1);
	    gbReset(tphoneNum, 1, 5, 3, 1);
	    
	    JLabel st5 = new JLabel("생년월일 :");
	    st5.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
	    JPanel Pbirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    tYear = new JTextField(6);
	    tMonth = new JTextField(6);
	    tDate = new JTextField(6);
	    Pbirth.add(tYear);
	    Pbirth.add(new JLabel(" / "));
	    Pbirth.add(tMonth);
	    Pbirth.add(new JLabel(" / "));
	    Pbirth.add(tDate);
	    gbReset(st5, 0, 6, 1, 1);
	    gbReset(Pbirth, 1, 6, 3, 1);
	    
	    JLabel st6 = new JLabel("특이사항 :");
	    st6.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
	    tremark = new JTextArea(5, 20);
	    JScrollPane scroll = new JScrollPane(tremark);
	    gbReset(st6, 0, 7, 1, 1);
	    gbReset(scroll, 1, 7, 3, 1);
	    
	    JLabel st7 = new JLabel("성적 :");
	    st7.setFont(new Font("KBIZ한마음고딕 M", Font.BOLD, 15));
	    tratio = new JTextField(20);
	    gbReset(st7, 0, 8, 1, 1);
	    gbReset(tratio, 1, 8, 3, 1);
	    
	    JPanel PButton = new JPanel();
	    bSave = new JButton("저장"); 
	    bModify = new JButton("수정");
	    bExit = new JButton("종료");
	    PButton.add(bSave);
	    PButton.add(bModify);
	    PButton.add(bExit);
	    gbReset(PButton, 0, 9, 4, 1);
	    
	    bSave.addActionListener(this);
	    bModify.addActionListener(this);
	    bExit.addActionListener(this);
	    
		setSize(360,430);
	    setVisible(true);
	    setResizable(false);
	    setLocation(600,300);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);//현재창만 닫음
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b == bSave) {
			System.out.println("창 저장버튼");
			insertStudent();
		}else if(b == bExit) {
			int s = JOptionPane.showConfirmDialog(this, "종료하시면 작성하신 내용이 사라집니다.");
			if(s == JOptionPane.OK_OPTION) {
				dispose(); // 현재 띄워진 프레임 종료
			}else {
				JOptionPane.showMessageDialog(this, "종료를 취소하였습니다.");
			}
		}else if(b == bModify) {
			int s = JOptionPane.showConfirmDialog(this, "정보를 수정 하시겠습니까?");
			if(s == JOptionPane.OK_OPTION) {
				updateStudent();
				dispose(); // 현재 띄워진 프레임 종료
				
			}else {
				JOptionPane.showMessageDialog(this, "정보 수정을 취소하셨습니다.");
			}
		}
		Pstd.JTableRefresh(); //저장후 테이블 다시 새로고침
	}
	
	public void insertStudent() {
		StudentDB stuDB = new StudentDB();
		Student stuget = getData();
		boolean check = stuDB.insertStudent(stuget);//DB에 데이터를 저장
		if(check) {
			JOptionPane.showMessageDialog(this, "학생 저장 완료");
			dispose();
		}else {
			JOptionPane.showMessageDialog(this, "학생 저장이 정상적으로 안되었음");
		}
	}
	
	public void updateStudent() {
		StudentDB stuDB = new StudentDB();
		Student stuget = getData();
		boolean check = stuDB.updateStudent(stuget);//DB에 데이터를 저장
		/*if(check) {
			JOptionPane.showMessageDialog(this, "업데이트 완료");
			dispose();
		}else {
			JOptionPane.showMessageDialog(this, "업데이트 오류");
		}*/
	}
	
	public void deleteStudent(String studentNumber) {
		String stuNum = studentNumber;
		StudentDB stuDB = new StudentDB();
		boolean check = stuDB.deleteStudent(stuNum);
		
		if(check) {
			JOptionPane.showMessageDialog(this, "데이터 삭제완료");
			//Pstd.JTableRefresh();//actionPerformd에서만 실행
            dispose(); 
		}else {
			JOptionPane.showMessageDialog(this, "데이터 삭제오류");
		}
		
	}
	//입력 값 확인하는 메소드
	public Student getData() {
		Student stu = new Student();
		
		String number = tnumber.getText();
		String studentNumber = tStuNum.getText();
		String name = tname.getText();
		String phoneNumber = tphoneNum.getText();
		String remarks = tremark.getText();
		String birth1 = tYear.getText();
		String birth2 = tMonth.getText();
		String birth3 = tDate.getText();
		String birthday = birth1+birth2+birth3;
		String grade = (String)cbGrade.getSelectedItem();
		String gender="";
		if(rbMan.isSelected()) {
			gender="M";
		}else if(rbWoman.isSelected()) {
			gender="W";
		}
		String ratio = tratio.getText();
		
		stu.setNumber(number);
		stu.setStudentNumber(studentNumber);
		stu.setName(name);
		stu.setPhoneNumber(phoneNumber);
		stu.setRemarks(remarks);
		stu.setBirthday(birthday);
		stu.setGrade(grade);
		stu.setGender(gender);
		stu.setRatio(ratio);
		
		return stu;//받은 데이터를 넘김
	}
	
	//컴포넌트 위치 설정
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
