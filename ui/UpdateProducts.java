package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import service.ProductsService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateProducts extends JFrame {

	private JPanel contentPane;
	private JTextField productName;
	private JTextField addCount;
	private JTextField discount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Object[] row = {"dd","ww","ww","1"};
					UpdateProducts frame = new UpdateProducts(row);
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
	public UpdateProducts(final Object[] row) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商品名称:");
		lblNewLabel.setBounds(98, 46, 68, 15);
		contentPane.add(lblNewLabel);
		
		productName = new JTextField();
		productName.setEditable(false);
		productName.setText((String) row[1]);
		productName.setBounds(192, 43, 151, 21);
		contentPane.add(productName);
		productName.setColumns(10);
		
		JLabel label = new JLabel("新增库存:");
		label.setBounds(98, 94, 68, 15);
		contentPane.add(label);
		
		addCount = new JTextField();
		addCount.setColumns(10);
		addCount.setBounds(192, 91, 151, 21);
		contentPane.add(addCount);
		
		JLabel label_1 = new JLabel("折扣:");
		label_1.setBounds(98, 139, 54, 15);
		contentPane.add(label_1);
		
		discount = new JTextField();
		discount.setText(String.valueOf(row[3]));
		discount.setColumns(10);
		discount.setBounds(192, 136, 151, 21);
		contentPane.add(discount);
		
		JButton btnNewButton = new JButton("更新");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = (Integer)(row[2]) + Integer.valueOf(addCount.getText());
				double Discount = Double.valueOf(discount.getText());
				String productNo = (String) row[0];
				ProductsService ps = new ProductsService();
				if(ps.updateProductsService(productNo, count, Discount)){
					JOptionPane.showMessageDialog(UpdateProducts.this, "更新库存信息成功!当前库存:"+count+"当前折扣:"+Discount);
				}else{
					JOptionPane.showMessageDialog(UpdateProducts.this, "更新库存信息失败!");
				}
				
			}
		});
		btnNewButton.setBounds(112, 190, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateProducts.this.dispose();
				GoodsAdministrator ga = new GoodsAdministrator();
				ga.setVisible(true);
			}
		});
		button.setBounds(231, 190, 93, 23);
		contentPane.add(button);
	}
}
