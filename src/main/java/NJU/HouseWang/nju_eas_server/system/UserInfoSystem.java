package NJU.HouseWang.nju_eas_server.system;

import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.LoginList;
import NJU.HouseWang.nju_eas_server.data.StudentList;
import NJU.HouseWang.nju_eas_server.data.TeacherList;
import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.po.User.StudentPO;
import NJU.HouseWang.nju_eas_server.po.User.TeacherPO;
import NJU.HouseWang.nju_eas_server.po.User.UserPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;
import NJU.HouseWang.nju_eas_server.systemService.UserInfoSystemService;

public class UserInfoSystem implements UserInfoSystemService {
	private LoginList ll;
	private TeacherList tl;
	private StudentList sl;
	private String uid;
	private GuestPO guest = null;
	private NetService ns;
	private UserPO upo;
	public UserInfoSystem() {
		// TODO Auto-generated constructor stub
		ll = new LoginList();
		tl = new TeacherList();
		sl = new StudentList();
		ll.init();
		tl.init();
		sl.init();
		
	}
	@Override
	public void operate(String uid, String cmd) {
		this.uid = uid;
		guest = (GuestPO) ll.getLoginer(uid);
		String[] cmdpart = cmd.split("；");
		String cmdType = cmdpart[0] + cmdpart[1];
		switch (cmdType) {
		case "showselfInformation":
			showSelfInformation();
			break;
		case "editselfInformation":
			String userType = ((GuestPO) ll.getLoginer(uid)).getType()
					.toString();
			if ((userType.equals("Teacher")) || (userType.equals("SchoolDean"))
					|| (userType.equals("DeptAD"))) {
				upo = new TeacherPO(cmdpart[2], cmdpart[3], cmdpart[4],
						cmdpart[5]);
			} else {
				upo = new StudentPO(cmdpart[2], cmdpart[3], cmdpart[4],
						cmdpart[5], cmdpart[6], cmdpart[7], cmdpart[8],
						cmdpart[9]);
			}
			editSelfInformation(upo);
			break;
		case "adduser":
			UserPO u = new UserPO(cmdpart[2], UserType.valueOf(cmdpart[3]));
			addUser(u);
			break;
		case "edituser":
			GuestPO guest = new GuestPO(cmdpart[2],
					UserType.valueOf(cmdpart[3]), cmdpart[4]);
			editUser(guest);
			break;
		case "deluser":
			delUser(cmdpart[2]);
			break;
		case "showlogin_list":
			showLoginList(cmdpart[2]);
			break;
		case "showteacher_list":
			showTeacherList(cmdpart[2]);
			break;
		case "showstudent_list":
			showStudentList(cmdpart[2]);
			break;
		case "adduser_list":
			addUserList();
			break;
		case "deluser_list":
			delUserList();
			break;
		case "editpassword":
			editPassword(cmdpart[2], cmdpart[3]);
			break;
		default:
			break;

		}
	}

