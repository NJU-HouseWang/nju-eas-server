package NJU.HouseWang.nju_eas_server.po.Edu;

import java.io.File;

import NJU.HouseWang.nju_eas_server.po.DataPOService;
/*
 * PO中包含TeachingPlan中的院系、提交状态、审核状态、doc文件这些属性
 */
public class TeachingPlanPO implements DataPOService {
	private String dept;
	private boolean isCommitted = false;
	//审核状态： 0：未审核	 1： 通过	2：未通过
	private int status = 0;
	File tpFile;
	
	public boolean isCommitted() {
		return isCommitted;
	}

	public void setCommitted(boolean isCommitted) {
		this.isCommitted = isCommitted;
	}

	public File getTpFile() {
		return tpFile;
	}

	public void setTpFile(File tpFile) {
		this.tpFile = tpFile;
	}

	public TeachingPlanPO(){
		
	}
	
	public void setDept(String dept){
		this.dept = dept;
	}
	
	public String getDept(){
		return this.dept;
	}
	
	public void setStatus(int status){
		this.status = status;
	}
	
	public int getStatus(){
		return this.status;
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
	
	public String toCommand(){
		return dept + "；" + isCommitted + "；" + status;
	}

}
