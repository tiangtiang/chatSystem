package suzumiya;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ClientUI extends JFrame {
	private JPanel background; // 背景
	private JPanel unChangedArea; // 不可变区域
	private JPanel changedArea; // 可变区域

	private JTextArea showArea; // 展示区：对话记录
	private JTextArea testArea; // 编辑区：输入内容
	private JButton sendButton; // 发送按钮
	private JScrollPane jsp, jsp1; // 滚动条

	private Client client; // 客户端后台
	private OutputStreamWriter writer; // 向服务器中写入数据的数据流

	public ClientUI() {
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
		initClient();
	}

	public void init() {
		background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
		unChangedArea.setLayout(new BoxLayout(unChangedArea, BoxLayout.X_AXIS));

		unChangedArea.add(Box.createHorizontalStrut(20));
		showArea.setEditable(false);
		unChangedArea.add(jsp1);
		unChangedArea.add(Box.createHorizontalStrut(20));

		changedArea.setLayout(new BoxLayout(changedArea, BoxLayout.X_AXIS));

		// editField.setBounds(10,10, 300, 100);
		// changedArea.add(editField);
		jsp.setPreferredSize(new Dimension(300, 10));
		changedArea.add(Box.createHorizontalStrut(20));
		changedArea.add(jsp);
		changedArea.add(sendButton);
		changedArea.add(Box.createHorizontalStrut(20));

		sendButton.addActionListener(new sendListener());

		background.add(unChangedArea);
		background.add(changedArea);
		background.add(Box.createHorizontalStrut(5));
		this.add(background);
		setSize(550, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}

	private void initClient(){
		client = new Client();
		writer = client.getWriter();
		new ReaderThread(client.getReader());
	}
	
	/**
	 * 向通话记录中添加数据
	 * 
	 * @param result
	 *            数据
	 */
	private void addString(String result) {
		StringBuilder text = new StringBuilder(showArea.getText());
		text.append("\n");
		text.append(result);
		showArea.setText(text.toString());
	}

	/**
	 * 检查输入是否合法
	 * 
	 * @param text
	 *            输入内容
	 * @return 是否合法
	 */
	private boolean checkString(String text) {
		if (text.equals("#connect")) {
			if (client == null) {
				initClient();
				return false;
			} else {
				String errMsg4 = "已建立连接！";
				addString(errMsg4);
				return false;
			}
		}
		return true;
	}

	/**
	 * 按钮发送事件监听器
	 * 
	 * @author yaMin
	 *
	 */
	class sendListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			send();
		}

		/**
		 * 向服务器端发送数据
		 */
		void send() {
			String text = testArea.getText(); // 显示的内容
			if (checkString(text)) {
				try {
					writer.write(text + "\n");
					writer.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				testArea.setText("");
				if (text.equals("#quit")) {
					String quitMessage = "连接已断开！";
					addString(quitMessage);
					try {
						writer.close();
						client = null;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				if(text.equals("#connect"))
					testArea.setText("");
			}
		}

	}

	/**
	 * 从服务器中读取数据并显示到聊天记录中
	 * 
	 * @author yaMin
	 *
	 */
	class ReaderThread implements Runnable {
		private BufferedReader reader;

		public ReaderThread(BufferedReader reader) {
			this.reader = reader;
			new Thread(this).start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					addString(reader.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//client.close();
					break;
				}
			}
		}

	}

}
