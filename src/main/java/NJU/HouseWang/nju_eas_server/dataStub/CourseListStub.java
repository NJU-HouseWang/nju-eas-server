package NJU.HouseWang.nju_eas_server.dataStub;

import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.data.CourseList;
import NJU.HouseWang.nju_eas_server.po.Edu.CoursePO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class CourseListStub extends CourseList{
	// 连接数据库
		public void init() {
			
		}

		// 关闭数据库
		public void finish() {
		}

		// 是否包含ID
		public boolean containsID(String id) {
			Boolean result = true;
			return result;
		}

		public CoursePO getCourse(String id) {
			CoursePO result = new CoursePO(id,"微积分");
			return result;
		}

		public Feedback removeCourse(String id) {
			return Feedback.OPERATION_SUCCEED;
		}

		public Feedback addCourse(CoursePO course) {
			return Feedback.OPERATION_SUCCEED;
		}

		public Feedback updateCourse(CoursePO course) {
			return Feedback.OPERATION_SUCCEED;
		}

		public ArrayList<CoursePO> getCourseList(String conditions) {
			ArrayList<CoursePO> result = new ArrayList<CoursePO>();
			CoursePO c1 = new CoursePO("1001","微积分");
			CoursePO c2 = new CoursePO("1002","线性代数");
			CoursePO c3 = new CoursePO("1003","离散数学");
			result.add(c1);
			result.add(c2);
			result.add(c3);
			return result;
		}

}
