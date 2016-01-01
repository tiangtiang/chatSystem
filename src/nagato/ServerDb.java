package nagato;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 存储客户端信息
 * 
 * @author yaMin
 *
 */
public class ServerDb {
	private static Map<String, Socket> user = new HashMap<String, Socket>(); // 存储用户名和对应套接字

	/**
	 * 存储一对新的用户名和套接字组合
	 * 
	 * @param name
	 *            用户名
	 * @param client
	 *            对应套接字
	 */
	public void put(String name, Socket client) {
		user.put(name, client);
	}

	/**
	 * 获取用户名对应套接字
	 * 
	 * @param name
	 *            用户名
	 * @return 对应套接字
	 */
	public Socket get(String name) {
		return user.get(name);
	}

	/**
	 * 转化成字符串
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (String key : user.keySet())
			str.append(key + "\t\t\t" + user.get(key) + "\n");
		return str.toString();
	}
}
