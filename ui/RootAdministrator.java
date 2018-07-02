package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class RootAdministrator extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RootAdministrator frame = new RootAdministrator();
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
	public RootAdministrator() {
		this.setTitle("主管理员界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 500, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//修改字体大小
		JLabel lblNewLabel = new JLabel("<html><font style='font:bold 20pt'>管理员,请选择要执行的功能模块</font></html>");
		lblNewLabel.setBounds(78, 33, 318, 49);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("管理员工信息");
		btnNewButton.setFont(new Font("幼圆", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RootAdministrator.this.dispose();
				AdministratorManagement am = new AdministratorManagement();
				am.setVisible(true);
			}
		});
		btnNewButton.setBounds(170, 120, 159, 41);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("管理顾客信息");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RootAdministrator.this.dispose();
				CustomerManagement cm = new CustomerManagement();
				cm.setVisible(true);
			}
		});
		button.setFont(new Font("幼圆", Font.PLAIN, 15));
		button.setBounds(170, 200, 159, 41);
		contentPane.add(button);
		
		JButton btnNewButton1 = new JButton("查询历史信息");
		btnNewButton1.setFont(new Font("幼圆", Font.PLAIN, 15));
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RootAdministrator.this.dispose();
				QueryHistory qh = new QueryHistory(0000,"0000");
				qh.setVisible(true);
			}
		});		
		btnNewButton1.setBounds(170, 280, 159, 41);
		contentPane.add(btnNewButton1);	
		
		JButton btnNewButton_1 = new JButton("返回登录界面");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RootAdministrator.this.dispose();
				Login lg = new Login();
				lg.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(350, 360, 117, 35);
		contentPane.add(btnNewButton_1);
	}

}
