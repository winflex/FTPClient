package ftp.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import ftp.view.components.QuickConnecDialog.ConnectInfo;

public class TestJackson {
	@Test
	public void test() throws JsonGenerationException, JsonMappingException, IOException{
		ConnectInfo info = new ConnectInfo();
		info.setPassword("root");
		info.setPort(21);
		info.setURL("localhost");
		info.setUsername("root");
		List<ConnectInfo> list = new ArrayList<ConnectInfo>();
		list.add(info);
		list.add(info);
		list.add(info);
		ObjectMapper m = new ObjectMapper();
		m.writeValue(new File("D:\\a.json"), list);
		List<ConnectInfo> l = m.readValue(new File("D:\\a.json"), new TypeReference<List<ConnectInfo>>() {});
		for(ConnectInfo i : l){
			System.out.println(i.getUsername());
		}
		System.out.println("---------");
		list.add(info);
		m.writeValue(new File("D:\\a.json"), list);
		l = m.readValue(new File("D:\\a.json"), new TypeReference<List<ConnectInfo>>() {});
		for(ConnectInfo i : l){
			System.out.println(i.getUsername());
		}
	}

}
