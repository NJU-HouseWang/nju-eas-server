package NJU.HouseWang.nju_eas_server.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import NJU.HouseWang.nju_eas_server.data.CourseList;
import NJU.HouseWang.nju_eas_server.data.CourseSelectionList;
import NJU.HouseWang.nju_eas_server.data.CourseSelectorNumList;
import NJU.HouseWang.nju_eas_server.data.Course_StudentList;
import NJU.HouseWang.nju_eas_server.data.StatusList;
import NJU.HouseWang.nju_eas_server.dataStub.StatusListStub;
import NJU.HouseWang.nju_eas_server.net.ServerStub;
import NJU.HouseWang.nju_eas_server.netService.NetService;
import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.po.Edu.CourseSelectionPO;
import NJU.HouseWang.nju_eas_server.po.Edu.CourseSelectorNumPO;
import NJU.HouseWang.nju_eas_server.po.Edu.Course_StudentPO;
import NJU.HouseWang.nju_eas_server.po.Edu.StatusPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;
import NJU.HouseWang.nju_eas_server.systemService.CourseSelectionSystemService;

public class CourseSelectionSystem implements CourseSelectionSystemService {
	private NetService ns;
	private StatusList sl;
	private Course_StudentList c_sl;
	private CourseSelectionList csl;
	private CourseSelectorNumList csnl;
	private CourseList cl;
	private static final int MAXSELECTIONNUM = 4; // 最大的选课数量

	public CourseSelectionSystem() {
		// TODO Auto-generated constructor stub
		sl = new StatusList();
		c_sl = new Course_StudentList();
		csl = new CourseSelectionList();
		csnl = new CourseSelectorNumList();
		cl = new CourseList();
		sl.init();
		c_sl.init();
		csl.init();
		csnl.init();
		sl.init();

	}

	@Override
	public void operate(String uid, String cmd) {
		String[] cmdInfo = cmd.split("；");
		String cmdType = cmdInfo[0] + cmdInfo[1];
		switch (cmdType) {
		case "showstatus":
			this.showStatus(cmdInfo[2]);
			break;
		case "editstatus":
			StatusPO sp = new StatusPO();
			sp.setFunction(cmdInfo[2]);
			sp.setIsopen(cmdInfo[3]);
			this.editStatus(sp);
			break;
		case "showstatus_list":
			this.showStatusList();
			break;
		case "showstatus_list_head":
			this.showStatusListHead();
			break;
		default:
			break;
		}
	}

	@Override
	public void initNetService(NetService ns) {
		// TODO Auto-generated method stub
		this.ns = ns;
	}

