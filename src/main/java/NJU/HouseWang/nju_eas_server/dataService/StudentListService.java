package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.User.StudentPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

/**
 * 学生列表类
 * @author 教化场
 * @version 2013-11-16
 */
public interface StudentListService {
	/**
	 * 判断是否包含学号
	 * @param id 学号
	 * @return 是否包含学号
	 */
	public boolean containsID(String id);

	/**
	 * 获取学生
	 * @param id 学号
	 * @return 学生PO
	 */
	public StudentPO getStudent(String id);

	/**
	 * 删除学生
	 * @param id 学号
	 * @return 反馈
	 */
	public Feedback removeStudent(String id);

	/**
	 * 添加学生
	 * @param user 学生
	 * @return 反馈
	 */
	public Feedback addStudent(StudentPO user);

	/**
	 * 编辑学生
	 * @param user 学生
	 * @return 反馈
	 */
	public Feedback updateStudent(StudentPO user);

	/**
	 * 获取学生列表
	 * @param grade 年级
	 * @param department 院系
	 * @return 学生列表
	 */
	public ArrayList<StudentPO> getStudentList(String grade, String department);

	/**
	 * 获取学生列表
	 * @param department 院系
	 * @return 学生列表
	 */
	public ArrayList<StudentPO> getStudentList(String department);

	/**
	 * 获取学生列表
	 * @return 学生列表
	 */
	public ArrayList<StudentPO> getStudentList();

	/**
	 * 获取年级列表
	 * @return 年级列表
	 */
	public ArrayList<String> getGradeList();

	/**
	 * 获取列表表头
	 * @return 表头
 	 */
	public String getListHead();

}
