package NJU.HouseWang.nju_eas_server.po.User;

import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class StudentPO extends UserPO {
	private String name;// 姓名
	private String department;// 院系
	private String major;// 专业
	private String grade;// 年级
	private String classNo;// 班级编号
	private String duration;// 学制
	private String enrollmentStatus;// 学籍状态

	public StudentPO() {
	}

	public StudentPO(String id, String name, String department, String major,
			String grade, String duration, String enrollmentStatus,
			String classNo) {
		super(id, UserType.Student);
		this.id = id;
		this.name = name;
		this.department = department;
		this.major = major;
		this.grade = grade;
		this.duration = duration;
		this.enrollmentStatus = enrollmentStatus;
		this.classNo = classNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}

	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	@Override
	public String toString() {
		return "StudentPO [name=" + name + ", department=" + department
				+ ", major=" + major + ", grade=" + grade + ", classNo="
				+ classNo + ", duration=" + duration + ", enrollmentStatus="
				+ enrollmentStatus + "]";
	}

	@Override
	public String toCommand() {
		return id + "；" + name + "；" + department + "；" + major + "；" + grade
				+ "；" + classNo + "；" + duration + "；" + enrollmentStatus + "；";
	}

}