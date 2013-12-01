package NJU.HouseWang.nju_eas_server.systemService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.po.Edu.Course_StudentPO;

public interface CourseInfoSystemService extends SystemService {
	// 显示课程条目的详细信息
	public void showCourseDetail(String time ,String department, String id, String item);
		
	// 修改课程
	public void editCourse(String time, CoursePO c);

	// 新增课程
	public void addCourse(String time, CoursePO c);

	// 删除课程
	public void delCourse(String time, String department, String id);

	// 查看课程列表
	public void showCourseList(String time, String conditions);

	// 批量新增课程
	public void addCourseList();
	
	//从教学计划中自动生成课程列表
	public void addCourseListFromTP();
	
	//显示课程列表的表头
	public void showCourseListHead();
	
	//登记成绩
	public void editCourse_Student(String time, Course_StudentPO csp);
	
	//查看学生课程列表
	public void showStudentCourseList(String studentId);
	
	//查看学生成绩列表
	public void showStudentScoreList(String time, String studentId);
	
	//查看教师任课的学生信息
	public void showStudentListFromTeacherAndCourse(String teacherId, String courseId);
	
	
	
	//根据系统时间返回学年学期
	public String getTime();
}
