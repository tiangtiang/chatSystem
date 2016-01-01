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
public class ServerThread implements Runnable {

	private Socket client; // 客户端套接字
	private BufferedReader breader; // 读入流
	private BufferedWriter bwriter; // 写入流
	private ServerDb sdb; // 存储器

	public ServerThread(Socket client) {
		this.client = client;
		sdb = new ServerDb();
		init();
	}

	/**
	 * 初始化线程
	 */
	private void init() {
		try {
			// 初始化读取流
			breader = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			bwriter = new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream()));

			final String initialMsg = "输入唯一的用户名: ";
			final String errorMsg = "用户名已存在！重新输入: ";

			bwriter.write(initialMsg);
			String result = breader.readLine();
			while (sdb.get(result) != null) {
				bwriter.write(errorMsg);
				result = breader.readLine();
			}
			sdb.put(result, client);
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
				analysCommand(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
				bwriter.write(sdb.toString());
			else {
				char[] charCmd = command.toCharArray();
				if (charCmd[0] != '#') {
					String errMsg2 = "无效命令！ ";
					bwriter.write(errMsg2);
				} else {
					String name = command.split(" ")[0].substring(1);
					String msg = command.substring(name.length() + 2);
					sendMessage(name, msg);
				}
			}
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
				String errMsg3 = "找不到用户：" + name;
				bwriter.write(errMsg3);
			} else {
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						des.getOutputStream()));
				out.write(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
