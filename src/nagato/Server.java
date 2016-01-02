package nagato;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * 服务器监听类
 * @author yaMin
 *
 */
public class Server {
	private final int port = 9999;		//服务器监听端口
	
	private ServerSocket server;		//服务器套接字
	
	/**
	 * 初始化服务器
	 */
	private void init(){
		try {
			server = new ServerSocket(port);
			System.out.println("服务器启动...");
			while(true){
				new ServerThread(server.accept());		//创建一个新的服务器线程处理消息
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("服务器启动失败！");
			e.printStackTrace();
		}
	}
	
	public Server(){
		init();
	}
	
	/**
	 * 关闭服务器
	 */
	public void close(){
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			System.out.println("关闭服务器...");
		}
	}
}
