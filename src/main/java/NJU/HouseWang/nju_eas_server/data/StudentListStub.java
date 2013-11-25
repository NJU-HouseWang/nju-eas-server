package NJU.HouseWang.nju_eas_server.data;

import java.sql.SQLException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.po.User.StudentPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public class StudentListStub extends StudentList{
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

		public StudentPO getStudent(String id) {
			StudentPO result = new StudentPO("121250157","王鑫","软件学院","软件工程","2012级","3班","四年","正常");
			return result;
		}

		public Feedback removeStudent(String id) {
			return Feedback.OPERATION_SUCCEED;
		}

		public Feedback addStudent(StudentPO user) {
			return Feedback.OPERATION_SUCCEED;
		}

		public Feedback updateStudent(StudentPO user) {
			return Feedback.OPERATION_SUCCEED;
		}

		public ArrayList<StudentPO> getStudentList(String conditions) {
			ArrayList<StudentPO> result = new ArrayList<StudentPO>();
			StudentPO s1 = new StudentPO("121250157","王鑫","软件学院","软件工程","2012级","3班","四年","正常");
			StudentPO s2 = new StudentPO("121250158","王穴痒","软件学院","软件工程","2012级","3班","四年","正常");
			result.add(s1);
			result.add(s2);
			return result;
		}

}
