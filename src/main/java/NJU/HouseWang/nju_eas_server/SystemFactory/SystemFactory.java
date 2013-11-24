package NJU.HouseWang.nju_eas_server.SystemFactory;

import NJU.HouseWang.nju_eas_server.system.CourseInfoSystem;
import NJU.HouseWang.nju_eas_server.system.LogSystem;
import NJU.HouseWang.nju_eas_server.system.LoginSystem;
import NJU.HouseWang.nju_eas_server.system.UserInfoSystem;
import NJU.HouseWang.nju_eas_server.systemService.SystemService;

public class SystemFactory {

	public static SystemService create(String cmd) {
		String[] cmdtmp = cmd.split("；");
		String action = cmdtmp[0];
		String aim = cmdtmp[1];
		switch (action) {
		case "login":
		case "logout":
			return (new LoginSystem());
		case "show":
			switch(aim){
			case "selfInformation":
			case "login_list":
			case "teacher_list":
			case "student_list":
				return (new UserInfoSystem());
			case "log_list":
				return (new LogSystem());
			case "course":
			case "course_list":
				return (new CourseInfoSystem());
			case "status_list":
				
			}
			break;
		case "add":
			switch(aim){
			case "user":
			case "user_list":
				return (new UserInfoSystem());
			case "course":
			case "course_list":
				return (new CourseInfoSystem());
			}
			break;
		case "edit":
			switch(aim){
			case "user":
			case "selfInformation":
			case "password":
				return (new UserInfoSystem());
			case "course":
			case "course_list":
				return (new CourseInfoSystem());
			case "status_list":
			}
			break;
		case "del":
			switch(aim){
			case "user":
			case "user_list":
				return (new UserInfoSystem());
			case "course":
			case "course_list":
				return (new CourseInfoSystem());
			}
			break;
		case "publish":
		case "select":
		case "byElect":
		case "set":
		case "audit":
		case "":
		}
		return null;
	}
	
	public static void main(String[] args){
		create("login；Student；121250157；bilicrazy123").operate("121250157","login；Student；121250157；bilicrazy123");
	}
}
