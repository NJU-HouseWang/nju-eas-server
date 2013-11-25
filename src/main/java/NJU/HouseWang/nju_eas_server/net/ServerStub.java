package NJU.HouseWang.nju_eas_server.net;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import NJU.HouseWang.nju_eas_server.netService.NetService;

public class ServerStub implements NetService {

	@Override
	public void sendFeedback(String fb) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Receive a feedback: " + fb);
	}

	@Override
	public String receiveCommand() throws IOException {
		// TODO Auto-generated method stub
		return "login；Student；121250157；bilicrazy123";
	}

	@Override
	public void sendList(ArrayList<String> list) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Receive a list, the first line is : " + list.get(0));

	}

	@Override
	public ArrayList<String> receiveList() throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> result  = new ArrayList<String>();
		result.add("0001；Teacher");
		result.add("0002；Teacher");
		return result;
	}

	@Override
	public void sendFile(File fileName) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void receiveFile(String fileName) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getIp() throws IOException {
		// TODO Auto-generated method stub
		return "128.0.0.1";
	}

}
