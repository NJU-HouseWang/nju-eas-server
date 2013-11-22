package NJU.HouseWang.nju_eas_server.systemService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;

public interface CourseInfSystemService extends SystemService {
	// 显示课程
	public void showCourse(String id);

	// 修改课程
	public void editCourse(CoursePO c);

	// 新增课程
	public void addCourse(CoursePO c);

	// 删除课程
	public void delCourse(String id);

	// 查看课程列表
	public void showCourseList(String conditions);

	// 批量新增课程
	public void addCourseList();

	// 批量删除课程
	public void delCourseList();
}
