package NJU.HouseWang.nju_eas_server.po.Edu;

public class CourseSelectorNumPO {
	private String courseId;
	private int selectorNum;
	private int totalNum;
	
	public CourseSelectorNumPO(){
		
	}
	
	public CourseSelectorNumPO(String courseId, int selectorNum, int totalNum){
		this.courseId = courseId;
		this.selectorNum = selectorNum;
		this.totalNum = totalNum;
	}
	
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public int getSelectorNum() {
		return selectorNum;
	}
	public void setSelectorNum(int selectorNum) {
		this.selectorNum = selectorNum;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
}
