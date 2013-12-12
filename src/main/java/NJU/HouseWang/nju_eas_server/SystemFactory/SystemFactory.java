package NJU.HouseWang.nju_eas_server.SystemFactory;

import NJU.HouseWang.nju_eas_server.logic.AnnouncementLogic;
import NJU.HouseWang.nju_eas_server.logic.CourseInfoLogic;
import NJU.HouseWang.nju_eas_server.logic.CourseSelectionLogic;
import NJU.HouseWang.nju_eas_server.logic.EduFrameworkLogic;
import NJU.HouseWang.nju_eas_server.logic.LogLogic;
import NJU.HouseWang.nju_eas_server.logic.LoginLogic;
import NJU.HouseWang.nju_eas_server.logic.MessageLogic;
import NJU.HouseWang.nju_eas_server.logic.SystemInfoLogic;
import NJU.HouseWang.nju_eas_server.logic.TeachingPlanLogic;
import NJU.HouseWang.nju_eas_server.logic.UserInfoLogic;
import NJU.HouseWang.nju_eas_server.logicService.LogicService;

public class SystemFactory {

	public static LogicService create(String cmd) {
		String[] cmdtmp = cmd.split("；");
		String action = cmdtmp[0];
		String aim = null;
		if (cmdtmp.length > 1) {
			aim = cmdtmp[1];
		}
		switch (action) {
		case "login":
		case "logout":
			return (new LoginLogic());
		case "show":
			switch (aim) {
			case "self_information":
			case "login_list":
			case "teacher_list":
			case "student_list":
			case "login_list_head":
			case "teacher_list_head":
			case "student_list_head":
				return (new UserInfoLogic());
			case "log_list":
			case "log_list_head":
				return (new LogLogic());

			case "message":
			case "message_list":
			case "message_list_head":
				return (new MessageLogic());
			case "announcement":
			case "announcement_list":
			case "announcement_list_head":
				return (new AnnouncementLogic());
			case "eduframework":
			case "modulenum":
			case "eduframework_head":
				return (new EduFrameworkLogic());
			case "teachingplan":
			case "teachingplan_list":
			case "teachingplan_head":
			case "teachingplan_list_head":
			case "file_name":
				return (new TeachingPlanLogic());
			case "status":
			case "status_list":
			case "status_list_head":
			case "max_selection_num":
			case "selected_course":
				return (new CourseSelectionLogic());
			case "course_detail":
			case "course_list":
			case "common_course_list":
			case "course_list_head":
			case "common_course_list_head":
			case "student_course_list":
			case "student_score_list":
			case "student_list_from_teacher_and_course":
			case "term":
			case "term_list":
			case "seleced_common_course_list_head":
			case "seleced_common_course_list":
				return (new CourseInfoLogic());
			case "dept_list":
				return (new SystemInfoLogic());
			}
			break;
		case "add":
			switch (aim) {
			case "user":
			case "user_list":
			case "teacher":
			case "student":
				return (new UserInfoLogic());

			case "message":
				return (new MessageLogic());
			case "announcement":
				return (new AnnouncementLogic());
			case "eduframework":
				return (new EduFrameworkLogic());
			case "teachingplan":
				return (new TeachingPlanLogic());
			case "course_student_list":
				return (new CourseSelectionLogic());
			case "course":
			case "course_list":
			case "course_list_from_tp":
			case "common_course":
				return (new CourseInfoLogic());
			}
			break;
		case "edit":
			switch (aim) {
			case "user":
			case "self_information":
			case "password":
				return (new UserInfoLogic());

			case "message":
				return (new MessageLogic());
			case "announcement":
				return (new AnnouncementLogic());
			case "teachingplan":
				return (new TeachingPlanLogic());
			case "status":
				return (new CourseSelectionLogic());
			case "course":
			case "term":
			case "common_course":
				return (new CourseInfoLogic());

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
				return (new UserInfoLogic());

			case "message":
				return (new MessageLogic());
			case "announcement":
				return (new AnnouncementLogic());
			case "eduframework":
				return (new EduFrameworkLogic());
			case "teachingplan":
				return (new TeachingPlanLogic());
			case "course_student_list":
			case "course_student_po":
				return (new CourseSelectionLogic());
			case "course":
			case "common_course":
				return (new CourseInfoLogic());
			}
			break;
		case "move":
			switch (aim) {
			case "message":
				return (new MessageLogic());
			}
			break;
		case "publish":
			switch (aim) {
			case "common_course":
				return (new CourseInfoLogic());
			}
			break;
		case "select":
			switch (aim) {
			case "common_course":
			case "course":
				return (new CourseSelectionLogic());
			}
			break;
		case "byelect":
			switch (aim) {
			case "course":
				return (new CourseSelectionLogic());
			}
			break;
		case "quit":
			switch (aim) {
			case "course":
				return (new CourseSelectionLogic());
			}
			break;
		case "process":
			switch (aim) {
			case "common_course_selection":
				return (new CourseSelectionLogic());
			}
			break;
		case "set":
		case "audit":
			switch (aim) {
			case "teachingplan":
				return (new TeachingPlanLogic());
			}
			break;
		case "upload":
			switch (aim) {
			case "teachingplan_file":
				return (new TeachingPlanLogic());
			}
			break;
		case "download":
			switch (aim) {
			case "teachingplan_file":
				return (new TeachingPlanLogic());
			}
			break;

		case "register":
			switch (aim) {
			case "score":
				return (new CourseInfoLogic());
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
