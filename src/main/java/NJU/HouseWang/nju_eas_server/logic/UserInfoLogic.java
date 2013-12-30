package NJU.HouseWang.nju_eas_server.logic;

import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.LoginList;
import NJU.HouseWang.nju_eas_server.data.StudentList;
import NJU.HouseWang.nju_eas_server.data.TeacherList;
import NJU.HouseWang.nju_eas_server.logicService.UserInfoLogicService;
import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.po.User.StudentPO;
import NJU.HouseWang.nju_eas_server.po.User.TeacherPO;
import NJU.HouseWang.nju_eas_server.po.User.UserPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;
/**
 * 用户信息逻辑类
 * @author 教化场
 * @version 2013-11-12
 */
public class UserInfoLogic implements UserInfoLogicService {
	private LoginList ll;
	private TeacherList tl;
	private StudentList sl;
	private String uid;
	private GuestPO guest = null;
	private UserPO upo;
	private AuthorityManager am;
	private String dept;

	public UserInfoLogic() {
		ll = this.initLoginList();
		tl = this.initTeacherList();
		sl = this.initStudentList();
		am = this.initAuthorityManager();

	}

	public LoginList initLoginList() {
		LoginList l = new LoginList();
		l.init();
		return l;
	}

	public TeacherList initTeacherList() {
		TeacherList t = new TeacherList();
		t.init();
		return t;
	}

	public StudentList initStudentList() {
		StudentList s = new StudentList();
		s.init();
		return s;
	}

	public AuthorityManager initAuthorityManager() {
		AuthorityManager a = AuthorityManager.getInstance();
		return a;
	}

