package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;

public interface CourseInfoLogicService extends LogicService {
	// 显示课程条目的详细信息
	public String showCourseDetail(String time, String department, String id,
			String item);

	// 修改课程
	public String editCourse(String time, CoursePO c);

	// 新增课程
	public String addCourse(String time, CoursePO c);

	// 删除课程
	public String delCourse(String time, String department, String id);

	// 查看课程列表
	public ArrayList<String> showCourseList(String time, String conditions);

	// 批量新增课程
	public String addCourseList();

	// 从教学计划中自动生成课程列表
	public String addCourseListFromTP();

	// 显示课程列表的表头
	public String showCourseListHead();

	// 登记成绩
	public String registerScore(String time);

	// 查看学生课程列表
	public ArrayList<String> showStudentCourseList(String studentId);

	// 查看学生成绩列表
	public ArrayList<String> showStudentScoreList(String time, String studentId);

	// 查看教师任课的学生信息
	public ArrayList<String> showStudentListFromTeacherAndCourse(
			String teacherId, String courseId);

	// 根据系统时间返回学年学期
	public String getTime();
}