	@Override
	public void showSelfInformation() {
		String userType = guest.getType().toString();
		String feedback = null;
		if ((userType.equals("Teacher")) || (userType.equals("SchoolDean"))
				|| (userType.equals("DeptAD"))) {
			feedback = tl.getTeacher(uid).toCommand();

		} else {
			feedback = sl.getStudent(uid).toCommand();
		}
		try {
			ns.sendFeedback(feedback);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void editSelfInformation(UserPO u) {
		String userType = guest.getType().toString();
		if ((userType.equals("Teacher")) || (userType.equals("SchoolDean"))
				|| (userType.equals("DeptAD"))) {
			tl.updateTeacher((TeacherPO) u);

		} else {
			sl.updateStudent((StudentPO) u);
		}
		try {
			ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void addUser(UserPO u) {
		String id = u.getId();
		UserType ut = u.getType();
		if (!ll.containsID(id)) {
			String pw = this.generateInitialPassword(u);
			GuestPO guest = new GuestPO(id, ut, pw);
			ll.addLoginer(guest);
			if ((ut.equals("Teacher")) || (ut.equals("SchoolDean"))
					|| (ut.equals("DeptAD"))) {
				tl.addTeacher((TeacherPO) u);

			} else {
				sl.addStudent((StudentPO) u);
			}
			try {
				ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			try {
				ns.sendFeedback(Feedback.DATA_ALREADY_EXISTED.toString());
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	@Override
	public void editUser(GuestPO u) {
		String id = u.getId();
		if (ll.containsID(id)) {
			ll.updateLoginer(u);
			try {
				ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			try {
				ns.sendFeedback(Feedback.DATA_NOT_FOUND.toString());
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	@Override
	public void delUser(String id) {
		if (ll.containsID(id)) {
			ll.removeLoginer(id);
			if (tl.containsID(id)) {
				tl.removeTeacher(id);
			} else {
				sl.removeStudent(id);
			}
			try {
				ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			try {
				ns.sendFeedback(Feedback.DATA_NOT_FOUND.toString());
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	
	@Override
	public void addUserList() {
		try {
			ArrayList<String> list = ns.receiveList();
			for (int i = 0; i < list.size(); i++) {
				UserPO user = this.stringToUserPO(list.get(i));
				this.addUser(user);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void delUserList() {
		ArrayList<String> list;
		try {
			list = ns.receiveList();
			for (int i = 0; i < list.size(); i++) {
				this.delUser(list.get(i));
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void initNetService(NetService ns) {
		// TODO Auto-generated method stub
		this.ns = ns;
	}

	@Override
	public void editPassword(String oldPW, String newPW) {
		// TODO Auto-generated method stub
		if (oldPW.equals(guest.getPassword())) {
			if (newPW.length() > 5) {
				if (!newPW.equals(oldPW)) {
					guest.setPassword(newPW);
					ll.updateLoginer(guest);
					try {
						ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
					} catch (IOException e) {

						e.printStackTrace();
					}
				} else {
					try {
						ns.sendFeedback(Feedback.PW_REPEATED.toString());
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			} else {
				try {
					ns.sendFeedback(Feedback.PW_TOO_SHORT.toString());
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		} else {
			try {
				ns.sendFeedback(Feedback.PW_WRONG_INPUT.toString());
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	@Override
	public String generateInitialPassword(UserPO u) {
		// TODO Auto-generated method stub
		return "123456";
	}

	/*
	 * public TeacherPO stringToTeacherPO(String str){ String[] info =
	 * str.split("；"); TeacherPO t = new
	 * TeacherPO(info[0],info[1],info[2],info[3]); return t; }
	 * 
	 * public StudentPO stringToStudentPO(String str){ String[] info =
	 * str.split("；"); StudentPO s = new
	 * StudentPO(info[0],info[1],info[2],info[3
	 * ],info[4],info[5],info[6],info[7]); return s; }
	 * 
	 * public GuestPO stringToGuestPO(String str){ String[] info =
	 * str.split("；"); GuestPO g = new
	 * GuestPO(info[0],UserType.valueOf(info[1]),info[2]); return g; }
	 */

	public UserPO stringToUserPO(String str) {
		String[] info = str.split("；");
		UserPO u = new UserPO(info[0], UserType.valueOf(info[1]));
		return u;
	}

	@Override
	public void showLoginList(String conditions) {
		// TODO Auto-generated method stub
		ArrayList<GuestPO> list1 = ll.getLoginList(conditions);
		ArrayList<String> guestList = new ArrayList<String>();
		for (int i = 0; i < list1.size(); i++) {
			String guestInfo = (list1.get(i)).toCommand();
			guestList.add(guestInfo);
		}
		try {
			ns.sendList(guestList);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void showTeacherList(String conditions) {
		// TODO Auto-generated method stub
		ArrayList<TeacherPO> list2 = tl.getTeacherList(conditions);
		ArrayList<String> teacherList = new ArrayList<String>();
		for (int i = 0; i < list2.size(); i++) {
			String teacherInfo = (list2.get(i)).toCommand();
			teacherList.add(teacherInfo);
		}
		try {
			ns.sendList(teacherList);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void showStudentList(String conditions) {
		// TODO Auto-generated method stub
		ArrayList<StudentPO> list3 = sl.getStudentList(conditions);
		ArrayList<String> studentList = new ArrayList<String>();
		for (int i = 0; i < list3.size(); i++) {
			String studentInfo = (list3.get(i)).toCommand();
			studentList.add(studentInfo);
		}
		try {
			ns.sendList(studentList);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