	@Override
	public void showStatus(String function) {
		// TODO Auto-generated method stub
		StatusPO sp = sl.getStatus(function);
		try {
			ns.sendFeedback(sp.getIsopenToString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void editStatus(StatusPO sp) {
		// TODO Auto-generated method stub
		sl.updateStatus(sp);
		try {
			ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void showStatusList() {
		// TODO Auto-generated method stub
		ArrayList<StatusPO> list = sl.getStatusList();
		ArrayList<String> statusList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String statusInfo = (list.get(i)).toCommand();
			statusList.add(statusInfo);
		}
		try {
			ns.sendList(statusList);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void showStatusListHead() {
		// TODO Auto-generated method stub
		try {
			ns.sendFeedback(sl.getListHead());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void selectCommonCourse(String courseId, String studentId) {
		// TODO Auto-generated method stub
		if (!isMax(studentId)) {
			if (!csl.containsCourseSelection(courseId, studentId)) {

				csl.addCourseSelection(new CourseSelectionPO(courseId,
						studentId, getPriority(studentId)));
				try {
					ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					ns.sendFeedback(Feedback.SELECTION_REPEATED.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			try {
				ns.sendFeedback(Feedback.MAX_SELECTION.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void selectCourse(String listName, String courseId, String studentId) {
		// TODO Auto-generated method stub
		if (!c_sl.containsCourse_StudentPO(listName, courseId, studentId)) {

			c_sl.addCourse_StudentPO(listName, new Course_StudentPO(listName,
					courseId, studentId));
			try {
				ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				ns.sendFeedback(Feedback.SELECTION_REPEATED.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void byElectCourse(String listName, String courseId, String studentId) {
		// TODO Auto-generated method stub
		CourseSelectorNumPO csnp = csnl.getCourseSelectorNumPO(courseId);
		if (csnp.getSelectorNum() < csnp.getTotalNum()) {
			csnp.setSelectorNum(csnp.getSelectorNum() + 1);
			c_sl.addCourse_StudentPO(listName, new Course_StudentPO("通识课",
					courseId, studentId));
			csnl.updateCourseSelectorNumPO(csnp);
			try {
				ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				ns.sendFeedback(Feedback.MAX_SELECTION.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void quitCourse(String listName, String courseId, String studentId) {
		// TODO Auto-generated method stub
		if (c_sl.containsCourse_StudentPO(listName, courseId, studentId)) {
			c_sl.removeCourse_StudentPO(listName, courseId, studentId);
			CourseSelectorNumPO csnp = csnl.getCourseSelectorNumPO(courseId);
			csnp.setSelectorNum(csnp.getSelectorNum() - 1);
			csnl.updateCourseSelectorNumPO(csnp);
			try {
				ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				ns.sendFeedback(Feedback.OPERATION_FAIL.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addCourse_StudentList(String listName) {
		// TODO Auto-generated method stub
		ArrayList<String> list;
		try {
			list = ns.receiveList();
			for (int i = 0; i < list.size(); i++) {
				String[] info = list.get(i).split("；");
				this.selectCourse(listName, info[0], info[1]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delCourse_StudentList(String listName) {
		// TODO Auto-generated method stub
		ArrayList<String> list;
		try {
			list = ns.receiveList();
			for (int i = 0; i < list.size(); i++) {
				String[] info = list.get(i).split("；");
				this.delCourse_StudentPO(listName, info[0], info[1]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void processCommonCourseSelection(String listName) {
		// TODO Auto-generated method stub
		ArrayList<CourseSelectionPO> courseSelectionList = csl
				.getCourseSelectionList();
		ArrayList<String> courseList = new ArrayList<String>();
		courseList.add(courseSelectionList.get(0).getCourseId());
		for (int i = 0; i < courseSelectionList.size(); i++) {
			if (!courseList.contains(courseSelectionList.get(i).getCourseId())) {
				courseList.add(courseSelectionList.get(i).getCourseId());
			}
		}

		for (int j = 0; j < courseList.size(); j++) {
			ArrayList<CourseSelectionPO> csl1 = csl
					.getCourseSelectionListFromCourseId(courseList.get(j));
			String courseListName = listName.substring(0,6)+"course_list";
			int num = cl.getCourse(courseListName, "通识课",courseList.get(j) ).getStudentNum();
			if(csl1.size()>num){
				csl1 = lot(csl1,num);
			} 
			String course_StudentListName = listName.substring(0,6)+"course_student_list";
			for(int m = 0; m < csl1.size();m++){
				Course_StudentPO p = new Course_StudentPO("通识课",csl1.get(m).getCourseId(),csl1.get(m).getStudentId());
				c_sl.addCourse_StudentPO(course_StudentListName, p);
				CourseSelectorNumPO cp = csnl.getCourseSelectorNumPO(csl1.get(m).getCourseId());
				//未完成
				
			}

		}
	}
	
	//抽签逻辑
	public ArrayList<CourseSelectionPO> lot(ArrayList<CourseSelectionPO> list, int totalNum){
		int selectorNum = list.size();
		//未完成
		return list;
	}

	@Override
	public void showMaxSelectionNum() {
		// TODO Auto-generated method stub
		try {
			ns.sendFeedback("" + MAXSELECTIONNUM);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean isMax(String studentId) {
		// TODO Auto-generated method stub
		boolean isMax = false;
		ArrayList<CourseSelectionPO> list = csl
				.getCourseSelectionListFromStudentId(studentId);
		if (list.size() >= MAXSELECTIONNUM) {
			isMax = true;
		}
		return isMax;
	}

	@Override
	public void showSelectedCouse(String listName, String studentId) {
		// TODO Auto-generated method stub
		ArrayList<CourseSelectionPO> list = csl
				.getCourseSelectionListFromStudentId(studentId);
		ArrayList<String> courseList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			courseList.add(cl.getCourse(listName, "通识课",
					list.get(i).getCourseId()).toString());
		}
		try {
			ns.sendList(courseList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getPriority(String studentId) {
		int priority = Calendar.getInstance().get(Calendar.YEAR) - 2000
				- Integer.parseInt(studentId.substring(0, 1));
		return priority;
	}

	@Override
	public void delCourse_StudentPO(String listName, String courseId,
			String studentId) {
		// TODO Auto-generated method stub
		if (c_sl.containsCourse_StudentPO(listName, courseId, studentId)) {
			c_sl.removeCourse_StudentPO(listName, courseId, studentId);
			try {
				ns.sendFeedback(Feedback.OPERATION_SUCCEED.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				ns.sendFeedback(Feedback.DATA_NOT_FOUND.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
