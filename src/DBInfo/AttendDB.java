package DBInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Frame.AttendPanel;
import Main.Attend;

//출석 패널과 DB를 연동하기 위한 클래스이다.
public class AttendDB extends StudentDB {
	//student Panel
	//StudentDB's connection ConnectDB
	
	//Attend Table에 Student만큼의 레코드를 넣어준다.
	AttendPanel attpane;
	private Vector attendScore;
	public AttendDB(AttendPanel pane) {
		super();
		attpane = pane;
		Connection con = null; 
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		//for Attend Table
		ResultSet rs_att = null;
		int col = 0;
		int count = 0;	//Attend table record num
		
		//StudentDB의 number, studentNumber, name을 Attend에 저장
		try {
			con = connectDB.makeConnection();
			String sql_stu = "select number, studentNumber, name from student.student";
			String sql_att = "select * from student.attend";
			pstmt = con.prepareStatement(sql_stu);
			pstmt2 = con.prepareStatement(sql_att);
			rs = pstmt.executeQuery();
			rs_att = pstmt2.executeQuery();
			
			getScore();
			sql_att = "insert into student.attend "
					+ "(number, studentNumber, name, attendString, att, late, abs, extra) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?) "
					+ "on duplicate key update "
					+ "number=?, studentNumber=?, name=?, attendString=?, " 
					+ "att=?, late=?, abs=?, extra=?";

			pstmt1 = con.prepareStatement(sql_att);
			while(rs.next()) {
				pstmt1.setString(1, rs.getString("number"));
				pstmt1.setString(2, rs.getString("studentNumber"));
				pstmt1.setString(3, rs.getString("name"));
				pstmt1.setString(4, "xxxxxxxxxxxxxxxx");
				pstmt1.setInt(5, 0);
				pstmt1.setInt(6, 0);
				pstmt1.setInt(7, 16);
				pstmt1.setString(8, "없음");
//				pstmt1.execute();
//				rs_att = pstmt2.executeQuery();
				if(rs_att.next()) {
					pstmt1.setString(9, rs.getString("number"));
					pstmt1.setString(10, rs.getString("studentNumber"));
					pstmt1.setString(11, rs.getString("name"));
					pstmt1.setString(12, rs_att.getString("attendString"));	//attendDB로부터
					pstmt1.setInt(13, rs_att.getInt("att"));//attendDB로부터
					pstmt1.setInt(14, rs_att.getInt("late"));//attendDB로부터
					pstmt1.setInt(15, rs_att.getInt("abs"));//attendDB로부터
					pstmt1.setString(16, rs_att.getString("extra"));//attendDB로부터
				}
				else{
					pstmt1.setString(9, rs.getString("number"));
					pstmt1.setString(10, rs.getString("studentNumber"));
					pstmt1.setString(11, rs.getString("name"));
					pstmt1.setString(12, "");	//attendDB로부터
					pstmt1.setInt(13, 0);//attendDB로부터
					pstmt1.setInt(14, 0);//attendDB로부터
					pstmt1.setInt(15, 16);//attendDB로부터
					pstmt1.setString(16, "없음");//attendDB로부터
				}
				pstmt1.execute();
			}
		}catch(SQLException e) {
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
						rs.getString(4).substring(0, 1),
						rs.getString(4).substring(1, 2),
						rs.getString(4).substring(2, 3),
						rs.getString(4).substring(3, 4),
						rs.getString(4).substring(4, 5),
						rs.getString(4).substring(5, 6),
						rs.getString(4).substring(6, 7),
						rs.getString(4).substring(7, 8),
						rs.getString(4).substring(8, 9),
						rs.getString(4).substring(9, 10),
						rs.getString(4).substring(10, 11),
						rs.getString(4).substring(11, 12),
						rs.getString(4).substring(12, 13),
						rs.getString(4).substring(13, 14),
						rs.getString(4).substring(14, 15),
						rs.getString(4).substring(15, 16),
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
	public void modifyAttendData(String column, String[] data) {
		Connection con = connectDB.makeConnection();
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("update");
		try {
			sql = "update student.attend "
					+ "set attendString=?, "
					+ "att=?, late=?, abs=?, extra=?"
					+ "where studentNumber=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, data[0]);
			pstmt.setString(2, data[1]);
			pstmt.setString(3, data[2]);
			pstmt.setString(4, data[3]);
			pstmt.setString(5, data[4]);
			pstmt.setString(6, column);
			
			pstmt.execute();
			//패널을 받아와 새로고침
			attpane.resetting();
		}catch(SQLException e) {
			System.out.println("SQLException : " + e.getMessage());
		}
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
