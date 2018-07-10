package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class GiveChange extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private double change = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiveChange frame = new GiveChange(0,1003,0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param customerNo 
	 */
	public GiveChange(final double totalAmount,final int userId, final int customerNo) {
		this.setTitle("找零界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商品总金额:"+totalAmount);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 23));
		lblNewLabel.setBounds(56, 33, 294, 57);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("支付金额:");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(56, 100, 87, 32);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(153, 107, 130, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		final JLabel label = new JLabel("找零金额:"+change);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBounds(56, 183, 186, 32);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("确认支付");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change = Double.valueOf(textField.getText())-totalAmount;
				DecimalFormat df = new DecimalFormat("#.00");
				change = Double.valueOf(df.format(change));
				if(change<0){
					JOptionPane.showMessageDialog(null,"付款不足，请支付"+totalAmount+"元","错误提示",JOptionPane.ERROR_MESSAGE);}
				else{
				label.setText("找零金额:"+change);
				}
				
				
				if(customerNo==0&&(totalAmount>200||totalAmount==200)){
					NewCustomer0 nc = new NewCustomer0(userId);
					nc.setVisible(true);
				}
				else {
					System.out.println("无需新增会员");
				}
			}
		});
		btnNewButton.setBounds(50, 150, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GiveChange.this.dispose();
				Cashier frame = new Cashier(userId);
				frame.setVisible(true);
			}
		});
		button.setBounds(173, 150, 93, 23);
		contentPane.add(button);
	}
}
