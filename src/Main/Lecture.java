package Main;

public class Lecture {
	public Lecture( String name,int lectureNumber, int score, String division, String professor, String roomNumber,int count,String introduce ) {
		this.name=name;
		this.lectureNumber=lectureNumber;
		this.score=score;
		this.division=division;
		this.professor=professor;
		this.roomNumber=roomNumber;
		this.count=count;
		this.introduce=introduce;
	}
	
	private int lectureNumber;
	private String name;
	private int score;//3학점
	private String division;//전공
	private String professor;//전공
	private String roomNumber;//강의실
	private int count;//학생수
	private String introduce;//강의소개
	
	public int getLectureNumber() {
		return lectureNumber;
	}
	public void setLectureNumber(int lectureNumber) {
		this.lectureNumber = lectureNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}	
	
	
}
