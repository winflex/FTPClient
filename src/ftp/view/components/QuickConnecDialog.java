package ftp.view.components;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import ftp.Connector;
/**
 * 快速连接窗口
 * @author lixiaohui
 *
 */
public class QuickConnecDialog extends JDialog {

	private Connector connector;//连接时调用该对象的connect方法
	
	private String filepath = System.getProperty("user.dir") + "\\save\\history.json";//历史纪录保存文件路径
	
	private List<ConnectInfo> connectInfoList;
	
	public QuickConnecDialog() {
		this("快速连接");
	}
	
	public QuickConnecDialog(String title) {
		this.setTitle(title);
	}

	//-------------utility method---------
	
	//读取json，转化为ConnectInfo
	private List<ConnectInfo> getConnectInfoList() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<ConnectInfo> list = mapper.readValue(new File(filepath), new TypeReference<List<ConnectInfo>>() {});
			return list;
		} catch (Exception e) {
			return new ArrayList<ConnectInfo>();
		}
	}
	//保存ConnectInfo为json
	private void saveConnectInfoList(List<ConnectInfo> list){
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(filepath), list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//---------getter setter
	
	public Connector getConnector() {
		return connector;
	}

	public void setConnector(Connector connector) {
		this.connector = connector;
	}

	//---------inner classes

	public static class ConnectInfo{
		private String URL;
		private int port;
		private String username;
		private String password;
		private boolean anonymous;//匿名
		private String connectType;
		
		public ConnectInfo(String uRL, int port, String username,
				String password, boolean anonymous, String connectType) {
			super();
			URL = uRL;
			this.port = port;
			this.username = username;
			this.password = password;
			this.anonymous = anonymous;
			this.connectType = connectType;
		}
		public ConnectInfo() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getURL() {
			return URL;
		}
		public void setURL(String uRL) {
			URL = uRL;
		}
		public int getPort() {
			return port;
		}
		public void setPort(int port) {
			this.port = port;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getConnectType() {
			return connectType;
		}
		public void setConnectType(String connectType) {
			this.connectType = connectType;
		}
		public boolean isAnonymous() {
			return anonymous;
		}
		public void setAnonymous(boolean anonymous) {
			this.anonymous = anonymous;
		}
	}
}
