package travel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TempMain {

	public void createProcess(){
		//服务器
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("-jar");
		list.add("server.jar");
		ProcessBuilder pd = new ProcessBuilder(list);
		try {
			pd.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 客户端
		list = new ArrayList<String>();
		list.add("java");
		list.add("-jar");
		list.add("client.jar");
		pd = new ProcessBuilder(list);
		try {
			pd.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