	public Object operate(String cmd) {
		System.out.println("Process Command: " + cmd);
		String[] cmdInfo = cmd.split("；");
		Object feedback = null;
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showself_information":
			if (!cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[cmdInfo.length - 2]);
				guest = (GuestPO) ll.getLoginer(uid);
				feedback = showSelfInformation();
			}
			break;
		case "editself_information":
			if (!cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[cmdInfo.length - 2]);
				guest = (GuestPO) ll.getLoginer(uid);
				String userType = ((GuestPO) ll.getLoginer(uid)).getType()
						.toString();
				if ((userType.equals("Teacher"))
						|| (userType.equals("SchoolDean"))
						|| (userType.equals("DeptAD"))) {
					upo = new TeacherPO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
							cmdInfo[5]);
				} else {
					upo = new StudentPO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
							cmdInfo[5], cmdInfo[6], cmdInfo[7], cmdInfo[8],
							cmdInfo[9]);
				}
				feedback = editSelfInformation(upo);
			}
			break;
		case "adduser":
			UserPO u = new UserPO(cmdInfo[2], UserType.valueOf(cmdInfo[3]));
			feedback = addUser(u);
			break;
		case "edituser":
			System.out.println(cmdInfo[2] + "  " + cmdInfo[3] + "  "
					+ cmdInfo[4]);
			guest = new GuestPO(cmdInfo[2], UserType.valueOf(cmdInfo[3]),
					cmdInfo[4]);
			feedback = editUser(guest);
			break;
		case "delstudent":
		case "delteacher":
		case "deluser":
			feedback = delUser(cmdInfo[2]);
			break;
		case "addteacher":
			TeacherPO tp = new TeacherPO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
					cmdInfo[5]);
			feedback = addTeacher(tp);
			break;
		case "addstudent":
			StudentPO sp = new StudentPO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
					cmdInfo[5], cmdInfo[6], cmdInfo[7], cmdInfo[8], cmdInfo[9]);
			feedback = addStudent(sp);
			break;
		case "editteacher":
			TeacherPO tp2 = new TeacherPO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
					cmdInfo[5]);
			feedback = editTeacher(tp2);
			break;
		case "editstudent":
			StudentPO sp2 = new StudentPO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
					cmdInfo[5], cmdInfo[6], cmdInfo[7], cmdInfo[8], cmdInfo[9]);
			feedback = editStudent(sp2);
			break;
		case "showlogin_list":
			feedback = showLoginList();
			break;
		case "showteacher_list":
			feedback = showTeacherList(cmdInfo[2]);
			break;
		case "showstudent_list":
			feedback = showStudentList(cmdInfo[2]);
			break;
		case "adduser_list":
			feedback = "list";
			break;
		case "deluser_list":
			feedback = "list";
			break;
		case "editpassword":
			if (!cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				uid = am.getGuest(cmdInfo[cmdInfo.length - 2]);
				guest = (GuestPO) ll.getLoginer(uid);
				feedback = editPassword(cmdInfo[2], cmdInfo[3]);
			}
			break;
		case "showlogin_list_head":
			feedback = this.showLoginListHead();
			break;
		case "showteacher_list_head":
			feedback = this.showTeacherListHead();
			break;
		case "showstudent_list_head":
			feedback = this.showStudentListHead();
			break;
		case "showgrade_list":
			feedback = this.showGradeList();
			break;
		case "showself_dept":
			if (!cmd.endsWith("；ok")) {
				feedback = "ip";
			} else {
				feedback = this.showSelfDept(cmdInfo[cmdInfo.length - 2]);
			}
			break;
		default:
			break;
		case "showteacher_name":
			feedback = this.showTeacherName(cmdInfo[2]);
			break;
		case "showstudent_name":
			feedback = this.showStudentName(cmdInfo[2]);
			break;
		case "showuser_name":
			feedback = this.showUserName(cmdInfo[2]);
			break;
		}
		ll.finish();
		tl.finish();
		sl.finish();
		return feedback;
	}

	@Override
	public Object operate(String cmd, ArrayList<String> list) {
		ll = this.initLoginList();
		tl = this.initTeacherList();
		sl = this.initStudentList();
		String[] cmdInfo = cmd.split("；");
		Object feedback = null;
		String uid = am.getGuest(cmdInfo[cmdInfo.length - 1]);
		this.uid = uid;
		guest = (GuestPO) ll.getLoginer(uid);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "adduser_list":
			feedback = addUserList(list);
			break;
		case "deluser_list":
			feedback = delUserList(list);
			break;
		default:
			break;
		}
		ll.finish();
		tl.finish();
		sl.finish();
		return feedback;
	}

	@Override
	/**
	 * 查看个人信息
	 * @return 反馈
	 */
	public String showSelfInformation() {
		try {
			String userType = guest.getType().toString();
			String feedback = null;
			if ((userType.equals("Teacher")) || (userType.equals("SchoolDean"))
					|| (userType.equals("DeptAD"))) {
				feedback = tl.getTeacher(uid).toCommand();

			} else if (userType.equals("Student")) {
				feedback = sl.getStudent(uid).toCommand();
			} else {
				feedback = "admin；管理员；Admin；System";
			}
			return feedback;
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 修改个人信息
	 * @param u 用户PO
	 * @return 反馈
	 */
	public String editSelfInformation(UserPO u) {
		try {
			String userType = guest.getType().toString();
			if ((userType.equals("Teacher")) || (userType.equals("SchoolDean"))
					|| (userType.equals("DeptAD"))) {
				tl.updateTeacher((TeacherPO) u);

			} else {
				sl.updateStudent((StudentPO) u);
			}
			return (Feedback.OPERATION_SUCCEED.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 新增用户
	 * @param u 用户PO
	 * @return 反馈
	 */
	public String addUser(UserPO u) {
		try {
			String id = u.getId();
			UserType ut = u.getType();
			if (!ll.containsID(id)) {
				String pw = this.generateInitialPassword(u);
				GuestPO guest = new GuestPO(id, ut, pw);
				ll.addLoginer(guest);
				if ((ut.equals("Teacher")) || (ut.equals("SchoolDean"))
						|| (ut.equals("DeptAD"))) {
					TeacherPO tp = new TeacherPO();
					tp.setId(id);
					tp.setType(ut);
					tl.addTeacher(tp);

				} else {
					StudentPO sp = new StudentPO();
					sp.setId(id);
					sl.addStudent((StudentPO) u);
				}
				return (Feedback.OPERATION_SUCCEED.toString());
			} else {
				return (Feedback.DATA_ALREADY_EXISTED.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 修改用户
	 * @param u 客户PO
	 * @return 反馈
	 */
	public String editUser(GuestPO u) {
		try {
			String id = u.getId();
			if (ll.containsID(id)) {
				ll.updateLoginer(u);
				return (Feedback.OPERATION_SUCCEED.toString());
			} else {
				return (Feedback.DATA_NOT_FOUND.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 删除用户
	 * @param id 用户名
	 * @return 反馈
	 */
	public String delUser(String id) {
		try {
			if (ll.containsID(id)) {
				if (tl.containsID(id)) {
					tl.removeTeacher(id);
					ll.removeLoginer(id);
				} else {
					sl.removeStudent(id);
					ll.removeLoginer(id);
				}
				return (Feedback.OPERATION_SUCCEED.toString());
			} else {
				return (Feedback.DATA_NOT_FOUND.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 批量新增用户
	 * @param list 列表名
	 * @return 反馈
	 */
	public String addUserList(ArrayList<String> list) {
		try {
			for (int i = 0; i < list.size(); i++) {
				String[] info = list.get(i).split("；");
				if (ll.containsID(info[0])) {
					return Feedback.DATA_ALREADY_EXISTED.toString();
				}

			}
			for (int i = 0; i < list.size(); i++) {
				UserPO user = this.stringToUserPO(list.get(i));
				this.addUser(user);
			}
			return Feedback.OPERATION_SUCCEED.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 批量删除用户
	 * @param list 列表名
	 * @return 反馈
	 */
	public String delUserList(ArrayList<String> list) {
		try {
			for (int i = 0; i < list.size(); i++) {
				String[] info = list.get(i).split("；");
				if (!ll.containsID(info[0])) {
					return Feedback.DATA_NOT_FOUND.toString();
				}

			}
			for (int i = 0; i < list.size(); i++) {
				this.delUser(list.get(i));
			}
			return Feedback.OPERATION_SUCCEED.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 修改密码
	 * @param oldPW 旧密码
	 * @param newPW 新密码
	 * @return 反馈
	 */
	public String editPassword(String oldPW, String newPW) {
		try {
			if (oldPW.equals(guest.getPassword())) {
				if (newPW.length() > 5) {
					if (!newPW.equals(oldPW)) {
						guest.setPassword(newPW);
						ll.updateLoginer(guest);
						return (Feedback.OPERATION_SUCCEED.toString());
					} else {
						return (Feedback.PW_REPEATED.toString());
					}
				} else {
					return (Feedback.PW_TOO_SHORT.toString());
				}
			} else {
				return (Feedback.PW_WRONG_INPUT.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 生成初始密码
	 * @param u 用户PO
 	 * @return 反馈
	 */
	public String generateInitialPassword(UserPO u) {

		return "123456";
	}
	/**
	 * string转换用户
	 * @param str
	 * @return 用户
	 */
	public UserPO stringToUserPO(String str) {
		String[] info = str.split("；");
		UserPO u = new UserPO(info[0], UserType.valueOf(info[1]));
		return u;
	}

	@Override
	/**
	 * 查看用户列表
	 * @return 登录列表
	 */
	public ArrayList<String> showLoginList() {

		ArrayList<GuestPO> list1 = ll.getLoginList();
		ArrayList<String> guestList = new ArrayList<String>();
		for (int i = 0; i < list1.size(); i++) {
			String guestInfo = (list1.get(i)).toCommand();
			guestList.add(guestInfo);
		}
		return guestList;
	}

	@Override
	/**
	 * 查看教师列表
	 * @param condition 条件
	 * @return 教师列表
	 */
	public ArrayList<String> showTeacherList(String condition) {

		ArrayList<TeacherPO> list2 = new ArrayList<TeacherPO>();
		if (condition.equals("all")) {
			list2 = tl.getTeacherList();
		} else {
			list2 = tl.getTeacherList(condition);
		}
		ArrayList<String> teacherList = new ArrayList<String>();
		for (int i = 0; i < list2.size(); i++) {
			String teacherInfo = (list2.get(i)).toCommand();
			teacherList.add(teacherInfo);
		}
		return teacherList;
	}

	@Override
	/**
	 * 查看学生列表
	 * @param condition 条件
	 * @return 学生列表
	 */
	public ArrayList<String> showStudentList(String condition) {

		ArrayList<StudentPO> list3 = new ArrayList<StudentPO>();
		if (condition.equals("all")) {
			list3 = sl.getStudentList();
		} else {
			String[] info = condition.split("，");
			if (info[0].equals("all")) {
				list3 = sl.getStudentList(info[1]);
			} else {
				list3 = sl.getStudentList(info[0], info[1]);
			}
		}
		ArrayList<String> studentList = new ArrayList<String>();
		for (int i = 0; i < list3.size(); i++) {
			String studentInfo = (list3.get(i)).toCommand();
			studentList.add(studentInfo);
		}
		return studentList;
	}

	@Override
	/**
	 * 显示用户列表的表头
	 * @return 表头
	 */
	public String showLoginListHead() {
		try {
			return (ll.getListHead());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 显示教师列表的表头
	 * @return 表头
 	 */
	public String showTeacherListHead() {
		try {
			return (tl.getListHead());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 显示学生列表的表头
	 * @return 表头
	 */
	public String showStudentListHead() {
		try {
			return (sl.getListHead());
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 新增老师
	 * @param tp 教师PO
	 * @return 反馈
	 */
	public String addTeacher(TeacherPO tp) {
		try {
			String id = tp.getId();
			if (!tl.containsID(id)) {
				tl.addTeacher(tp);
				UserType up = tp.getType();
				GuestPO gp = new GuestPO(id, up,
						this.generateInitialPassword(tp));
				ll.addLoginer(gp);
				return (Feedback.OPERATION_SUCCEED.toString());

			} else {
				return (Feedback.ID_ALREADY_EXISTED.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 新增学生
	 * @param sp 学生PO
	 * @return 反馈
	 */
	public String addStudent(StudentPO sp) {
		try {
			String id = sp.getId();
			if (!sl.containsID(id)) {
				sl.addStudent(sp);
				UserType up = sp.getType();
				GuestPO gp = new GuestPO(id, up,
						this.generateInitialPassword(sp));
				ll.addLoginer(gp);
				return (Feedback.OPERATION_SUCCEED.toString());

			} else {
				return (Feedback.ID_ALREADY_EXISTED.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 修改教师
	 * @param tp 任课教师PO
	 * @return 反馈
	 */
	public String editTeacher(TeacherPO tp) {
		try {
			String id = tp.getId();
			if (tl.containsID(id)) {
				tl.updateTeacher(tp);
				return (Feedback.OPERATION_SUCCEED.toString());
			} else {
				return (Feedback.DATA_NOT_FOUND.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 修改学生
	 * @param sp 学生PO
	 * @return 反馈
	 */
	public String editStudent(StudentPO sp) {
		try {
			String id = sp.getId();
			if (sl.containsID(id)) {
				sl.updateStudent(sp);
				return (Feedback.OPERATION_SUCCEED.toString());
			} else {
				return (Feedback.DATA_NOT_FOUND.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 返回所有的年级的列表
	 * @return 年级列表
	 */
	public ArrayList<String> showGradeList() {

		ArrayList<String> allGradeList = sl.getGradeList();
		ArrayList<String> feedback = new ArrayList<String>();
		String grade = "";
		if (!allGradeList.isEmpty()) {
			feedback.add(allGradeList.get(0));
			for (int i = 1; i < allGradeList.size(); i++) {
				grade = allGradeList.get(i);
				if (!feedback.contains(grade)) {
					feedback.add(grade);
				}
			}
		}
		return feedback;
	}

	@Override
	/**
	 * 返回Ip对应的院系
	 * @param ip ip地址
	 * @return 院系
	 */
	public String showSelfDept(String ip) {
		try {
			uid = am.getGuest(ip);
			if (tl.containsID(uid)) {
				dept = tl.getTeacher(uid).getCompany();
			} else if (sl.containsID(uid)) {
				dept = sl.getStudent(uid).getDepartment();
			}
			return dept;
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 返回id对应的教师名称,若无则返回null
	 * @param id 教师工号
	 * @return 教师名称
	 */
	public String showTeacherName(String id) {
		try {
			if (tl.containsID(id)) {
				return (tl.getTeacher(id).getName());
			} else {
				return "null";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 返回id对应的学生名称,若无则返回null
	 * @param id 学号
	 * @return 学生名称
	 */
	public String showStudentName(String id) {
		try {
			if (sl.containsID(id)) {
				return (sl.getStudent(id).getName());
			} else {
				return "null";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}

	@Override
	/**
	 * 返回id对应的用户姓名，若无则返回null
	 * @param id 用户名
	 * @return 用户名称
	 */
	public String showUserName(String id) {
		try {
			if (tl.containsID(id)) {
				return showTeacherName(id);
			} else if (sl.containsID(id)) {
				return showStudentName(id);
			} else if (id.equals("admin")) {
				return "系统管理员";
			} else {
				return "null";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Feedback.OPERATION_FAIL.toString();
		}
	}
}
