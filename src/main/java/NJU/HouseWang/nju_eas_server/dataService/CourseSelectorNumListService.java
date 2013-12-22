package NJU.HouseWang.nju_eas_server.dataService;

import NJU.HouseWang.nju_eas_server.po.Edu.CourseSelectorNumPO;
import NJU.HouseWang.nju_eas_server.systemMessage.Feedback;

public interface CourseSelectorNumListService {
	public Feedback delList();

	public Feedback updateCourseSelectorNumPO(CourseSelectorNumPO p);

	public Feedback addCourseSelectorNumPO(CourseSelectorNumPO p);

	public CourseSelectorNumPO getCourseSelectorNumPO(String courseId);

}
