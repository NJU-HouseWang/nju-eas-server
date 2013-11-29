package NJU.HouseWang.nju_eas_server.po.Edu;

import NJU.HouseWang.nju_eas_server.po.DataPOService;

public class CoursePO extends PlannedCoursePO {

	private String department;// 开设的院系
	private String grade; // 上课的年级
	private int studentNum; //学生人数
	private String teacherId;// 授课老师的工号
	private String teacherName;// 授课老师的姓名
	private String time;// 上课时间
	private String place;// 上课地点
	private String introduction;// 介绍
	private String book;// 参考书目
	private String syllabus;// 教学大纲

	public CoursePO() {
	}

	public CoursePO(String id, String name, String module, String type,
			String nature, int credit, int period, String term, String department,
			String grade, int studentNum, String teacherId, String teacherName, String time,
			String place, String introduction, String book, String syllabus) {
		super(id, name, module, type, nature, credit, period,term);
		this.department = department;
		this.grade = grade;
		this.studentNum = studentNum;
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.time = time;
		this.place = place;
		this.introduction = introduction;
		this.book = book;
		this.syllabus = syllabus;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}

	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public String toCommand() {
		return (super.toCommand() + "；" + grade + "；" + studentNum + "；" + teacherId + "；"
				+ teacherName + "；" + time + "；" + place + "；" + introduction
				+ "；" + book + "；" + syllabus);
	}

}
