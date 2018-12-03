package DBInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Main.Attend;

//출석 패널과 DB를 연동하기 위한 클래스이다.
public class AttendDB extends StudentDB {
	//student Panel
	//StudentDB's connection ConnectDB
	
	//Attend Table에 Student만큼의 레코드를 넣어준다.
	
	private Vector attendScore;
	public AttendDB() {
		super();
		
		Connection con = null; 
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		int col = 0;
		int count = 0;	//Attend table record num
		
		//StudentDB의 number, studentNumber, name을 Attend에 저장
		try {
			con = connectDB.makeConnection();
			String sql_stu = "select number, studentNumber, name from student.student";
			String sql_att = "select number, studentNumber, name from student.attend";
			pstmt = con.prepareStatement(sql_stu);
			pstmt1 = con.prepareStatement(sql_att);
			rs = pstmt.executeQuery();
			getScore();
//			while(pstmt1.executeQuery().next()) {
//				count++;
//			}
//			rs.beforeFirst();
			System.out.println(pstmt1.execute(sql_att));
			if(pstmt1.execute(sql_att)) {
				sql_att = "insert into student.attend "
						+ "(number, studentNumber, name, attend, att, late, abs, extra)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			}
			else if(!(rs.equals(pstmt1.executeQuery()))) { //두 DB가 다르면 attend의 table을 지우고 다시 저장한다.
//				while(rs.next()) {
//					for(int i=0; i<count; i++) {
//						if(!rs.getObject(col).equals(pstmt1.executeQuery().getObject(i))) {
//							
//						}
//					}
//					col++;
//				}
//				sql_att = "insert into student.attend (number, studentNumber, name, abs) values (?, ?, ?, ?)";
				sql_att = "update student.attend set number=?, studentNumber=?, name=?, attend=?,"
						+ " att=?, late=?, abs=?, extra=?";
			}
			pstmt1 = con.prepareStatement(sql_att);
			while(rs.next()) {
				pstmt1.setString(1, rs.getString("number"));
				pstmt1.setString(2, rs.getString("studentNumber"));
				pstmt1.setString(3, rs.getString("name"));
				pstmt1.setString(4, "");
				pstmt1.setInt(5, 0);
				pstmt1.setInt(6, 0);
				pstmt1.setInt(7, 16);
				pstmt1.setString(8, "");
				pstmt1.execute();
			}
		}catch(SQLException e) {
			System.out.print(1);
			System.out.println("SQLException: "+e.getMessage());
		}
	}
	
	//검색 기능
	public void searchAttendData(DefaultTableModel model, String stuText) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			
			con = connectDB.makeConnection();
			if(isNum(stuText)) {
				sql = "select * from student.attend where studentNumber=?";
			}else {
				sql = "select * from student.attend where name=?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, stuText);
			rs = pstmt.executeQuery();
			
			for(int i=0; i<model.getRowCount();) {
				model.removeRow(0);
			}
			while(rs.next()) {
				 String[] attStu = {
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						"","","","","","","","","","","","","","","",
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8)
				};
				model.addRow(attStu);
			}
		}catch(SQLException e) {
			System.out.println("SQL : " + e.getMessage());
		}
		
	}
	public boolean isNum(String i) {
		try {
			Integer.parseInt(i);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	// 출결 수정
	public void modifyAttendData() {
		
	}

	public void getScore(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		attendScore = new Vector();
		
		try {
			con = connectDB.makeConnection();
			String sql = "select studentNumber, att, late, abs from student.attend";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.last();
			
			rs.beforeFirst();
			while(rs.next()) {
				// 100점 / 16주 = 100점 / 32번 = 3.125
				Vector scoreSet = new Vector();
				scoreSet.add(rs.getString(1));
				scoreSet.add(rs.getInt(2)*3.125);
				scoreSet.add(rs.getInt(3)*3.125);
				scoreSet.add(rs.getInt(4)*3.125);
				
				attendScore.add(scoreSet);
			}
			
		}catch(SQLException e) {
			System.out.println("SQLException : "+e.getMessage());
		}
		
//		return attendScore;
	}
}
