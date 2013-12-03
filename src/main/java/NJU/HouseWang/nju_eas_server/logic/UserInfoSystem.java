package NJU.HouseWang.nju_eas_server.logic;

import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.LoginList;
import NJU.HouseWang.nju_eas_server.data.StudentList;
import NJU.HouseWang.nju_eas_server.data.TeacherList;
import NJU.HouseWang.nju_eas_server.dataStub.LoginListStub;
import NJU.HouseWang.nju_eas_server.dataStub.StudentListStub;
import NJU.HouseWang.nju_eas_server.dataStub.TeacherListStub;
import NJU.HouseWang.nju_eas_server.logicService.UserInfoLogicService;
import NJU.HouseWang.nju_eas_server.net.ServerStub;
import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.User.GuestPO;
import NJU.HouseWang.nju_eas_server.po.User.StudentPO;
import NJU.HouseWang.nju_eas_server.po.User.TeacherPO;
import NJU.HouseWang.nju_eas_server.po.User.UserPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemMessage.UserType;

public class UserInfoSystem implements UserInfoLogicService {
	private LoginList ll;
	private TeacherList tl;
	private StudentList sl;
	private String uid;
	private GuestPO guest = null;
	private UserPO upo;
	private AuthorityManager am;

	public UserInfoSystem() {
		// TODO Auto-generated constructor stub
		ll = this.initLoginList();
		tl = this.initTeacherList();
		sl = this.initStudentList();
		am = this.initAuthorityManager();

	}
	
	public LoginList initLoginList(){
		LoginList l = new LoginList();
		l.init();
		return l;
	}
	
	public TeacherList initTeacherList(){
		TeacherList t = new TeacherList();
		t.init();
		return t;
	}
	
	public StudentList initStudentList(){
		StudentList s = new StudentList();
		s.init();
		return s;
	}

