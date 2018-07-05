package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import service.CustomerService;
import service.UsersService;

import entity.Users;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class Cashier extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cashier frame = new Cashier(1003);
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
	public Cashier(final int userId) {
		this.setTitle("收银员界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 523, 370);
		
		JMenuBar menuBar = new JMenuBar();//菜单栏
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("收银员信息管理(M)");//构造一个新 JMenu，用提供的字符串作为其文本。
		mnNewMenu.setMnemonic('M');//键盘助记符
		mnNewMenu.setHorizontalAlignment(SwingConstants.LEFT);//设置对齐方式
		menuBar.add(mnNewMenu);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel cashierId = new JLabel("New label");
		cashierId.setText("当前操作收银员编号:"+userId);
		cashierId.setBounds(55, 267, 155, 15);
		contentPane.add(cashierId);
		
		
		final JButton button = new JButton("会员收银");
		button.setFont(new Font("微软雅黑 Light", Font.PLAIN, 21));
		button.setBounds(72, 91, 150, 88);
		contentPane.add(button);
		
		final JButton button_1 = new JButton("非会员收银");
		button_1.setFont(new Font("微软雅黑 Light", Font.PLAIN, 21));
		button_1.setBounds(264, 91, 155, 88);
		contentPane.add(button_1);
		
		
		JMenuItem jmi1 = new JMenuItem("修改密码(C)");
		jmi1.setMnemonic('C');
		jmi1.setHorizontalAlignment(SwingConstants.CENTER);
		jmi1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.setVisible(false);
				button_1.setVisible(false);
				UsersService us = new UsersService();
				final Users changeUser =  us.getUsersByUserIdService(userId);
				//显示修改密码界面
				{
				JLabel labelun = new JLabel("用户名:");
				labelun.setBounds(88, 40, 54, 15);
				contentPane.add(labelun);
				
				userName = new JTextField();
				userName.setText(changeUser.getUserName());
				userName.setEditable(false);
				userName.setBounds(206, 37, 134, 21);
				contentPane.add(userName);
				userName.setColumns(10);
				
				JLabel label = new JLabel("请输入原密码:");
				label.setBounds(88, 81, 94, 15);
				contentPane.add(label);
				
				textField = new JTextField();
				textField.setColumns(10);
				textField.setBounds(206, 78, 134, 21);
				contentPane.add(textField);
				
				JLabel label_1 = new JLabel("请输入新密码:");
				label_1.setBounds(88, 122, 94, 15);
				contentPane.add(label_1);
				
				textField_1 = new JTextField();
				textField_1.setColumns(10);
				textField_1.setBounds(206, 119, 134, 21);
				contentPane.add(textField_1);
				
				JLabel label_2 = new JLabel("请确认新密码:");
				label_2.setBounds(88, 169, 94, 15);
				contentPane.add(label_2);
				
				textField_2 = new JTextField();
				textField_2.setColumns(10);
				textField_2.setBounds(206, 166, 134, 21);
				contentPane.add(textField_2);
				
				JButton btnNewButton = new JButton("确认修改");
				btnNewButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String psd = textField.getText();
						String newPsd =  textField_1.getText();
						String newPsdc = textField_2.getText();
						if(changeUser.getPassword().equals(psd)){
							if(newPsd.equals(newPsdc)){
								UsersService us1 = new UsersService();
								if(us1.updateUserPasswordService(userId, newPsdc)){
									JOptionPane.showMessageDialog(Cashier.this, "修改成功!");
									Cashier.this.dispose();
									Login frame = new Login();
									frame.setVisible(true);
								}else{
									JOptionPane.showMessageDialog(Cashier.this, "修改失败!");
								}
							}else{
								JOptionPane.showMessageDialog(Cashier.this, "两次输入不一致!","错误提示",JOptionPane.ERROR_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(Cashier.this, "原始密码错误!","错误提示",JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnNewButton.setBounds(117, 216, 93, 23);
				contentPane.add(btnNewButton);
				
				JButton btnNewButton_1 = new JButton("取消");
				btnNewButton_1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Cashier.this.dispose();
						Cashier frame = new Cashier(userId);
						frame.setVisible(true);
						
					}
				});
				btnNewButton_1.setBounds(235, 216, 93, 23);
				contentPane.add(btnNewButton_1);
				//刷新页面
				Cashier.this.repaint();
				}
				
				
				
			}
		});
		mnNewMenu.add(jmi1);
		JMenuItem Cancellation= new JMenuItem("注销(W)");
		Cancellation.setMnemonic('W');
		Cancellation.setHorizontalAlignment(SwingConstants.CENTER);
		Cancellation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cashier.this.dispose();
				Login l = new Login();
				l.setVisible(true);
			}
		});
		mnNewMenu.add(Cancellation);
		
		
		JMenuItem exit = new JMenuItem("退出(R)");
		exit.setMnemonic('R');
		exit.setHorizontalAlignment(SwingConstants.CENTER);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cashier.this.dispose();
			}
		});
		mnNewMenu.add(exit);
		
		
		//会员收银
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setVisible(false);
				button_1.setVisible(false);
				JLabel lblNewLabel = new JLabel("请输入会员卡卡号");
				lblNewLabel.setBounds(101, 86, 109, 15);
				contentPane.add(lblNewLabel);
				
				textField_3 = new JTextField();
				textField_3.setBounds(220, 83, 155, 21);
				contentPane.add(textField_3);
				textField_3.setColumns(10);
				
				JButton btnNewButton_2 = new JButton("收银");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String customerNo1 = textField_3.getText();
						 int customerNo = 0;
						try {
						    customerNo = Integer.parseInt(customerNo1);
						} catch (NumberFormatException e1) {
						    e1.printStackTrace();
						}
						CustomerService cs = new CustomerService();
						if(cs.checkCustomerDao(customerNo)){
							Cashier.this.dispose();
							CollectMoney cm = new CollectMoney(userId, customerNo);
							//System.out.println("cm"+userId);
							cm.setVisible(true);
						}
					}
				});
				btnNewButton_2.setBounds(127, 190, 93, 23);
				contentPane.add(btnNewButton_2);
				
				JButton button = new JButton("返回主窗口");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Cashier.this.dispose();
						Cashier c = new Cashier(userId);
						c.setVisible(true);
					}
				});
				button.setBounds(263, 190, 120, 23);
				contentPane.add(button);
				Cashier.this.repaint();
				
			}
		});
		//非会员收银
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cashier.this.dispose();
				CollectMoney cm = new CollectMoney(userId,0);
				cm.setVisible(true);
			}
		});
	}
}
