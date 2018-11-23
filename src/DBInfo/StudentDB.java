package DBInfo;

import java.sql.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Frame.StudentPanel;
import Main.Student;

public class StudentDB {
	private static final String DRIVER
	= "com.mysql.cj.jdbc.Driver";
	private static final String url 
	= "jdbc:mysql://localhost/student?characterEncoding=UTF-8&serverTimezone=UTC";
	StudentPanel stuP;
	
	StudentDB(){
	}
	
	StudentDB(StudentPanel stuP){
		this.stuP = stuP;
		System.out.println("exampe=>"+stuP);
	}
	
	// 디비 연결 METHOD
	public Connection makeConnection() {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			System.out.println("데이터베이스 연결중..");
			con = DriverManager.getConnection(url, "root", "Dlrudals95`"); // 각자 로컬 아이디로 ..
			System.out.println("데이터베이스 연결 성공");
		}catch(ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}catch(SQLException ex) {
			System.out.println("SQLException:"+ex.getMessage());
		}
		return con;
	}
	
	//한사람 이름으로 회원정보 검색
	public Student getStudent(String name) {
		Student dto = new Student();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = makeConnection();
			String sql = "select * from student where name=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				dto.setNumber(rs.getString("number"));
				dto.setStudentNumber(rs.getString("studentNumber"));
				dto.setName(rs.getString("name"));
				dto.setGrade(rs.getString("grade"));
				dto.setGender(rs.getString("gender"));
				dto.setPhoneNumber(rs.getString("phoneNumber"));
				dto.setBirthday(rs.getString("birthday"));
				dto.setRemarks(rs.getString("remarks"));
				dto.setRatio(rs.getString("ratio"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	//리스트 목록 출력
	public Vector getMemberList() { 
		Vector data = new Vector(); //Jtable에 값 넣기위해
		Connection con = null; //연결하기위해
		PreparedStatement ps = null; //db명령 넣음
		ResultSet rs = null; //출력
		try {
			con = makeConnection();
			String sql = "select * from student order by name asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String number = rs.getString("number");
                String studentNumber = rs.getString("studentNumber");
                String name = rs.getString("name");
                String grade = rs.getString("grade");
                String gender = rs.getString("gender");
                String phoneNumber = rs.getString("phoneNumber");
                String birthday = rs.getString("birthday");
                String remarks = rs.getString("remarks");
                String ratio = rs.getString("ratio");
               
                Vector row = new Vector();
                row.add(number);
                row.add(studentNumber);
                row.add(name);
                row.add(grade);
                row.add(gender);
                row.add(phoneNumber);
                row.add(birthday);
                row.add(remarks);
                row.add(ratio);
  
                data.add(row); //큰 벡터값에 넣음
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	//학생 등록
	public boolean insertStudent(Student std) {
		boolean check = false;
		Connection con = null; //연결하기위해
		PreparedStatement ps = null; //db명령 넣음
		
		try {
			con = makeConnection();
			String sql = "inset into student(number,studentNumber,name,grade,gender,phoneNumber,birthday,remark,ratio)"+
					"values(?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, std.getNumber());
			ps.setString(2, std.getStudentNumber());
			ps.setString(3, std.getName());
			ps.setString(4, std.getGrade());
			ps.setString(5, std.isGender());
			ps.setString(6, std.getPhoneNumber());
			ps.setString(7, std.getBirthday());
			ps.setString(8, std.getRemarks());
			ps.setString(9, std.getRatio());
			
			int saveStu = ps.executeUpdate(); // 실행 => 저장
			if(saveStu>0) {
				System.out.println("가입 성공");
				check=true;
			}else {
				System.out.println("가입 실패");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	//학생 정보 수정
	public boolean updateStudent(Student upstd) {
		System.out.println("std="+upstd.toString()); // 현재 정보 콘솔에 ..(확인하기위해)
		boolean check = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = makeConnection();
			String sql = "update student set number=?, name=?, grade=?, gender=?, phoneNumber=?, birthday=?"+
			", remark=?, ratio=?" + "where studentNumber=?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, upstd.getNumber());
			ps.setString(2, upstd.getName());
			ps.setString(3, upstd.getGrade());
			ps.setString(4, upstd.isGender());
			ps.setString(5, upstd.getPhoneNumber());
			ps.setString(6, upstd.getBirthday());
			ps.setString(7, upstd.getRemarks());
			ps.setString(8, upstd.getRatio());
			ps.setString(9, upstd.getStudentNumber());
			
			int saveStu = ps.executeUpdate(); // 실행 => 저장
			if(saveStu>0) {
				check=true;
			}else {
				System.out.println("업데이트 실패 db");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	//디비 데이터 삭제
	public boolean deleteStudent(String studentNumber) {
		boolean check = false;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = makeConnection();
			String sql = "delete from student where studentNumber=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, studentNumber);
			int saveStu = ps.executeUpdate();
			if(saveStu>0) {
				check = true;
				System.out.println("디비 삭제 성공");
			}else {
				System.out.println("디비 삭제 실패");
			}
		}catch(Exception e) {
			System.out.println(e+"->오류 발생");
		}
		return check;
	}
	
	//db 데이터 다시 부르기(이름 정렬)
	public void studentCheck(DefaultTableModel model) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = makeConnection();
			String sql = "select *from student order by name asc";
			ps = con.prepareStatement(sql); 
			rs = ps.executeQuery();
			while(rs.next()) {
				Object data [] = {
					rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9)
				};
				model.addRow(data);
			}
			
			//tablemodel에 있는 데이터 지우기
			for(int i=0; i<model.getRowCount();) {
				model.removeRow(0);;
			}
		}catch(SQLException e) {
			System.out.println(e);
		}finally{
            if(rs!=null) {
                try {
                    rs.close();
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
            }
            if(ps!=null) {
                try {
                    ps.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            if(con!=null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
