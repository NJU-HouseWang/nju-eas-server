package NJU.HouseWang.nju_eas_server.po.Edu;

import NJU.HouseWang.nju_eas_server.po.DataPOService;

public class CoursePO implements DataPOService {
	private String id;// 课程编号
	private String name;// 课程名称
	private String module;// 所属模块
	private String type;// 课程类别
	private String nature;// 课程性质
	private int credit;// 学分
	private int period;// 学时
	private String term; // 开设学期
	private String department;// 开设的院系
	private int studentNum; // 学生人数
	private String teacherId;// 授课老师的工号
	private String teacherName;// 授课老师的姓名
	private String timeAndPlace;// 上课时间及地点
	private String introduction;// 介绍
	private String book;// 参考书目
	private String syllabus;// 教学大纲
	private String grade; // 年级

	public CoursePO() {
	}

	public CoursePO(String id, String name, String module, String type,
			String nature, int credit, int period, String grade, String term,
			String department, int studentNum, String teacherId,
			String teacherName, String timeAndPlace, String place) {
		this.id = id;
		this.name = name;
		this.module = module;
		this.type = type;
		this.nature = nature;
		this.credit = credit;
		this.period = period;
		this.term = term;
		this.department = department;
		this.studentNum = studentNum;
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.timeAndPlace = timeAndPlace;
	}

	public CoursePO(String id, String name, String module, String type,
			String nature, int credit, int period, String grade, String term,
			String department) {
		this.id = id;
		this.name = name;
		this.module = module;
		this.type = type;
		this.nature = nature;
		this.credit = credit;
		this.period = period;
		this.term = term;
		this.department = department;
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Override
	public String getId() {

		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;

	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public String courseToCommand() {
		return (id + "；" + name + "；" + nature + "；" + credit + "；" + period
				+ "；" + grade + "；" + term + "；" + department + "；"
				+ teacherName + "；" + timeAndPlace);
	}

	public String commonCourseToCommand(){
		return (id + "；" + name + "；" + credit + "；" +  teacherName + "；" + timeAndPlace);
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
