package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.Edu.CourseSelectionPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

/**
 * 选课列表类
 * @author 教化场
 * @version 2013-11-13
 */
public interface CourseSelectionListService {
	/**
	 * 删除列表
	 * @return 反馈
 	 */
	public Feedback delList();

	/**
	 * 判断是否包含选课记录
	 * @param courseId 课程号
	 * @param studentId 学号
	 * @return 是否包含选课记录
	 */
	public boolean containsCourseSelection(String courseId, String studentId);

	/**
	 * 删除选课记录
	 * @param courseId 课程号
	 * @param studentId 学号
	 * @return 反馈
	 */
	public Feedback removeCourseSelection(String courseId, String studentId);

	/**
	 * 添加选课记录
	 * @param CourseSelection 选课记录
	 * @return 反馈
	 */
	public Feedback addCourseSelection(CourseSelectionPO CourseSelection);

	/**
	 * 由课程号获取选课记录
	 * @param courseId 课程号
	 * @return 选课记录列表
	 */
	public ArrayList<CourseSelectionPO> getCourseSelectionListFromCourseId(
			String courseId);

	/**
	 * 由学号获取选课记录列表
	 * @param studentId 学号
	 * @return 选课记录列表
	 */
	public ArrayList<CourseSelectionPO> getCourseSelectionListFromStudentId(
			String studentId);

	/**
	 * 获取选课记录列表
	 * @return 选课记录列表
	 */
	public ArrayList<CourseSelectionPO> getCourseSelectionList();

}