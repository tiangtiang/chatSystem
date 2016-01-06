package travel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Homepage extends JFrame {
	
	private TempMain temp = new TempMain();

	public Homepage() {

		setBak(); // 调用背景方法
		Container c = getContentPane(); // 获取JFrame面板
		JPanel jp = new JPanel(); // 创建个JPanel
		jp.setOpaque(false); // 把JPanel设置为透明 这样就不会遮住后面的背景 这样你就能在JPanel随意加组件了
		c.add(jp);
		setSize(1300, 900);

	}

	public void setBak() {
		((JPanel) this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("55.jpg");
		JLabel background = new JLabel(img);
		JLabel titile = new JLabel("欢迎来到协城旅游");
		titile.setBounds(300, 80, 1200, 200);
		titile.setForeground(Color.white);
		titile.setFont(new Font("黑体", Font.BOLD, 80));
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		JButton but1 = new JButton("了解详情");
		JButton but2 = new JButton("人工服务");
		JButton but3 = new JButton("电话咨询");
		background.add(titile);
		background.add(but1);
		background.add(but2);
		background.add(but3);
		but1.setBounds(200, 550, 200, 70);
		but1.setFont(new Font("黑体", Font.BOLD, 30));
		but1.setForeground(Color.ORANGE);
		but2.setBounds(500, 550, 200, 70);
		but2.setFont(new Font("黑体", Font.BOLD, 30));
		but2.setForeground(Color.ORANGE);
		but3.setBounds(800, 550, 200, 70);
		but3.setForeground(Color.ORANGE);
		but3.setFont(new Font("黑体", Font.BOLD, 30));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		but1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Detail();
			}
		});

		but2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp.createProcess();
			}
		});

		but3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame fr = new JFrame("电话咨询");
				fr.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				fr.setVisible(true);
				fr.setResizable(false);
				fr.setSize(500, 300);
				fr.setLayout(null);
				JTextField jt = new JTextField("如果您有不懂的问题可以拨打电话：123456");
				jt.setFont(new Font("黑体", Font.BOLD, 20));
				jt.setForeground(Color.BLUE);
				jt.setBounds(0, 50, 430, 100);
				JTextField jt1 = new JTextField("祝您有一个美好的旅程");
				jt1.setFont(new Font("黑体", Font.BOLD, 20));
				jt1.setForeground(Color.BLUE);
				jt1.setBounds(0, 150, 300, 100);
				fr.add(jt);
				fr.add(jt1);
				jt.setEditable(false);
				jt1.setEditable(false);
			}
		});
	}

	public static void main(String[] args) {
		new Homepage();
	}

}
