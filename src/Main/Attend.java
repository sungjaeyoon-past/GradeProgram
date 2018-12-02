package Main;

//출석 정보를 AttendPanel로 넘겨주기 위한 클래스이다.
public class Attend {
	private String number;
	private String studentNumber;
	private String name;
	private String attend;
	private int att;
	private int late;
	private int abs;
	private String extra;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAttend() {
		return attend;
	}
	public void setAttend(String attend) {
		this.attend = attend;
	}
	public int getAtt() {
		return att;
	}
	public void setAtt(int att) {
		this.att = att;
	}
	public int getLate() {
		return late;
	}
	public void setLate(int late) {
		this.late = late;
	}
	public int getAbs() {
		return abs;
	}
	public void setAbs(int abs) {
		this.abs = abs;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	@Override
	public String toString() {
		return "Attend [number=" + number + ", studentNumber=" + studentNumber + ", name=" + name + ", attend=" + attend
				+ ", att=" + att + ", late=" + late + ", abs=" + abs + ", extra=" + extra + "]";
	}
	
}
