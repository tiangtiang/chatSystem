package nagato;

import java.io.BufferedReader;
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
public class ServerThread implements Runnable {

	private Socket client; 			// 客户端套接字
	private BufferedReader breader; // 读入流
	private OutputStreamWriter bwriter; // 写入流
	private ServerDb sdb; 			// 存储器
	private String userName;		//用户名
	
	private final String ENCODING = "GB2312";

	public ServerThread(Socket client) {
		this.client = client;
		sdb = new ServerDb();
		init();
		new Thread(this).start();
	}

	/**
	 * 初始化线程
	 */
	private void init() {
		try {
			System.out.println(client.getPort()+"\t"+client.getInetAddress());
			// 初始化读取流
			breader = new BufferedReader(new InputStreamReader(
					client.getInputStream(),ENCODING));
			bwriter = new OutputStreamWriter(client.getOutputStream(),ENCODING);

			final String initialMsg = "输入唯一的用户名: \n";
			final String errorMsg = "用户名已存在！重新输入: \n";
			final String welcomMsg = "欢迎";

			//初始化用户名
			bwriter.write(initialMsg);
			bwriter.flush();
			String result = breader.readLine();
			
			System.out.println(result);
			while (sdb.get(result) != null) {
				bwriter.write(errorMsg);
				bwriter.flush();
				result = breader.readLine();
				
				System.out.println(result);
			}
			sdb.put(result, client);
			bwriter.write(welcomMsg+result+"\n");
			bwriter.flush();
			userName = result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("初始化读取流失败！");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				String command = breader.readLine();
				if(command.equals("#quit")){
					//close();
					break;
				}
				analysCommand(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sdb.remove(userName);
	}

	/**
	 * 解析命令
	 * 
	 * @param command
	 *            命令
	 */
	private void analysCommand(String command) {
		try {
			if (command.equals("#list"))
				bwriter.write("#"+sdb.toString());
			else {
				char[] charCmd = command.toCharArray();
				if (charCmd[0] != '#') {
					final String errMsg2 = "无效命令！ \n";
					
					bwriter.write(errMsg2);
					bwriter.flush();
				} else {
					String name = command.split(" ")[0].substring(1);
					String msg = command.substring(name.length() + 2);
					sendMessage(name, msg);
				}
			}
			bwriter.flush();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 发送信息
	 * 
	 * @param name
	 *            目标用户名
	 * @param msg
	 *            信息
	 */
	private void sendMessage(String name, String msg) {
		Socket des = sdb.get(name);
		try {
			if (des == null) {
				String errMsg3 = "找不到用户：" + name+"\n";
				bwriter.write(errMsg3);
				bwriter.flush();
			} else {
				OutputStreamWriter out = new OutputStreamWriter(des.getOutputStream());
				out.write(userName+": "+msg+"\n");
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
