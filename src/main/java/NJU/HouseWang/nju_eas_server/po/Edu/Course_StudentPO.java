package NJU.HouseWang.nju_eas_server.po.Edu;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.dataService.DataService;
import NJU.HouseWang.nju_eas_server.po.DataPOService;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class Course_StudentPO implements DataPOService {
	private String dept; // 院系
	private String courseId; // 课程编号
	private String studentId; // 学生学号
	private String originalScore; // 原始成绩
	private String secondScore; // 补考成绩

	public Course_StudentPO() {

	}

	public Course_StudentPO(String dept, String courseId, String studentId) {
		this.dept = dept;
		this.courseId = courseId;
		this.studentId = studentId;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getOriginalScore() {
		return originalScore;
	}

	public void setOriginalScore(String originalScore) {
		this.originalScore = originalScore;
	}

	public String getSecondScore() {
		return secondScore;
	}

	public void setSecondScore(String secondScore) {
		this.secondScore = secondScore;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub

	}

	public String toCommand() {
		return (dept + "；" + courseId + "；" + studentId + "；" + originalScore
				+ "；" + secondScore);
	}

}
