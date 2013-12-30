package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.Course_StudentPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

/**
 * 课程_学生列表类
 * @author 教化场
 * @version 2013-11-12
 */
public interface Course_StudentListService {
	/**
	 * 创建课程列表
	 * @param term 学期
	 * @return 反馈
	 */
	public Feedback createCourseList(String term);

	/**
	 * 放弃课程
	 * @param term 学期
	 * @return 反馈
	 */
	public Feedback dropCourseList(String term);

	/**
	 * 判断是否包含课程_学生PO
	 * @param term 学期
	 * @param courseId 课程号
	 * @param studentId 学号
	 * @return 是否包含课程_学生PO
	 */
	public boolean containsCourse_StudentPO(String term, String courseId,
			String studentId);

	/**
	 * 获取课程_学生PO
	 * @param term 学期
	 * @param courseId 课程号
	 * @param studentId 学号
	 * @return 课程_学生PO
	 */
	public Course_StudentPO getCourse_StudentPO(String term, String courseId,
			String studentId);

	/**
	 * 删除课程_学生PO
	 * @param term 学期
	 * @param courseId 课程号
	 * @param studentId 学号
	 * @return 反馈
	 */
	public Feedback removeCourse_StudentPO(String term, String courseId,
			String studentId);

	/**
	 * 添加课程_学生PO
	 * @param term 学期
	 * @param po 课程_学生PO
	 * @return 反馈
	 */
	public Feedback addCourse_StudentPO(String term, Course_StudentPO po);

	/**
	 * 编辑课程_学生PO
	 * @param term 学期
	 * @param po 课程_学生PO
	 * @return 反馈
	 */
	public Feedback updateCourse_StudentPO(String term, Course_StudentPO po);

	/**
	 * 由课程号获取列表
	 * @param term 学期
	 * @param dept 院系
	 * @param courseId 课程号
	 * @return 课程_学生PO列表
	 */
	public ArrayList<Course_StudentPO> getListFromCourseId(String term,
			String dept, String courseId);

	/**
	 * 由学号获取列表
	 * @param term 学期
	 * @param studentId 学号
	 * @return 课程_学生PO列表
	 */
	public ArrayList<Course_StudentPO> getListFromStudentId(String term,
			String studentId);

	/**
	 * 获取列表表头
	 * @return 表头
	 */
	public String getListHead();
}