package NJU.HouseWang.nju_eas_server.logicTest;

import java.util.ArrayList;

import junit.framework.TestCase;
import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.data.CommonCourseList;
import NJU.HouseWang.nju_eas_server.dataStub.CommonCourseListStub;
import NJU.HouseWang.nju_eas_server.dataStub.CourseListStub;
import NJU.HouseWang.nju_eas_server.dataStub.CourseSelectorNumListStub;
import NJU.HouseWang.nju_eas_server.dataStub.Course_StudentListStub;
import NJU.HouseWang.nju_eas_server.dataStub.StatusListStub;
import NJU.HouseWang.nju_eas_server.dataStub.StudentListStub;
import NJU.HouseWang.nju_eas_server.dataStub.TeacherListStub;
import NJU.HouseWang.nju_eas_server.dataStub.TeachingPlanListStub;
import NJU.HouseWang.nju_eas_server.dataStub.TeachingPlanStub;
import NJU.HouseWang.nju_eas_server.dataStub.TermListStub;
import NJU.HouseWang.nju_eas_server.logic.CourseInfoLogic;
import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.po.Edu.TeachingPlanItemPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class CourseInfoLogicTest extends TestCase {
	private CourseListStub cl;
	private AuthorityManager am;
	private TeachingPlanListStub tpl;
	private TeachingPlanStub tp;
	private CourseSelectorNumListStub csnl;
	private Course_StudentListStub csl;
	private StudentListStub sl;
	private TeacherListStub tl;
	private StatusListStub statusList;
	private TermListStub termList;
	private CommonCourseListStub ccl;
	private String course;

	public CourseInfoLogic cil;

	public void setUp() throws Exception {
		am = AuthorityManager.getInstance();
		cl = new CourseListStub();
		tpl = new TeachingPlanListStub();
		tp = new TeachingPlanStub();
		csnl = new CourseSelectorNumListStub();
		csl = new Course_StudentListStub();
		sl = new StudentListStub();
		tl = new TeacherListStub();
		statusList = new StatusListStub();
		termList = new TermListStub();
		ccl = new CommonCourseListStub();

		cil = new CourseInfoLogic() {
			@Override
			public AuthorityManager initAuthorityManager() {
				return am;
			}

			@Override
			public CourseListStub initCourseList() {
				return cl;
			}

			@Override
			public TeachingPlanListStub initTeachingPlanList() {
				return tpl;
			}

			@Override
			public TeachingPlanStub initTeachingPlan() {
				return tp;
			}

			@Override
			public CourseSelectorNumListStub initCourseSelectorNumList() {
				return csnl;
			}

			@Override
			public Course_StudentListStub initCourse_StudentList() {
				return csl;
			}

			@Override
			public StudentListStub initStudentList() {
				return sl;
			}

			@Override
			public TeacherListStub initTeacherList() {
				return tl;
			}

			@Override
			public StatusListStub initStatusList() {
				return statusList;
			}

			@Override
			public TermListStub initTermList() {
				return termList;
			}
			
			@Override
			public CommonCourseListStub initCommonCourseList() {
				return ccl;
			}
		};
	}

	protected void tearDown() throws Exception {
	}

	public void testShowCourseDetail() {
		ArrayList<String> result = new ArrayList<String>();
		result = cil.showCourseDetail("wang学yang", "department", "id");
		assertTrue(result.get(0).equals("null"));
	}

	public void testEditCourse() {
		CoursePO c = new CoursePO("id", "name", "module", "type", "nature", 1,
				2, "grade", "term", "department", 3, "teacherId",
				"teacherName", "timeAndPlace", "place");
		ArrayList<String> content = new ArrayList<String>();
		content.add("content1");
		String result = cil.editCourse("wang学yang", "courseId", "dept", content);
		assertTrue(result.equals(Feedback.OPERATION_FAIL.toString()));
	}

	public void testAddCourse() {
		CoursePO c = new CoursePO();
		String result = cil.addCourse(c);
		assertTrue(result.equals(Feedback.DATA_ALREADY_EXISTED.toString()));
	}

	public void testDelCourse() {
		String result = cil.delCourse("term学term", "department", "id");
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}

	public void testShowCourseList() {
		ArrayList<String> result = cil.showCourseList("term学term",
				"condition，condition");
		assertTrue(result.get(0).equals(
				"null；null；null；0；0；null；null；null；null；null"));
	}

	public void testShowCommonCourseList() {
		ArrayList<String> result = cil.showCommonCourseList();
		assertTrue(result.get(0).equals("null；null；0；0；null；null；null；0人"));
	}

	public void testAddCourseList() {
		ArrayList<String> list = new ArrayList<String>();
		String result = cil.addCourseList(list);
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}

	public void testAddCourseListFromTP() {
		String result = cil.addCourseListFromTP("deptName");
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}

	public void testShowCourseListHead() {
		String result = cil.showCourseListHead();
		assertTrue(result.equals("CourseListHead"));
	}

	public void testShowCommonCourseListHead() {
		String result = cil.showCommonCourseListHead();
		assertTrue(result.equals("ListHead"));
	}

	public void testRecordScore() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("software；157；90；69；82");
		String result = cil.recordScore("2013-2014学年 第1学期", list);
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}

	public void testShowStudentCourseList() {
		ArrayList<String> result = cil.showStudentCourseList("studentId");
		assertTrue(result.get(0).equals("007；name；nature；1；2；null；x2013_1；通识课；teacherName；timeAndPlace"));
	}

	public void testShowStudentScoreList() {
		ArrayList<String> result = cil.showStudentScoreList("term学term",
				"studentId");
		assertTrue(result.get(0).equals("dept；007；157；-1；-1"));
	}

	
	public void testShowCourseStudentList() {
		ArrayList<String> result = cil.showCourseStudentList("courseId", "department");
		assertTrue(result.get(0).equals("dept；008；158；-1；-1"));
	}

	public void testShowTerm() {
		String result = cil.showTerm();
		assertTrue(result.equals("2013-2014学年 第1学期"));
	}

	public void testEditTerm() {
		String result = cil.editTerm("2013-2014学年 第1学期");
		assertTrue(result.equals(Feedback.OPERATION_SUCCEED.toString()));
	}

	public void testPublishCommonCourse() {
		ArrayList<String> list = new ArrayList<String>();
		String str = "id；name；module；type；nature；1；2；grade；term；department；3；teacherId；teacherName；timeAndPlace；place";
		list.add(str);
		String result = cil.publishCommonCourse(list);
		assertTrue(result.equals(Feedback.OPERATION_FAIL.toString()));
	}

	public void testStringToCoursePO() {
		CoursePO c = cil
				.stringToCoursePO("id；name；module；type；nature；1；2；grade；term；department；3；teacherId；teacherName；timeAndPlace；place");
		assertTrue(c
				.courseToCommand()
				.equals("id；name；nature；1；2；null；term；department；teacherName；timeAndPlace"));
	}

	public void testTpPOToCoursePO() {
		TeachingPlanItemPO tpip = new TeachingPlanItemPO("moduleId",
				"moduleName", 1, "courseNature", "courseType", 2, "courseId",
				"courseName", 3, "4", 5);
		CoursePO result = cil.tpPOToCoursePO("dept", tpip);
		assertTrue(result.courseToCommand().equals("courseId；courseName；courseNature；3；4；2011；2013-2014学年 第1学期；dept；null；null"));
	}

	public void testShowTermList() {
		ArrayList<String> result = cil.showTermList();
		assertTrue(result.get(0).equals("TermList"));
	}

	public void testTermTransfer() {
		String str = "wang学yang";
		String result = cil.termTransfer(str);
		assertTrue(result.equals("xwang_g"));
	}
}
