package nagato;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ServerUI extends JFrame {

	private JPanel background; // 背景
	private JPanel unChangedArea; // 不可变区域
	private JPanel changedArea; // 可变区域

	private JTextArea showArea; // 展示区：对话记录
	private JTextArea testArea; // 编辑区：输入内容
	private JButton sendButton; // 发送按钮
	private JScrollPane jsp, jsp1; // 滚动条

	public ServerThread server; // 服务器线程
	public BufferedWriter bwriter; // 写入流
	public BufferedReader breader; // 读取流

	public ServerUI(ServerThread server) {
		super("服务器线程");
		background = new JPanel();
		unChangedArea = new JPanel();
		changedArea = new JPanel();

		showArea = new JTextArea(23, 40);
		testArea = new JTextArea(1, 30);
		new JTextField(30);
		sendButton = new JButton("发送");
		jsp = new JScrollPane(testArea);
		jsp1 = new JScrollPane(showArea);

		init();

		initServer(server);
	}

	/**
	 * 初始化界面
	 */
	public void init() {
		background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
		unChangedArea.setLayout(new BoxLayout(unChangedArea, BoxLayout.X_AXIS));

		unChangedArea.add(Box.createHorizontalStrut(20));
		showArea.setEditable(false);
		unChangedArea.add(jsp1);
		unChangedArea.add(Box.createHorizontalStrut(20));

		changedArea.setLayout(new BoxLayout(changedArea, BoxLayout.X_AXIS));

		jsp.setPreferredSize(new Dimension(300, 10));
		changedArea.add(Box.createHorizontalStrut(20));
		changedArea.add(jsp);
		changedArea.add(sendButton);
		changedArea.add(Box.createHorizontalStrut(20));

		background.add(unChangedArea);
		background.add(changedArea);
		background.add(Box.createHorizontalStrut(5));

		sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = testArea.getText();
				if (!text.equals("")) {
					try {
						bwriter.write(text + "\n");
						bwriter.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					testArea.setText("");
				}
				addString("服务器：" + text);
			}
		});

		this.add(background);
		setSize(550, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	/**
	 * 初始化服务器线程
	 * 
	 * @param server
	 */
	public void initServer(ServerThread server) {
		this.server = server;
		breader = server.getReader();
		bwriter = server.getwriter();
		showArea.setText("服务器运行中...");

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						addString("客户端：" + breader.readLine());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						break;
					}
				}
			}

		}).start();
	}

	/**
	 * 添加纪录
	 * 
	 * @param result
	 */
	private void addString(String result) {
		StringBuilder text = new StringBuilder(showArea.getText());
		text.append("\n");
		text.append(result);
		showArea.setText(text.toString());
	}

}
