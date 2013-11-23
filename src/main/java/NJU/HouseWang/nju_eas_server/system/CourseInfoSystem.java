package NJU.HouseWang.nju_eas_server.system;

import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.dataService.DataService;
import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.DataPOService;
import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemService.CourseInfoSystemService;

public class CourseInfoSystem implements CourseInfoSystemService {
	private NetService ns;
	private DataService ds;

	@Override
	public void operate(String uid, String cmd) {
		// TODO Auto-generated method stub
		String[] cmdInfo = cmd.split("；");
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showCourse":
			this.showCourse(cmdInfo[2]);
			break;
		case "editCourse":
			CoursePO c1 = this.stringToCoursePO(cmd.substring(11,
					cmd.length() - 1));
			this.editCourse(c1);
			break;
		case "addCourse":
			CoursePO c2 = this.stringToCoursePO(cmd.substring(10,
					cmd.length() - 1));
			this.editCourse(c2);
			break;
		case "delCourse":
			this.delCourse(cmdInfo[2]);
			break;
		case "showCourseList":
			this.showCourseList(cmdInfo[2]);
			break;
		case "addCourseList":
			this.addCourseList();
			break;
		case "delCoureList":
			this.delCourseList();
			break;
		default:
			break;
		}
	}

	@Override
	public void showCourse(String id) {
		// TODO Auto-generated method stub
		if (ds.containsID("course_list", id)) {
			CoursePO c = (CoursePO) ds.getData("course_list", id);
			try {
				ns.sendFeedback(c.toCommand());
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
	public void editCourse(CoursePO c) {
		// TODO Auto-generated method stub
		String id = c.getId();
		if (ds.containsID("course_list", id)) {
			ds.updateData("course_list", c);
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
	public void addCourse(CoursePO c) {
		// TODO Auto-generated method stub
		String id = c.getId();
		if (!ds.containsID("course_list", id)) {
			ds.addData("course_list", c);
			try {
				ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		} else {
			try {
				ns.sendFeedback(Feedback.OPERATION_FAIL.toString());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delCourse(String id) {
		// TODO Auto-generated method stub
		if (ds.containsID("course_list", id)) {
			ds.removeData("course_list", id);
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
	public void showCourseList(String conditions) {
		// TODO Auto-generated method stub
		ArrayList<DataPOService> list = ds.getDataList("course_list",
				conditions);
		ArrayList<String> courseList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String courseInfo = ((CoursePO) list.get(i)).toCommand();
			courseList.add(courseInfo);
		}
		try {
			ns.sendList(courseList);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void addCourseList() {
		// TODO Auto-generated method stub
		try {
			ArrayList<String> list = ns.receiveList();
			ArrayList<CoursePO> courseList = new ArrayList<CoursePO>();
			for (int i = 0; i < list.size(); i++) {
				CoursePO course = this.stringToCoursePO(list.get(i));
				this.addCourse(course);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void delCourseList() {
		// TODO Auto-generated method stub
		ArrayList<String> list;
		try {
			list = ns.receiveList();
			for (int i = 0; i < list.size(); i++) {
				this.delCourse(list.get(i));
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	public CoursePO stringToCoursePO(String cmd) {
		String[] cmdInfo = cmd.split("；");
		CoursePO c = new CoursePO(cmdInfo[0], cmdInfo[1]);
		c.setModule(cmdInfo[2]);
		c.setType(cmdInfo[3]);
		c.setNature(cmdInfo[4]);
		c.setCredit(Integer.parseInt(cmdInfo[5]));
		c.setPeriod(Integer.parseInt(cmdInfo[6]));
		c.setDepartment(cmdInfo[7]);
		c.setTeacher(cmdInfo[8]);
		c.setTime(cmdInfo[9]);
		c.setPlace(cmdInfo[10]);
		c.setIntroduction(cmdInfo[11]);
		c.setBook(cmdInfo[12]);
		c.setSyllabus(cmdInfo[13]);
		return c;
	}

	@Override
	public void initNetService(NetService ns) {
		// TODO Auto-generated method stub
		
	}

}
