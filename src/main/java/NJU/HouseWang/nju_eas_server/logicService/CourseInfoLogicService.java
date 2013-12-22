package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.po.Edu.Course_StudentPO;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;

public interface CourseInfoLogicService extends LogicService {

	// 显示课程条目的详细信息
	public ArrayList<String> showCourseDetail(String listName, String department, String courseId);

	// 修改课程
	public String editCourse(String term, String courseId, String dept,  ArrayList<String> content);
	
	// 新增课程
	public String addCourse(CoursePO c);

	// 新增通识课
	public String addCommonCourse(CoursePO c);

	// 删除课程
	public String delCourse(String listName, String department, String id);

	// 删除通识课
	public String delCommonCourse(String id);

	// 修改通识课
	public String editCommonCourse(CoursePO c);

	// 查看课程列表
	public ArrayList<String> showCourseList(String listName, String conditions);

	// 查看通识课列表
	public ArrayList<String> showCommonCourseList();

	// 查看可选的通识课列表
	public ArrayList<String> showSelectableCommonCourseList();

	// 批量新增课程
	public String addCourseList(ArrayList<String> list);

	// 从教学计划中自动生成课程列表
	public String addCourseListFromTP();

	// 显示课程列表的表头
	public String showCourseListHead();

	// 显示通识课列表的表头
	public String showCommonCourseListHead();

	// 显示可选的通识课列表的表头
	public String showSelectableCommonCourseListHead();

	// 登记成绩
	public String recordScore(String term, ArrayList<String> kuist);

	// 查看学生课程列表
	public ArrayList<String> showStudentCourseList(String studentId);

	// 查看学生成绩列表
	public ArrayList<String> showStudentScoreList(String listName,
			String studentId);

	// 查看教师任课的学生信息
	public ArrayList<String> showCourseStudentList(String courseId,
			String department);

	// 返回学年学期
	public String showTerm();

	// 设置学年学期
	public String editTerm(String term);

	// 发布通识课
	public String publishCommonCourse(ArrayList<String> list);

	// 将teachingPlanItemPO转化为coursePO
	public CoursePO tpPOToCoursePO(String dept, TeachingPlanItemPO tpip);

	// 显示学期列表
	public ArrayList<String> showTermList();

	// 显示选择某课程的学生信息
	public ArrayList<String> showStudentListFromCourse(String term,
			String courseId, String dept);
	
	//显示成绩列表表头
	public String showCourseStudentListHead();
	
	//显示用于编辑的通识课列表表头
	public String showEditCommonCourseListHead();
	
	//显示一门通识课的信息
	public String showCommonCourse(String courseId);
}
