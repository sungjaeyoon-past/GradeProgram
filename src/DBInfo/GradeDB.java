package DBInfo;

import Frame.StudentPanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;

import Frame.GradePanel;

public class GradeDB {

	private ConnectionDB connectDB;
	private GradePanel gp;
	private String[] fieldName = new String[15];
	private int[] ratio;

	public int[] getRatio() {
		return ratio;
	}

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
		fieldNum = 0;
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

			ratio = new int[fieldNum - 4];
			sql = "select * from graderatio where idgradeRatio=1";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			for (int i = 4; i < fieldNum; i++) {
				ratio[i - 4] = rs.getInt(fieldName[i]);
			}

			sql = "select * from grade";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			int number = 1;
			while (rs.next()) {
				Vector row = new Vector();
				row.add(number++);
				for (int i = 1; i < fieldNum; i++) {
					row.add(rs.getString(fieldName[i]));
				}
				row.add(accumulateSum(row));
				data.add(row);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	};

	public int accumulateSum(Vector v) {
		int sum = 0;

		for (int i = 4; i < fieldNum; i++) {
			sum += (Integer.parseInt((String) v.get(i)) * ratio[i - 4]) / 100;
		}

		return sum;
	}

	public int[] getGradeRatio() {
		return ratio;
	}

	public boolean setRatio(JTextField[] jf) {
		String query = "";

		for (int i = 0; i < fieldNum - 4; i++) {
			query += fieldName[i + 4] + "=" + jf[i].getText() + ", ";
		}
		query = query.substring(0, query.length() - 2);

		String sql = "UPDATE graderatio SET " + query + " WHERE idgradeRatio=1;";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void addColumn(String newCol) {
		String sql = "ALTER TABLE `student`.`grade` ADD COLUMN " + newCol + " INT NOT NULL DEFAULT 0";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		sql = "ALTER TABLE `student`.`graderatio` ADD COLUMN " + newCol + " INT NOT NULL DEFAULT 0";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeColumn(String removeCol) {
		String sql = "ALTER TABLE `student`.`grade` DROP " + removeCol;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();

			sql = "ALTER TABLE `student`.`graderatio` DROP " + removeCol;
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
		} catch (Exception e) {
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
