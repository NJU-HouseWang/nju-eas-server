package NJU.HouseWang.nju_eas_server.dataService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.User.TeacherPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

/**
 * 任课老师列表类
 * @author 教化场
 * @version 2013-11-16
 */
public interface TeacherListService {
	/**
	 * 判断是否存在教师工号
	 * @param id 教师工号
	 * @return 是否存在教师工号
	 */
	public boolean containsID(String id);

	/**
	 * 获取教师PO
	 * @param id 教师工号
	 * @return 教师PO
	 */
	public TeacherPO getTeacher(String id);

	/**
	 * 删除教师
	 * @param id 教师工号
	 * @return 删除教师
	 */
	public Feedback removeTeacher(String id);

	/**
	 * 添加教师
	 * @param teacher 教师PO
	 * @return 反馈
	 */
	public Feedback addTeacher(TeacherPO teacher);

	/**
	 * 编辑教师
	 * @param teacher 教师PO
	 * @return 反馈
	 */
	public Feedback updateTeacher(TeacherPO teacher);

	/**
	 * 获取教师列表
	 * @return 教师列表
	 */
	public ArrayList<TeacherPO> getTeacherList();

	/**
	 * 获取教师列表
	 * @param type 类型
	 * @return 教师列表
	 */
	public ArrayList<TeacherPO> getTeacherList(String type);

	/**
	 * 获取列表表头
	 * @return 表头
	 */
	public String getListHead();
}