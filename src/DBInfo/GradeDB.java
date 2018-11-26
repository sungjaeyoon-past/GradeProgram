package DBInfo;

import Frame.StudentPanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Frame.GradePanel;

public class GradeDB {

	private ConnectionDB connectDB;
	private GradePanel gp;
	private String[] fieldName = new String[15];
	private int fieldNum = 0;
	Connection con = null;

	public GradeDB() {
		connectDB = new ConnectionDB();
		con = connectDB.makeConnection();
	}

	GradeDB(GradePanel gp) {
		this.gp = gp;
	}

	public Vector getMemberList() {
		fieldNum=0;
		Vector data = new Vector();
		PreparedStatement ps, ps2 = null;
		ResultSet rs, rs2 = null; // Ãâ·Â

		try {
			String sql = "show full columns from grade";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				fieldName[fieldNum++] = rs.getString("Field");
			}

			sql = "select * from grade";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Vector row = new Vector();
				for (int i = 0; i < fieldNum; i++) {
					row.add(rs.getString(fieldName[i]));
				}
				data.add(row);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	};
	
	public void addColumn(String newCol) {
		String sql = "ALTER TABLE `student`.`grade` ADD COLUMN "+newCol+" INT NOT NULL DEFAULT 0";	
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeColumn(String removeCol) {
		String sql = "ALTER TABLE `student`.`grade` DROP "+removeCol;	
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public String[] getFieldName() {
		return fieldName;
	}

	public void setFieldName(String[] fieldName) {
		this.fieldName = fieldName;
	}

	public int getFieldNum() {
		return fieldNum;
	}

	public void setFieldNum(int fieldNum) {
		this.fieldNum = fieldNum;
	}

}
