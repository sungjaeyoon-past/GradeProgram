package DBInfo;

import Frame.StudentPanel;
import Main.GradeRatio;
import Main.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;

import Frame.GradePanel;
import Frame.LecturePanel;

public class GradeDB {

	private ConnectionDB connectDB;
	private GradePanel gp;
	private String[] fieldName = new String[15];
	private int[] itemRatio;
	private int gradeRate[];
	private int countStudent = 1;
	private GradeRatio gr;
	private int fieldNum;
	Connection con = null;

	public GradeDB() {
		connectDB = new ConnectionDB();
		con = connectDB.makeConnection();
	}

	GradeDB(GradePanel gp) {
		this.gp = gp;
	}

	/*
	 * studentList[countStudent-1]=new Student();
	 * System.out.println(rs.getString("학번"));
	 * System.out.println(rs.getString("이름")); System.out.println(sum);
	 * studentList[countStudent-1].setStudentNumber(rs.getString("학번"));
	 * studentList[countStudent-1].setName(rs.getString("이름"));
	 * studentList[countStudent-1].setSum(sum);
	 */

	public Vector getMemberList() {
		Vector data = new Vector();
		try {
			gradeRate = getGradeRate();
			getItemList();// 항목들의 이름을 가져옴
			getItemRatio();// 항목들의 비율을 가져옴
			PreparedStatement ps = con.prepareStatement("select * from grade");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vector row = new Vector();
				row.add(countStudent);
				for (int i = 1; i < fieldNum; i++) {
					if (i == 3) {
						row.add("F");
						continue;
					}
					row.add(rs.getString(fieldName[i]));
				}
				double sum = accumulateSum(row);
				row.add(sum);
				data.add(row);
				countStudent++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	};

	// 항목들의 비율을 가져오는 함수
	public void getItemRatio() {
		try {
			itemRatio = new int[fieldNum - 4];
			PreparedStatement ps = con.prepareStatement("select * from graderatio where idgradeRatio=1");
			ResultSet rs = ps.executeQuery();
			rs.next();
			for (int i = 4; i < fieldNum; i++) {
				itemRatio[i - 4] = rs.getInt(fieldName[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 어떤 항목들이 추가되어있는지 가져와서 변수에 넣어주는 쿼리
	public void getItemList() {
		try {
			fieldNum = 0;
			PreparedStatement ps = con.prepareStatement("show full columns from grade");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				fieldName[fieldNum++] = rs.getString("Field");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 학점 비율 가져오는 함수
	public int[] getGradeRate() {
		int[] arr = new int[10];
		PreparedStatement ps = null;
		ResultSet rs = null; // 출력
		String sql = "SELECT AP,A,BP,B,CP,C,DP,D,F FROM student.graderate;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			for (int i = 1; i < 10; i++) {
				arr[i - 1] = rs.getInt(i);
			}
			for (int i : arr) {
				System.out.println(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arr;
	}

	// 총점을 계산해주는 함수
	public double accumulateSum(Vector v) {
		double sum = 0;

		for (int i = 4; i < fieldNum; i++) {
			sum += (Integer.parseInt((String) v.get(i)) * itemRatio[i - 4]) / 100;
		}

		return Double.parseDouble(String.format("%.2f", sum));
	}

	// 항목들의 비율을 설정해주는 함수
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

	// 점수입력에서 저장을 눌렀을 시 저장해주는 함수
	public boolean setScore(int[] arr, String studentNumber) {
		String query = "";
		for (int i = 4; i < fieldNum; i++) {
			query += fieldName[i];
			query += "=" + arr[i - 4] + ", ";
		}
		query = query.substring(0, query.length() - 2);

		String sql = "UPDATE grade SET " + query + " WHERE 학번=" + studentNumber;

		try {
			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 점수입력에서 재입력시 점수를 넘겨주는 함수
	public int[] getScore(String studentNumber) {
		int arr[] = new int[fieldNum];
		String sql = "SELECT * FROM grade WHERE 학번=" + studentNumber;
		try {
			ResultSet rs;
			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			for (int i = 4; i < fieldNum; i++) {
				arr[i - 4] = Integer.parseInt(rs.getString(fieldName[i]));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}

	// 항목을 추가하는 함수
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

	// 항목을 제거하는 함수
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

	public int[] getRatio() {
		return itemRatio;
	}

	public int[] getGradeRatio() {
		return itemRatio;
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