	public AuthorityManager initAuthorityManager(){
		AuthorityManager a = AuthorityManager.getInstance();
		return a;
	}
	@Override
	public Object operate(String cmd) {
		String[] cmdInfo = cmd.split("；");
		String uid = am.getGuest(cmdInfo[cmdInfo.length-1]);
		this.uid = uid;
		guest = (GuestPO) ll.getLoginer(uid);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showselfInformation":
			return showSelfInformation();
			
		case "editselfInformation":
			String userType = ((GuestPO) ll.getLoginer(uid)).getType()
					.toString();
			if ((userType.equals("Teacher")) || (userType.equals("SchoolDean"))
					|| (userType.equals("DeptAD"))) {
				upo = new TeacherPO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
						cmdInfo[5]);
			} else {
				upo = new StudentPO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
						cmdInfo[5], cmdInfo[6], cmdInfo[7], cmdInfo[8],
						cmdInfo[9]);
			}
			return editSelfInformation(upo);
			
		case "adduser":
			UserPO u = new UserPO(cmdInfo[2], UserType.valueOf(cmdInfo[3]));
			return addUser(u);
			
		case "edituser":
			GuestPO guest = new GuestPO(cmdInfo[2],
					UserType.valueOf(cmdInfo[3]), cmdInfo[4]);
			return editUser(guest);
			
		case "delstudent":
		case "delteacher":
		case "deluser":
			return delUser(cmdInfo[2]);
			
		case "addTeacher":
			TeacherPO tp = new TeacherPO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
					cmdInfo[5]);
			return addTeacher(tp);
			
		case "addStudent":
			StudentPO sp = new StudentPO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
					cmdInfo[5], cmdInfo[6], cmdInfo[7], cmdInfo[8], cmdInfo[9]);
			return addStudent(sp);
			
		case "editTeacher":
			TeacherPO tp2 = new TeacherPO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
					cmdInfo[5]);
			return editTeacher(tp2);
			
		case "editStudent":
			StudentPO sp2 = new StudentPO(cmdInfo[2], cmdInfo[3], cmdInfo[4],
					cmdInfo[5], cmdInfo[6], cmdInfo[7], cmdInfo[8], cmdInfo[9]);
			return editStudent(sp2);
			
		case "showlogin_list":
			return showLoginList(cmdInfo[2]);
			
		case "showteacher_list":
			return showTeacherList(cmdInfo[2]);
			
		case "showstudent_list":
			return showStudentList(cmdInfo[2]);
			
		case "adduser_list":
			return "list";
			
		case "deluser_list":
			return "list";
			
		case "editpassword":
			return editPassword(cmdInfo[2], cmdInfo[3]);
			
		case "showlogin_list_head":
			return this.showLoginListHead();
			
		case "showteacher_list_head":
			return this.showTeacherListHead();
			
		case "showstudent_list_head":
			return this.showStudentListHead();
			
		default:
			return null;
		}
	}
	
	@Override
	public Object operate(String cmd,ArrayList<String> list) {
		String[] cmdInfo = cmd.split("；");
		String uid = am.getGuest(cmdInfo[cmdInfo.length-1]);
		this.uid = uid;
		guest = (GuestPO) ll.getLoginer(uid);
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "adduser_list":
			return addUserList(list);
		case "deluser_list":
			return delUserList(list);
		default:
			return null;
		}
	}

	@Override
	public String showSelfInformation() {
		String userType = guest.getType().toString();
		String feedback = null;
		if ((userType.equals("Teacher")) || (userType.equals("SchoolDean"))
				|| (userType.equals("DeptAD"))) {
			feedback = tl.getTeacher(uid).toCommand();

		} else {
			feedback = sl.getStudent(uid).toCommand();
		}
		return feedback;
	}

	@Override
	public String editSelfInformation(UserPO u) {
		String userType = guest.getType().toString();
		if ((userType.equals("Teacher")) || (userType.equals("SchoolDean"))
				|| (userType.equals("DeptAD"))) {
			tl.updateTeacher((TeacherPO) u);

		} else {
			sl.updateStudent((StudentPO) u);
		}
		return (Feedback.OPERATION_SUCCEED.toString());
	}

	@Override
	public String addUser(UserPO u) {
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
			return(Feedback.OPERATION_SUCCEED.toString());
		} else {
			return(Feedback.DATA_ALREADY_EXISTED.toString());
		}
	}

	@Override
	public String editUser(GuestPO u) {
		String id = u.getId();
		if (ll.containsID(id)) {
			ll.updateLoginer(u);
			return(Feedback.OPERATION_SUCCEED.toString());
		} else {
			return(Feedback.DATA_NOT_FOUND.toString());
		}
	}

	@Override
	public String delUser(String id) {
		if (ll.containsID(id)) {
			ll.removeLoginer(id);
			if (tl.containsID(id)) {
				tl.removeTeacher(id);
			} else {
				sl.removeStudent(id);
			}
			return(Feedback.OPERATION_SUCCEED.toString());
		} else {
			return(Feedback.DATA_NOT_FOUND.toString());
		}

	}

	@Override
	public String addUserList(ArrayList<String> list) {
		for(int i = 0; i < list.size(); i++){
			String[] info = list.get(i).split("；");
			if(ll.containsID(info[0])){
				return Feedback.DATA_ALREADY_EXISTED.toString();
			}
					
		}
		for (int i = 0; i < list.size(); i++) {
			UserPO user = this.stringToUserPO(list.get(i));
			this.addUser(user);
		}
		return Feedback.OPERATION_SUCCEED.toString();
	}

	@Override
	public String delUserList(ArrayList<String> list) {
		for(int i = 0; i < list.size(); i++){
			String[] info = list.get(i).split("；");
			if(!ll.containsID(info[0])){
				return Feedback.DATA_NOT_FOUND.toString();
			}
					
		}
		for (int i = 0; i < list.size(); i++) {
			this.delUser(list.get(i));
		}
		return Feedback.OPERATION_SUCCEED.toString();
	}

	@Override
	public String editPassword(String oldPW, String newPW) {
		// TODO Auto-generated method stub
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
				return(Feedback.PW_TOO_SHORT.toString());
			}
		} else {
			return(Feedback.PW_WRONG_INPUT.toString());
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
	public ArrayList<String> showLoginList(String conditions) {
		// TODO Auto-generated method stub
		ArrayList<GuestPO> list1 = ll.getLoginList(conditions);
		ArrayList<String> guestList = new ArrayList<String>();
		for (int i = 0; i < list1.size(); i++) {
			String guestInfo = (list1.get(i)).toCommand();
			guestList.add(guestInfo);
		}
		return guestList;
	}

	@Override
	public ArrayList<String> showTeacherList(String conditions) {
		// TODO Auto-generated method stub
		ArrayList<TeacherPO> list2 = tl.getTeacherList(conditions);
		ArrayList<String> teacherList = new ArrayList<String>();
		for (int i = 0; i < list2.size(); i++) {
			String teacherInfo = (list2.get(i)).toCommand();
			teacherList.add(teacherInfo);
		}
		return teacherList;
	}

	@Override
	public ArrayList<String> showStudentList(String conditions) {
		// TODO Auto-generated method stub
		ArrayList<StudentPO> list3 = sl.getStudentList(conditions);
		ArrayList<String> studentList = new ArrayList<String>();
		for (int i = 0; i < list3.size(); i++) {
			String studentInfo = (list3.get(i)).toCommand();
			studentList.add(studentInfo);
		}
		return studentList;
	}

	@Override
	public String showLoginListHead() {
		return (ll.getListHead());
	}

	@Override
	public String showTeacherListHead() {
		return (tl.getListHead());
	}

	@Override
	public String showStudentListHead() {
		return(sl.getListHead());
	}

	@Override
	public String addTeacher(TeacherPO tp) {
		// TODO Auto-generated method stub
		String id = tp.getId();
		if(!tl.containsID(id)){
			tl.addTeacher(tp);
			UserType up = tp.getType();
			GuestPO gp = new GuestPO(id,up,this.generateInitialPassword(tp));
			ll.addLoginer(gp);
			return (Feedback.OPERATION_SUCCEED.toString());
			
		} else{
			return (Feedback.ID_ALREADY_EXISTED.toString());
		}
	}

	@Override
	public String addStudent(StudentPO sp) {
		// TODO Auto-generated method stub
		String id = sp.getId();
		if(!sl.containsID(id)){
			sl.addStudent(sp);
			UserType up = sp.getType();
			GuestPO gp = new GuestPO(id,up,this.generateInitialPassword(sp));
			ll.addLoginer(gp);
			return (Feedback.OPERATION_SUCCEED.toString());
			
		} else{
			return (Feedback.ID_ALREADY_EXISTED.toString());
		}
	}

	@Override
	public String editTeacher(TeacherPO tp) {
		// TODO Auto-generated method stub
		String id = tp.getId();
		if (tl.containsID(id)) {
			tl.updateTeacher(tp);
			return (Feedback.OPERATION_SUCCEED.toString());
		} else {
			return(Feedback.DATA_NOT_FOUND.toString());
		}
	}

	@Override
	public String editStudent(StudentPO sp) {
		// TODO Auto-generated method stub
		String id = sp.getId();
		if (sl.containsID(id)) {
			sl.updateStudent(sp);
			return (Feedback.OPERATION_SUCCEED.toString());
		} else {
			return (Feedback.DATA_NOT_FOUND.toString());
		}
	}
}
