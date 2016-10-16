package ftp;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import ftp.view.components.QuickConnecDialog.ConnectInfo;

public class FTPHandler implements Connector{

	private static FTPClient ftpClient;
	
	public FTPHandler() {
		ftpClient = new FTPClient();
	}

	@Override
	public ConnectResult connect(ConnectInfo cf) {
		try {
			ftpClient.connect(cf.getURL(), cf.getPort());
			return new ConnectResult(true, "连接成功");
		} catch (Exception e) {
			return new ConnectResult(false, ftpClient.getReplyString());
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		ConnectInfo cf = new ConnectInfo();
		cf.setPort(21);
		cf.setURL("localhost");
		cf.setUsername("Anonymous");
		cf.setPassword("lixiaohui");
		FTPHandler h = new FTPHandler();
		ConnectResult cr = h.connect(cf);
		//ftpClient.login(cf.getUsername(), cf.getPassword());
		if(cr.isSuccess()){
			ftpClient.listFiles();
			System.out.println(ftpClient.getReplyString());
		}
	}
}
