package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;

public interface CourseInfoLogicService extends LogicService {
	
	
	// 显示课程条目的详细信息
	public String showCourseDetail(String listName, String department, String id);

	// 修改课程
	public String editCourse(String listName, CoursePO c);

	// 新增课程
	public String addCourse(CoursePO c);
	
	//新增通识课
	public String addCommonCourse(CoursePO c);

	// 删除课程
	public String delCourse(String listName, String department, String id);

	//删除通识课
	public String delCommonCourse(String id);
	
	//修改通识课
	public String editCommonCourse(CoursePO c);
	
	// 查看课程列表
	public ArrayList<String> showCourseList(String listName, String conditions);

	//查看通识课列表
	public ArrayList<String> showCommonCourseList();
	
	//查看可选的通识课列表
	public ArrayList<String> showSelectedCommonCourseList();
	
	// 批量新增课程
	public String addCourseList(ArrayList<String> list);

	// 从教学计划中自动生成课程列表
	public String addCourseListFromTP();

	// 显示课程列表的表头
	public String showCourseListHead();
	
	//显示通识课列表的表头
	public String showCommonCourseListHead();

	//显示可选的通识课列表的表头
		public String showSelectedCommonCourseListHead();
		
	// 登记成绩
	public String registerScore(String listName, ArrayList<String> list, String scoreType);

	// 查看学生课程列表
	public ArrayList<String> showStudentCourseList(String studentId);

	// 查看学生成绩列表
	public ArrayList<String> showStudentScoreList(String listName, String studentId);

	// 查看教师任课的学生信息
	public ArrayList<String> showStudentListFromTeacherAndCourse(
			String teacherId, String courseId);

	// 返回学年学期
	public String showTerm();
	
	//设置学年学期
	public String editTerm(String term);
	
	// 发布通识课
	public String publishCommonCourse(ArrayList<String> list);
	
	//将teachingPlanItemPO转化为coursePO
	public CoursePO tpPOToCoursePO(String dept, TeachingPlanItemPO tpip);
	
	//显示学期列表
	public ArrayList<String> showTermList();
}
