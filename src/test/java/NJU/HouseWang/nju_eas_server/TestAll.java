package NJU.HouseWang.nju_eas_server;

import NJU.HouseWang.nju_eas_server.logicTest.AnnouncementLogicTest;
import NJU.HouseWang.nju_eas_server.logicTest.CourseInfoLogicTest;
import NJU.HouseWang.nju_eas_server.logicTest.CourseSelectionLogicTest;
import NJU.HouseWang.nju_eas_server.logicTest.EduFrameworkLogicTest;
import NJU.HouseWang.nju_eas_server.logicTest.LogLogicTest;
import NJU.HouseWang.nju_eas_server.logicTest.LoginLogicTest;
import NJU.HouseWang.nju_eas_server.logicTest.MessageLogicTest;
import NJU.HouseWang.nju_eas_server.logicTest.TeachingPlanLogicTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestAll extends TestCase{
	public static Test suite(){
		TestSuite suite = new TestSuite();
		suite.addTestSuite(AnnouncementLogicTest.class);
		suite.addTestSuite(CourseInfoLogicTest.class);
		suite.addTestSuite(CourseSelectionLogicTest.class);
		suite.addTestSuite(EduFrameworkLogicTest.class);
		suite.addTestSuite(LoginLogicTest.class);
		suite.addTestSuite(LogLogicTest.class);
		suite.addTestSuite(MessageLogicTest.class);
		suite.addTestSuite(TeachingPlanLogicTest.class);
		return suite;
	}
}
