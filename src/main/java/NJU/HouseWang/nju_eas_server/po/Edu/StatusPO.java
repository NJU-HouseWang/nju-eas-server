package NJU.HouseWang.nju_eas_server.po.Edu;

import NJU.HouseWang.nju_eas_server.po.DataPOService;

public class StatusPO implements DataPOService{
	private String status; //选课、补选、退选、学期的状态名
	private String content;	//状态的内容
	public StatusPO() {		
	}
	
	public StatusPO(String status, String content){
		this.status = status;
		this.content = content;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public void setContent(String content){
		this.content  = content;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public String toCommand(){
		return (status + "；" + content);
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
