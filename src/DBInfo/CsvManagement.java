package DBInfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Main.GradeRatio;
import Main.Student;

public class CsvManagement {
	ConnectionDB connectDB;
	GradeDB db;
	StudentDB st;
	Connection con = null;

	public CsvManagement() {
		connectDB = new ConnectionDB();
		con = connectDB.makeConnection();
		db = new GradeDB();
		st = new StudentDB();
	}

	public void openCsv(String filepath) {
		BufferedReader br = null;
		PreparedStatement ps = null;
		String sql;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "euc-kr"));
			String line;
			String opendGradeRatio[] = null;

// 학점비율 업데이트
			line = br.readLine();line = br.readLine();//학점라인
			opendGradeRatio = line.split(",");
			GradeRatio gr = new GradeRatio(Integer.parseInt(opendGradeRatio[0]), Integer.parseInt(opendGradeRatio[1]),
					Integer.parseInt(opendGradeRatio[2]), Integer.parseInt(opendGradeRatio[3]),
					Integer.parseInt(opendGradeRatio[4]), Integer.parseInt(opendGradeRatio[5]),
					Integer.parseInt(opendGradeRatio[6]), Integer.parseInt(opendGradeRatio[7]),
					Integer.parseInt(opendGradeRatio[8]));
			db.setGradeRate(gr);
			line = br.readLine();line = br.readLine();

// 항목 추가, 제거, 비율업데이트
			String itemName[] = db.getFieldName();
			int itemNum = db.getFieldNum();

			//원래 있던 항목을 다 날린다.
			for(int i=4;i<itemNum;i++) {
				db.removeColumn(itemName[i]);
			}
			
			//등록된 항목을 다시 설정
			String opendItemName[] = null;
			opendItemName = line.split(",");
			for(String s:opendItemName) {
				s=s.substring(0, s.length() - 3);
				db.addColumn(s);
			}

			//항목 비율 재설정
			line = br.readLine();
			String itemRatio[]=null;
			itemRatio= line.split(",");
			String query="";
			for (int i = 0; i < itemNum - 4; i++) {
				query += itemName[i + 4] + "=" + itemRatio[i] + ", ";
			}
			query = query.substring(0, query.length() - 2);
			sql = "UPDATE graderatio SET " + query + " WHERE idgradeRatio=1;";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
			
			line = br.readLine();line = br.readLine();			
			  
			// 학생 전체 드롭
			sql="delete from grade";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
			sql="delete from student";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
			// 학생 전체 추가
			String std[] = null;
			while ((line = br.readLine()) != null) {
				std = line.split(",");
				sql = "insert into student(number,studentNumber,name,grade,gender,phoneNumber,birthday,remark)"
						+ "values(?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, "1");
				ps.setString(2, std[0]);
				ps.setString(3, std[1]);
				ps.setString(4, std[2]);
				ps.setString(5, std[3]);
				ps.setString(6, std[4]);
				ps.setString(7, std[5]);
				ps.setString(8, std[6]);
				ps.executeUpdate();
				sql = "insert into grade(학번,이름,성적) values(" + std[0] + ",'" + std[1] + "','F')";
				ps = con.prepareStatement(sql);
				ps.executeUpdate();
				int score[] = new int[std.length - 8];
				for (int i = 8; i < std.length; i++) {
					score[i - 8] = Integer.parseInt(std[i]);
				}
				db.setScore(score, std[0]);
				sql = "UPDATE attend SET attendString='"+std[7]+"' WHERE studentNumber='"+std[0]+"'";
				ps = con.prepareStatement(sql);
				ps.executeUpdate();
			}
			
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void saveCsv(String filepath) {
		try {
			BufferedWriter fw = new BufferedWriter(new FileWriter(filepath + ".csv", true));

			// 학점비율 처리
			int ratio[] = db.getGradeRate();
			String ratioName[] = { "A+비율", "A비율", "B+비율", "B비율", "C+비율", "C비율", "D+비율", "D비율", "F비율" };
			for (int i = 0; i < 9; i++) {
				if (i == 9)
					fw.write("" + ratioName[i]);
				fw.write("" + ratioName[i] + ",");
			}
			fw.newLine();
			for (int i = 0; i < 9; i++) {
				if (i == 9)
					fw.write("" + ratio[i]);
				fw.write("" + ratio[i] + ",");
			}
			fw.newLine();
			fw.newLine();

			// 항목비율처리
			String itemName[] = db.getFieldName();
			int itemRatio[] = db.getItemRatioNew();
			int itemNum = db.getFieldNum();
			for (int i = 4; i < itemNum; i++) {
				fw.write(itemName[i] + "비율 ,");
			}
			fw.newLine();
			for (int i = 0; i < itemNum - 4; i++) {
				fw.write(itemRatio[i] + ",");
			}
			fw.newLine();
			fw.newLine();

			// 학생처리
			fw.write("학번, 이름, 학년, 성별, 휴대폰번호, 생년월일, 특이사항, 출결스트링,");
			for (int i = 4; i < itemNum; i++) {
				fw.write(itemName[i] + "점수 ,");
			}
			fw.newLine();
			PreparedStatement ps = con.prepareStatement(
					"Select A.studentNumber, A.name, A.grade, A.gender, A.phoneNumber, A.birthday, A.remark, C.attendString, B.* "
							+ "From student A left JOIN grade B ON A.studentNumber = B.학번 left JOIN attend C ON A.name = C.name ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				fw.write(rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + ","
						+ rs.getString(5) + "," + rs.getString(6) + "," + rs.getString(7) + "," + rs.getString(8)
						+ ",");
				for (int i = 13; i < itemNum + 9; i++) {
					fw.write(rs.getString(i) + ",");
				}
				fw.newLine();
			}

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
