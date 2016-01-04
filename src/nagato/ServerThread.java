package nagato;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 服务器线程
 * 
 * @author yaMin
 *
 */
public class ServerThread {

	private Socket client; 			// 客户端套接字
	private BufferedReader breader; // 读入流
	private BufferedWriter bwriter; // 写入流
	
	private final String ENCODING = "GB2312";

	public ServerThread(Socket client, int i) {
		this.client = client;
		init();
		System.out.println("服务器线程"+i+"已启动...");
	}

	/**
	 * 初始化线程
	 */
	private void init() {
		try {
			
			// 初始化读取流
			breader = new BufferedReader(new InputStreamReader(
					client.getInputStream(),ENCODING));
			bwriter = new BufferedWriter(
					new OutputStreamWriter(client.getOutputStream(),ENCODING));

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedReader getReader(){
		return breader;
	}
	
	public BufferedWriter getwriter(){
		return bwriter;
	}
}
