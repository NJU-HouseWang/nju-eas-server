package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

/**
 * 课程列表类
 * @author 教化场
 * @version 2013-11-13
 */
public interface CourseListService {
	/**
	 * 创建课程列表
	 * @param term 学期
	 * @return 反馈
	 */
	public Feedback createCourseList(String term);

	/**
	 * 弃选课程列表
	 * @param term 学期
	 * @return 反馈
	 */
	public Feedback dropCourseList(String term);

	/**
	 * 判断是否包含课程
	 * @param term 学期
	 * @param department 院系
	 * @param id 课程号
	 * @return 是否包含课程
	 */
	public boolean containsCourse(String term, String department, String id);

	/**
	 * 由院系和课程号获取课程
	 * @param term 学期
	 * @param department 院系
	 * @param id 课程号
	 * @return 课程PO
	 */
	public CoursePO getCourseFromDeptAndCourseId(String term,
			String department, String id);

	/**
	 * 由教师工号和课程号获取课程
	 * @param term 学期
	 * @param teacherId 教师工号
	 * @param id 课程号
	 * @return 课程PO
	 */
	public CoursePO getCourseFromTeacherIdAndCourseId(String term,
			String teacherId, String id);

	/**
	 * 删除课程
	 * @param term 学期
	 * @param department 院系
	 * @param id 课程号
	 * @return 反馈
	 */
	public Feedback removeCourse(String term, String department, String id);

	/**
	 * 添加课程
	 * @param term 学期
	 * @param Course 课程PO
	 * @return 反馈
	 */
	public Feedback addCourse(String term, CoursePO Course);

	/**
	 * 编辑课程
	 * @param term 学期
	 * @param Course 课程PO
	 * @return 反馈
	 */
	public Feedback updateCourse(String term, CoursePO Course);

	/**
	 * 由教师工号获取课程列表
	 * @param term 学期
	 * @param teacherId 教师工号
	 * @return 课程列表
	 */
	public ArrayList<CoursePO> getCourseListFromTeacherId(String term,
			String teacherId);

	/**
	 * 由年级和院系获取课程列表
	 * @param term 学期
	 * @param grade 年级
	 * @param department 院系
	 * @return 课程列表
	 */
	public ArrayList<CoursePO> getCourseListFromGradeAndDept(String term,
			String grade, String department);

	/**
	 * 由院系获取课程列表
	 * @param term 学期
 	 * @param department 院系
	 * @return 课程列表
	 */
	public ArrayList<CoursePO> getCourseListFromDept(String term,
			String department);

	/**
	 * 获取课程列表表头
	 * @return 表头
	 */
	public String getCourseListHead();
}
