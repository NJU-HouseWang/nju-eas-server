package NJU.HouseWang.nju_eas_server.netService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface NetService {

	// 发送反馈
	public void sendFeedback(String fb) throws IOException;

	// 接收命令
	public String receiveCommand() throws IOException;

	// 发送列表
	public void sendList(ArrayList<?> list) throws IOException;

	// 接收列表
	public ArrayList<String> receiveList() throws IOException;

	// 发送文件
	public void sendFile(File fileName) throws IOException;

	// 接收文件
	public File receiveFile(String fileName) throws IOException;
	
	//得到ip
	public String getIp() throws IOException;
}
