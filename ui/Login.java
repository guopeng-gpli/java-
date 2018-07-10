package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import service.UsersService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Login extends JFrame {

	private JPanel contentPane;//面板  默认流式flowlayout布局
	private JTextField userName;
	final JPasswordField pwd =new JPasswordField();
	private int roleId = 0;//角色

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		this.setTitle("课程设计--超市收银系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//控制面板边界
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Jusername = new JLabel("员工名:");//标签
		/*
		 * setBounds(int x,int y,int width,int height)
		 *  x - 组件的新 x 坐标
			y - 组件的新 y 坐标
			width - 组件的新 width
			height - 组件的新 height		 * 
		 * */
		Jusername.setBounds(82, 52, 54, 15);
		contentPane.add(Jusername);
		
		userName = new JTextField();
		userName.setBounds(186, 49, 141, 21);
		contentPane.add(userName);
		
		JLabel Jpwd = new JLabel("密码:");
		Jpwd.setBounds(82, 98, 54, 15);
		contentPane.add(Jpwd);

		pwd.setBounds(186,95,141,21);
		pwd.setColumns(10);
		contentPane.add(pwd);
		
		JLabel label_1 = new JLabel("员工类别:");
		label_1.setBounds(82, 144, 79, 15);
		contentPane.add(label_1);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"主管理员", "商品管理员", "收银员"}));
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(ItemEvent.SELECTED==e.getStateChange()){
					roleId = comboBox.getSelectedIndex();
				}
			}
		});
		comboBox.setBounds(186, 144, 79, 21);
		contentPane.add(comboBox);
		
		JButton button = new JButton("登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String UserName = userName.getText();//获取输入的名字
				String Pwd = pwd.getText();//获取输入的密码,方法过时！！
				UsersService user = new UsersService();//用户相关服务
				if(user.checkUsersService(UserName, Pwd, roleId)){//将输入的三个参数与数据库中的进行比对
					Login.this.dispose();
					if(roleId == 0){//主管理员
						RootAdministrator ra = new RootAdministrator();
						ra.setVisible(true);
					}else if(roleId == 1){//货物管理
						GoodsAdministrator ga = new GoodsAdministrator();
						ga.setVisible(true);
					}else if(roleId == 2){//收银员
						UsersService us = new UsersService();
						int userId = us.getUserIdByUserNameService(UserName);
						Cashier c = new Cashier(userId);
						c.setVisible(true);
					}
				}else{
					JOptionPane.showMessageDialog(Login.this, "登陆失败!");
				}
			}
		});
		button.setBounds(100, 193, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.this.dispose();
			}
		});
		button_1.setBounds(224, 193, 93, 23);
		contentPane.add(button_1);
	}
}
