package NJU.HouseWang.nju_eas_server.logicTest;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.dataStub.CourseListStub;
import NJU.HouseWang.nju_eas_server.dataStub.CourseSelectionListStub;
import NJU.HouseWang.nju_eas_server.dataStub.CourseSelectorNumListStub;
import NJU.HouseWang.nju_eas_server.dataStub.Course_StudentListStub;
import NJU.HouseWang.nju_eas_server.dataStub.StatusListStub;
import NJU.HouseWang.nju_eas_server.logic.CourseInfoLogic;
import NJU.HouseWang.nju_eas_server.logic.CourseSelectionLogic;
import junit.framework.TestCase;

public class CourseSelectionLogicTest extends TestCase{
	private AuthorityManager am;
	private StatusListStub sl;
	private Course_StudentListStub c_sl;
	private CourseSelectionListStub csl;
	private CourseSelectorNumListStub csnl;
	private CourseListStub cl;
	private CourseInfoLogic courseInfoSystem = new CourseInfoLogic();
	private static final int MAXSELECTIONNUM = 4; // 最大的选课数量
	
	public CourseSelectionLogic cstl;
	
	public void setUp() throws Exception {
		am = AuthorityManager.getInstance();
		sl = new StatusListStub();
		c_sl = new Course_StudentListStub();
		csl = new CourseSelectionListStub();
		csnl = new CourseSelectorNumListStub();
		cl = new CourseListStub();
		
		cstl = new CourseSelectionLogic(){
			@Override
			public AuthorityManager initAuthorityManager() {
				return am;
			}
			@Override
			public StatusListStub initStatusList() {
				return sl;
			}
			@Override
			public Course_StudentListStub initCourse_StudentList() {
				return c_sl;
			}
			@Override
			public CourseSelectionListStub initCourseSelectionList(){
				return csl;
			}
			@Override
			public CourseSelectorNumListStub initCourseSelectorNumList() {
				return csnl;
			}
			@Override
			public CourseListStub initCourseList() {
				return cl;
			}
		};
	}
	
	protected void tearDown() throws Exception {}
	
	public void testShowStatus(){
		String result = cstl.showStatus("function");
		System.out.print(result);
	}
}
