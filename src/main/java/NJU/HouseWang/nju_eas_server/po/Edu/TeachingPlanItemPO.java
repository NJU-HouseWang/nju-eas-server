package NJU.HouseWang.nju_eas_server.po.Edu;

import NJU.HouseWang.nju_eas_server.po.DataPOService;

public class TeachingPlanItemPO implements DataPOService {
	private String moduleId;
	private String moduleName;
	private int moduleCredit;
	private String courseNature;
	private String courseType;
	private int typeCredit;
	private String courseId;
	private String courseName;
	private int courseCredit;
	private String period;
	private int startTerm;

	public TeachingPlanItemPO() {

	}

	public TeachingPlanItemPO(String moduleId, String moduleName,
			int moduleCredit, String courseNature, String courseType,
			int typeCredit, String courseId, String courseName,
			int courseCredit, String period, int startTerm) {
		super();
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleCredit = moduleCredit;
		this.courseNature = courseNature;
		this.courseType = courseType;
		this.typeCredit = typeCredit;
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseCredit = courseCredit;
		this.period = period;
		this.startTerm = startTerm;
	}

	public TeachingPlanItemPO(String moduleId, String moduleName,
			String moduleCredit, String courseNature, String courseType,
			String typeCredit, String courseId, String courseName,
			String courseCredit, String period, String startTerm) {
		super();
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleCredit = Integer.parseInt(moduleCredit);
		this.courseNature = courseNature;
		this.courseType = courseType;
		this.typeCredit = Integer.parseInt(typeCredit);
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseCredit = Integer.parseInt(courseCredit);
		this.period = period;
		this.startTerm = Integer.parseInt(startTerm);
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public int getModuleCredit() {
		return moduleCredit;
	}

	public void setModuleCredit(int moduleCredit) {
		this.moduleCredit = moduleCredit;
	}

	public String getCourseNature() {
		return courseNature;
	}

	public void setCourseNature(String courseNature) {
		this.courseNature = courseNature;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public int getTypeCredit() {
		return typeCredit;
	}

	public void setTypeCredit(int typeCredit) {
		this.typeCredit = typeCredit;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(int courseCredit) {
		this.courseCredit = courseCredit;
	}

	public int getStartTerm() {
		return startTerm;
	}

	public void setStartTerm(int startTerm) {
		this.startTerm = startTerm;
	}

	public String toCommand() {
		return (moduleId + " " + moduleName + "(" + moduleCredit + ")；"
				+ courseNature + "；" + courseType + "(" + typeCredit + ")；"
				+ courseId + "；" + courseName + "；" + courseCredit + "；"
				+ period + "；" + startTerm);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub

	}
}
