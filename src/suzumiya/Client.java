package suzumiya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端后台
 * @author yaMin
 *
 */
public class Client {
	private final String IP = "localhost";
	private final int PORT = 9999;
	private Socket client;
	private BufferedReader inreader; // 从服务器读数据
	private OutputStreamWriter inwriter; // 向服务器写数据
	private BufferedReader outreader; // 从控制台读数据
	private OutputStreamWriter outwriter; // 向控制台写数据

	private final String encoding = "GB2312";		//流编码

	public Client() {
		init();
	}

	private void init() {
		try {
			client = new Socket(IP, PORT);

			// 初始化输入输出流
			outreader = new BufferedReader(new InputStreamReader(System.in,
					encoding));
			outwriter = new OutputStreamWriter(System.out, encoding);

			inreader = new BufferedReader(new InputStreamReader(
					client.getInputStream(), encoding));
			inwriter = new OutputStreamWriter(client.getOutputStream(),
					encoding);

			outwriter.write("已连接服务器...");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 执行程序
	 */
	public void excute() {

		new ReaderThread(inreader, outwriter);
		new ReaderThread(outreader, inwriter);
	}

	/**
	 * 关闭套接字等
	 */
	public void close() {
		try {
			outwriter.write("连接已关闭！");
			inwriter.close();
			inreader.close();
			outwriter.close();
			outreader.close();
			client.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 线程类，用来进行读写操作
	 * @author yaMin
	 *
	 */
	class ReaderThread implements Runnable {

		private BufferedReader reader;
		private OutputStreamWriter writer;

		public ReaderThread(BufferedReader reader, OutputStreamWriter writer) {
			this.reader = reader;
			this.writer = writer;
			new Thread(this).start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {

				while (true) {
					String result = reader.readLine();
					writer.write(result + "\n");
					writer.flush();
					if (result.equals("#quit"))
						break;
				}
				reader.close();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				close();
			}
		}

	}

}