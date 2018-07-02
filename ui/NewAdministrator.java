package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import service.UsersService;

import entity.Users;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewAdministrator extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JTextField pwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewAdministrator frame = new NewAdministrator();
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
	public NewAdministrator() {
		this.setTitle("新增管理员");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 505, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("用户名:");
		label.setBounds(85, 47, 54, 15);
		contentPane.add(label);
		
		userName = new JTextField();
		userName.setColumns(10);
		userName.setBounds(186, 44, 168, 21);
		contentPane.add(userName);
		
		JLabel label_1 = new JLabel("密码:");
		label_1.setBounds(85, 95, 54, 15);
		contentPane.add(label_1);
		
		pwd = new JTextField();
		pwd.setColumns(10);
		pwd.setBounds(186, 92, 168, 21);
		contentPane.add(pwd);
		
		JLabel label_2 = new JLabel("管理员级别:");
		label_2.setBounds(85, 144, 66, 15);
		contentPane.add(label_2);
		
		final JRadioButton rdbtnNewRadioButton = new JRadioButton("主管理员");
		rdbtnNewRadioButton.setBounds(173, 140, 93, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		final JRadioButton radioButton = new JRadioButton("货物管理员");
		radioButton.setBounds(268, 140, 94, 23);
		contentPane.add(radioButton);
		
		final JRadioButton radioButton_1 = new JRadioButton("收银员");
		radioButton_1.setBounds(364, 140, 85, 23);
		contentPane.add(radioButton_1);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNewRadioButton);
		bg.add(radioButton);
		bg.add(radioButton_1);
		
		JButton btnNewButton = new JButton("提交");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Users user = new Users();
				user.setUserName(userName.getText());
				user.setPassword(pwd.getText());
				if(rdbtnNewRadioButton.isSelected()){
					user.setRoleId(0);
				}else if(radioButton.isSelected()){
					user.setRoleId(1);
				}else if(radioButton_1.isSelected()){
					user.setRoleId(2);
				}else{
					JOptionPane.showMessageDialog(NewAdministrator.this, "请选择管理员类别!","错误提示",JOptionPane.ERROR_MESSAGE);
				}
				UsersService us = new UsersService();
				if(us.newUsersService(user)){
					JOptionPane.showMessageDialog(NewAdministrator.this, "添加管理员成功!");
				}else{
					JOptionPane.showMessageDialog(NewAdministrator.this, "添加管理员失败!");
				}
			}
		});
		btnNewButton.setBounds(106, 194, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewAdministrator.this.dispose();
				AdministratorManagement am = new AdministratorManagement();
				am.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(241, 194, 93, 23);
		contentPane.add(btnNewButton_1);
	}
}
