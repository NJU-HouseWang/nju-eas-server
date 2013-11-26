package NJU.HouseWang.nju_eas_server.systemService;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.po.User.StudentPO;
import NJU.HouseWang.nju_eas_server.po.User.TeacherPO;
import NJU.HouseWang.nju_eas_server.po.User.UserPO;

public interface UserInfoSystemService extends SystemService {
	// 查看个人信息
	public void showSelfInformation();

	// 修改个人信息
	public void editSelfInformation(UserPO u);

	// 新增用户
	public void addUser(UserPO u);

	// 修改用户
	public void editUser(GuestPO u);
	
	//修改教师
	public void editTeacher(TeacherPO tp);
	
	//修改学生
	public void editStudent(StudentPO sp);

	// 删除用户
	public void delUser(String id);
	
	//新增老师
	public void addTeacher(TeacherPO tp);
	
	//新增学生
	public void addStudent(StudentPO sp);

	// 查看用户列表
	public void showLoginList(String conditions);

	// 查看教师列表
	public void showTeacherList(String conditions);

	// 查看学生列表
	public void showStudentList(String conditions);
	
	//显示用户列表的表头
	public void showLoginListHead();
	
	//显示教师列表的表头
	public void showTeacherListHead();
	
	//显示学生列表的表头
	public void showStudentListHead();
	
	// 批量新增用户
	public void addUserList();

	// 批量删除用户
	public void delUserList();
	
	//修改密码
	public void editPassword(String oldPW, String newPW);
	
	//生成初始密码
	public String generateInitialPassword(UserPO u);
}
