package NJU.HouseWang.nju_eas_server.SystemFactory;

import NJU.HouseWang.nju_eas_server.system.CourseInfoSystem;
import NJU.HouseWang.nju_eas_server.system.CourseSelectionSystem;
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
			case "login_list_head":
			case "teacher_list_head":
			case "student_list_head":
				return (new UserInfoSystem());
			case "log_list":
			case "log_list_head":
				return (new LogSystem());
			case "course":
			case "course_list":
			case "course_list_head":
				return (new CourseInfoSystem());
			case "status_list":
			case "status_list_head":
				return (new CourseSelectionSystem());
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
			case "status":
				return (new CourseSelectionSystem());
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
//		create("login；Student；121250157；bilicrazy123").operate("121250157","login；Student；121250157；bilicrazy123");
		create("show；log_list；null").operate("admin", "show；log_list；null");
		create("show；login_list；null").operate("admin", "show；login_list；null");
		create("show；status_list；null").operate("admin", "show；status_list；null");
		create("add；user；0001；Teacher").operate("admin", "add；user；0001；Teacher");
		create("add；user_list").operate("admin", "add；user_list");
		create("edit；status；selectCourse；false").operate("admin", "edit；status；selectCourse；false");
		create("edit；user；0001；Teacher；123456").operate("admin", "edit；user；0001；Teacher；123456");
		create("del；user；0001").operate("admin", "del；user；0001");
		create("del；user_list").operate("admin", "del；user_list");

	}
}
