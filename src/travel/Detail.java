package travel;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class Detail extends JFrame {
	
	private TempMain temp = new TempMain();
	
	static JFrame frm=new JFrame("旅游详情");
	static JButton but1_1=new JButton("旅游时间");
	static JButton but2_1=new JButton("查看旅游地详情");
	static JButton but3_1=new JButton("我要订票");
	static JLabel lab1_1=new JLabel("浙江杭州");	
	static JButton but1_2=new JButton("旅游时间");
	static JButton but2_2=new JButton("查看旅游地详情");
	static JButton but3_2=new JButton("我要订票");
	static JLabel lab1_2=new JLabel("张家界");	
	static JButton but1_3=new JButton("旅游时间");
	static JButton but2_3=new JButton("查看旅游地详情");
	static JButton but3_3=new JButton("我要订票");
	static JLabel lab1_3=new JLabel("神农架");	
	static JButton but1_4=new JButton("旅游时间");
	static JButton but2_4=new JButton("查看旅游地详情");
	static JButton but3_4=new JButton("我要订票");
	static JLabel lab1_4=new JLabel("天涯海角");	
	public Detail(){		
		Container c=frm.getContentPane();
		c.setBackground(new Color(63,129,201));
		frm.setLayout(null);
		ImageIcon img = new ImageIcon("杭州.jpg");
		JLabel background = new JLabel(img);
		lab1_1.setFont(new Font("宋体",Font.BOLD,30));
		lab1_1.setForeground(Color.green);	
		lab1_2.setFont(new Font("宋体",Font.BOLD,30));
		lab1_2.setForeground(Color.green);
		lab1_3.setFont(new Font("宋体",Font.BOLD,30));
		lab1_3.setForeground(Color.green);
		lab1_4.setFont(new Font("宋体",Font.BOLD,30));
		lab1_4.setForeground(Color.green);		
		
		but1_1.setFont(new Font("宋体",Font.BOLD,18));
		but1_2.setFont(new Font("宋体",Font.BOLD,18));
		but1_3.setFont(new Font("宋体",Font.BOLD,18));
		but1_4.setFont(new Font("宋体",Font.BOLD,18));		
		
		but2_1.setFont(new Font("宋体",Font.BOLD,18));
		but2_2.setFont(new Font("宋体",Font.BOLD,18));
		but2_3.setFont(new Font("宋体",Font.BOLD,18));
		but2_4.setFont(new Font("宋体",Font.BOLD,18));		
		
		but3_1.setFont(new Font("宋体",Font.BOLD,18));	
		but3_2.setFont(new Font("宋体",Font.BOLD,18));
		but3_3.setFont(new Font("宋体",Font.BOLD,18));
		but3_4.setFont(new Font("宋体",Font.BOLD,18));		
				
		lab1_1.setBounds(350, 90, 300, 100);		
		background.setBounds(10, 30, 300, 400);
		but1_1.setBounds(350,200,180,30);
		but2_1.setBounds(350,250,180,30);
		but3_1.setBounds(350,300,180,30);		
		frm.add(but1_1);
		frm.add(but2_1);
		frm.add(but3_1);
		frm.add(lab1_1);		
		ImageIcon img2 = new ImageIcon("张家界.jpg"); 
		JLabel background2 = new JLabel(img2);
		background2.setBounds(580, 30, 300, 400);
		
		lab1_2.setBounds(920, 90, 300, 100);
		but1_2.setBounds(920,200,180,30);
		but2_2.setBounds(920,250,180,30);
		but3_2.setBounds(920,300,180,30);
		frm.add(background);
		frm.add(background2);
				
		
		
		
		frm.add(lab1_2);
		
		frm.add(but1_2);
		frm.add(but2_2);
		frm.add(but3_2);
		
		ImageIcon img3= new ImageIcon("神农架.jpg"); 
		JLabel background3 = new JLabel(img3);
		background3.setBounds(10, 350, 300, 400);
		lab1_3.setBounds(350, 420, 300, 100);
		but1_3.setBounds(350, 530, 180, 30);
		but2_3.setBounds(350, 580, 180, 30);
		but3_3.setBounds(350, 630, 180, 30);		
		frm.add(background3);
		frm.add(lab1_3);	
		frm.add(but1_3);
		frm.add(but2_3);
		frm.add(but3_3);		
		
		ImageIcon img4= new ImageIcon("天涯海角.jpg"); 
		JLabel background4 = new JLabel(img4);
		background4.setBounds(580, 350, 300, 400);
		lab1_4.setBounds(920, 420, 300, 100);
		but1_4.setBounds(920, 530, 180, 30);
		but2_4.setBounds(920, 580, 180, 30);
		but3_4.setBounds(920, 630, 180, 30);		
		frm.add(background4);
		frm.add(lab1_4);	
		frm.add(but1_4);
		frm.add(but2_4);
		frm.add(but3_4);		
		frm.add(background4);
		
		
				
		frm.setVisible(true);
		frm.setSize(1250, 1000);		
		but1_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f1=new JFrame("浙江杭州旅行时间");
				JTextArea ta=new JTextArea();
				ta.setText("我们带您去张家界的时间有\n"
						+"2016年1月5日到1月10日\n"
						+"或者，您也可以选择\n"
						+"2016年3月5日到3月10日\n"
						+"我们还有后续旅程为您设计，只要您需要，可以电话咨询我们的客服或者选择人工服务\n"
						+"谢谢您光临我们的旅游网");
				JScrollPane t=new JScrollPane(ta);
				ta.setLineWrap(true);
				ta.setEditable(false);
				ta.setFont(new Font ("楷体",Font.ITALIC,25));
				ta.setForeground(Color.GREEN);
				f1.add(t);
				f1.setSize(500,500);
				f1.setVisible(true);
			}
		});
		
		
		
		
		but2_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		but3_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				temp.createProcess();
			}
		});
		
		
		
		
		
		but1_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f1=new JFrame("张家界旅行时间");
				JTextArea ta=new JTextArea();
				ta.setText("我们带您去张家界的时间有\n"
						+"2016年1月5日到1月10日\n"
						+"或者，您也可以选择\n"
						+"2016年3月5日到3月10日\n"
						+"我们还有后续旅程为您设计，只要您需要，可以电话咨询我们的客服或者选择人工服务\n"
						+"谢谢您光临我们的旅游网");
				JScrollPane t=new JScrollPane(ta);
				ta.setLineWrap(true);
				ta.setEditable(false);
				ta.setFont(new Font ("楷体",Font.ITALIC,25));
				ta.setForeground(Color.GREEN);
				f1.add(t);
				f1.setSize(500,500);
				f1.setVisible(true);
				
			}
		});     
		
       but2_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				
			}
		});
		
       but3_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				temp.createProcess();
				
			}
		});	
       but1_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f1=new JFrame("神农架旅行时间");
				JTextArea ta=new JTextArea();
				ta.setText("我们带您去张家界的时间有\n"
						+"2016年1月5日到1月10日\n"
						+"或者，您也可以选择\n"
						+"2016年3月5日到3月10日\n"
						+"我们还有后续旅程为您设计，只要您需要，可以电话咨询我们的客服或者选择人工服务\n"
						+"谢谢您光临我们的旅游网");
				JScrollPane t=new JScrollPane(ta);
				ta.setLineWrap(true);
				ta.setEditable(false);
				ta.setFont(new Font ("楷体",Font.ITALIC,25));
				ta.setForeground(Color.GREEN);
				f1.add(t);
				f1.setSize(500,500);
				f1.setVisible(true);
				
				
			}
		});
       but2_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				
			}
		});
       but3_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				temp.createProcess();
				
			}
		});
       but1_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f1=new JFrame("天涯海角旅行时间");
				JTextArea ta=new JTextArea();
				ta.setText("我们带您去张家界的时间有\n"
						+"2016年1月5日到1月10日\n"
						+"或者，您也可以选择\n"
						+"2016年3月5日到3月10日\n"
						+"我们还有后续旅程为您设计，只要您需要，可以电话咨询我们的客服或者选择人工服务\n"
						+"谢谢您光临我们的旅游网");
				JScrollPane t=new JScrollPane(ta);
				ta.setLineWrap(true);
				ta.setEditable(false);
				ta.setFont(new Font ("楷体",Font.ITALIC,25));
				ta.setForeground(Color.GREEN);
				f1.add(t);
				f1.setSize(500,500);
				f1.setVisible(true);
				
				
			}
		});  
       but2_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				
			}
		}); 
       but3_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				temp.createProcess();
				
			}
		});
       
		
		
	}
	

}

