package NJU.HouseWang.nju_eas_server.po.Edu;

import NJU.HouseWang.nju_eas_server.po.DataPOService;

public class CoursePO extends PlannedCoursePO {

	private int studentNum; // 学生人数
	private String teacherId;// 授课老师的工号
	private String teacherName;// 授课老师的姓名
	private String timeAndPlace;// 上课时间及地点
	private String introduction;// 介绍
	private String book;// 参考书目
	private String syllabus;// 教学大纲
	private String grade;	//年级

	public CoursePO() {
	}

	public CoursePO(String id, String name, String module, String type,
			String nature, int credit, int period, int term,
			String department, int studentNum, String teacherId,
			String teacherName, String timeAndPlace, String place,
			String introduction, String book, String syllabus,String grade) {
		super(id, name, module, type, nature, credit, period, term, department);
		this.setDepartment(department);
		this.studentNum = studentNum;
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.timeAndPlace = timeAndPlace;
		this.introduction = introduction;
		this.book = book;
		this.syllabus = syllabus;
	}

	public CoursePO(String id, String name, String module, String type,
			String nature, int credit, int period, int term,
			String department,String grade) {
		super(id, name, module, type, nature, credit, period, term, department);
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

	public String getTimeAndPlace() {
		return timeAndPlace;
	}

	public void setTimeAndPlace(String timeAndPlace) {
		this.timeAndPlace = timeAndPlace;
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
		return (super.toCommand() + "；" + studentNum + "；" + teacherId + "；"
				+ teacherName + "；" + timeAndPlace + "；" + introduction + "；"
				+ book + "；" + syllabus + "；" + grade);
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
