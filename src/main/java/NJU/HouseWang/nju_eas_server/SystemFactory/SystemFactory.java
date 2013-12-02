package NJU.HouseWang.nju_eas_server.SystemFactory;

import NJU.HouseWang.nju_eas_server.logic.AnnouncementLogic;
import NJU.HouseWang.nju_eas_server.logic.EduFrameworkSystem;
import NJU.HouseWang.nju_eas_server.logic.LogSystem;
import NJU.HouseWang.nju_eas_server.logic.LoginSystem;
import NJU.HouseWang.nju_eas_server.logic.MessageSystem;
import NJU.HouseWang.nju_eas_server.logic.TeachingPlanSystem;
import NJU.HouseWang.nju_eas_server.logic.UserInfoSystem;
import NJU.HouseWang.nju_eas_server.logicService.LogicService;

public class SystemFactory {

	public static LogicService create(String cmd) {
		String[] cmdtmp = cmd.split("；");
		String action = cmdtmp[0];
		String aim = cmdtmp[1];
		switch (action) {
		case "login":
		case "logout":
			return (new LoginSystem());
		case "show":
			switch (aim) {
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

			case "message":
			case "message_list":
			case "message_list_head":
				return (new MessageSystem());
			case "announcement":
			case "announcement_list":
			case "announcement_list_head":
				return (new AnnouncementLogic());
			case "eduframework":
			case "modulenum":
				return (new EduFrameworkSystem());
			case "teachingplan":
			case "teachingplanlist":
			case "teachingplan_head":
			case "teachingplanlist_head":
				return (new TeachingPlanSystem());
			}
			break;
		case "add":
			switch (aim) {
			case "user":
			case "user_list":
			case "teacher":
			case "student":
				return (new UserInfoSystem());

			case "message":
				return (new MessageSystem());
			case "announcement":
				return (new AnnouncementLogic());
			case "eduframework":
				return (new EduFrameworkSystem());
			case "teachingplan":
				return (new TeachingPlanSystem());
			}
			break;
		case "edit":
			switch (aim) {
			case "user":
			case "selfInformation":
			case "password":
				return (new UserInfoSystem());

			case "message":
				return (new MessageSystem());
			case "announcement":
				return (new AnnouncementLogic());
			case "teachingplan":
				return (new TeachingPlanSystem());
			}
			break;
		case "del":
			switch (aim) {
			case "user":
			case "user_list":
			case "teacher":
			case "teacher_list":
			case "student":
			case "student_list":
				return (new UserInfoSystem());

			case "message":
				return (new MessageSystem());
			case "announcement":
				return (new AnnouncementLogic());
			case "eduframework":
				return (new EduFrameworkSystem());
			case "teachingplan":
				return (new TeachingPlanSystem());
			}
			break;
		case "move":
			switch (aim) {
			case "message":
				return (new MessageSystem());
			}
			break;
		case "publish":
		case "select":
		case "byElect":
		case "set":
		case "audit":
			switch (aim) {
			case "teachingplan":
				return (new TeachingPlanSystem());
			}
			break;
		case "upload":
			switch (aim) {
			case "teachingplanfile":
				return (new TeachingPlanSystem());
			}
			break;
		case "download":
			switch (aim) {
			case "teachingplanfile":
				return (new TeachingPlanSystem());
			}
			break;
		}
		return null;
	}

	/*
	 * public static void main(String[] args){ //
	 * create("login；Student；121250157；bilicrazy123"
	 * ).operate("121250157","login；Student；121250157；bilicrazy123");
	 * create("show；log_list；null").operate("admin", "show；log_list；null");
	 * create("show；login_list；null").operate("admin", "show；login_list；null");
	 * create("show；status_list；null").operate("admin",
	 * "show；status_list；null");
	 * create("add；user；0001；Teacher").operate("admin",
	 * "add；user；0001；Teacher"); create("add；user_list").operate("admin",
	 * "add；user_list");
	 * create("edit；status；selectCourse；false").operate("admin",
	 * "edit；status；selectCourse；false");
	 * create("edit；user；0001；Teacher；123456").operate("admin",
	 * "edit；user；0001；Teacher；123456");
	 * create("del；user；0001").operate("admin", "del；user；0001");
	 * create("del；user_list").operate("admin", "del；user_list");
	 * 
	 * }
	 */
}
