package NJU.HouseWang.nju_eas_server.logicService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.po.Edu.Course_StudentPO;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;

/**
 * 课程信息逻辑类
 * @author 教化场
 * @version 2013-11-7
 */
public interface CourseInfoLogicService extends LogicService {

	/**
	 * 显示课程细节信息
	 * @param listName 列表名
	 * @param department 院系
	 * @param courseId 课程号
	 * @return 课程细节信息列表
	 */
	public ArrayList<String> showCourseDetail(String listName, String department, String courseId);

	/**
	 * 编辑课程
	 * @param term 学期
	 * @param courseId 课程号
	 * @param dept 院系
	 * @param content 内容列表
	 * @return 反馈
	 */
	public String editCourse(String term, String courseId, String dept,  ArrayList<String> content);
	
	/**
	 * 添加课程
	 * @param c 课程PO
	 * @return 反馈
	 */
	public String addCourse(CoursePO c);

	/**
	 * 添加通识课
	 * @param c 课程PO
	 * @return 反馈
	 */
	public String addCommonCourse(CoursePO c);

	/**
	 * 删除课程
	 * @param listName 列表名
	 * @param department 院系
	 * @param id 课程号
	 * @return 反馈
	 */
	public String delCourse(String listName, String department, String id);

	/**
	 * 删除通识课
	 * @param id 课程号
	 * @return 反馈
	 */
	public String delCommonCourse(String id);

	/**
	 * 编辑通识课
	 * @param c 课程PO
	 * @return 反馈
	 */
	public String editCommonCourse(CoursePO c);

	/**
	 * 显示课程列表
	 * @param listName 列表名
	 * @param conditions 条件
	 * @return 课程列表
	 */
	public ArrayList<String> showCourseList(String listName, String conditions);

	/**
	 * 显示通识课列表
	 * @return 通识课列表
	 */
	public ArrayList<String> showCommonCourseList();

	/**
	 * 显示可选通识课列表
	 * @return 可选课程列表
	 */
	public ArrayList<String> showSelectableCommonCourseList();

	/**
	 * 批量新增课程
	 * @return 反馈
	 */
	public String addCourseList(ArrayList<String> list);

	/**
	 * 从教学计划中自动生成课程列表
	 * @param deptName 院系名
	 * @return 反馈
	 */
	public String addCourseListFromTP(String deptName);

	/**
	 * 显示课程列表的表头
	 * @return 表头
	 */
	public String showCourseListHead();

	/**
	 * 显示通识课列表的表头
	 * @return 表头
	 */
	public String showCommonCourseListHead();

	/**
	 * 显示可选的通识课列表的表头
	 * @return 表头
	 */
	public String showSelectableCommonCourseListHead();

	/**
	 * 登记成绩
	 * @param term 学期
	 * @param listName 列表名 
	 * @return 反馈
	 */
	public String recordScore(String term, ArrayList<String> listName);

	/**
	 * 查看学生课程列表
	 * @param studentId 学号
	 * @return 学生课程列表
	 */
	public ArrayList<String> showStudentCourseList(String studentId);

	/**
	 * 查看学生成绩列表
	 * @param listName 列表名
	 * @param studentId 学号
	 * @return 学生成绩列表
	 */
	public ArrayList<String> showStudentScoreList(String listName,
			String studentId);

	/**
	 * 查看教师任课的学生信息
	 * @param courseId 课程号
	 * @param department 院系
	 * @return 课程学生列表
	 */
	public ArrayList<String> showCourseStudentList(String courseId,
			String department);

	/**
	 * 返回学年学期
	 * @return 学期
	 */
	public String showTerm();

	/**
	 * 设置学年学期
	 * @param term 学期
	 * @return 反馈
	 */
	public String editTerm(String term);

	/**
	 * 发布通识课
	 * @param list 列表名
	 * @return 反馈
	 */
	public String publishCommonCourse(ArrayList<String> list);

	/**
	 * 将teachingPlanItemPO转化为coursePO
	 * @param dept 院系
	 * @param tpip 教学计划PO
	 * @return 课程PO
	 */
	public CoursePO tpPOToCoursePO(String dept, TeachingPlanItemPO tpip);

	/**
	 * 显示学期列表
	 * @return 学期列表
	 */
	public ArrayList<String> showTermList();

	/**
	 * 显示选择某课程的学生信息
	 * @param term 学期
	 * @param courseId 课程号
	 * @param dept 院系
	 * @return 学生信息列表
	 */
	public ArrayList<String> showStudentListFromCourse(String term,
			String courseId, String dept);
	
	/**
	 * 显示成绩列表表头
	 * @return 表头
	 */
	public String showCourseStudentListHead();
	
	/**
	 * 显示用于编辑的通识课列表表头
	 * @return 表头
	 */
	public String showEditCommonCourseListHead();
	
	/**
	 * 显示用于编辑的通识课的信息
	 * @param courseId 课程号
	 * @return 用于编辑的通识课信息
	 */
	public String showEditCommonCourse(String courseId);
}
