package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import service.ProductsService;

import entity.Products;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class NewProducts extends JFrame {

	private JPanel contentPane;
	private JTextField ProductNo;
	private JTextField ProductName;
	private JTextField Type;
	private JTextField Unit;
	private JTextField Count;
	private JTextField Price;
	private JTextField Discount;
	private String categoryId = "001";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewProducts frame = new NewProducts();
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
	public NewProducts() {
		this.setTitle("新增商品信息");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 450, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商品编号:");
		lblNewLabel.setBounds(67, 22, 72, 15);
		contentPane.add(lblNewLabel);
		
		ProductNo = new JTextField();
		ProductNo.setBounds(164, 19, 144, 21);
		contentPane.add(ProductNo);
		ProductNo.setColumns(10);
		
		JLabel label = new JLabel("商品名称:");
		label.setBounds(67, 50, 72, 15);
		contentPane.add(label);
		
		ProductName = new JTextField();
		ProductName.setColumns(10);
		ProductName.setBounds(164, 47, 144, 21);
		contentPane.add(ProductName);
		
		JLabel label_1 = new JLabel("商品类别:");
		label_1.setBounds(67, 75, 72, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("规格:");
		label_2.setBounds(67, 119, 54, 15);
		contentPane.add(label_2);
		
		Type = new JTextField();
		Type.setColumns(10);
		Type.setBounds(164, 116, 144, 21);
		contentPane.add(Type);
		
		JLabel label_3 = new JLabel("单位:");
		label_3.setBounds(67, 147, 54, 15);
		contentPane.add(label_3);
		
		Unit = new JTextField();
		Unit.setColumns(10);
		Unit.setBounds(164, 144, 144, 21);
		contentPane.add(Unit);
		
		JLabel label_4 = new JLabel("数量:");
		label_4.setBounds(67, 175, 54, 15);
		contentPane.add(label_4);
		
		Count = new JTextField();
		Count.setColumns(10);
		Count.setBounds(164, 172, 144, 21);
		contentPane.add(Count);
		
		JLabel label_5 = new JLabel("售价:");
		label_5.setBounds(67, 203, 54, 15);
		contentPane.add(label_5);
		
		Price = new JTextField();
		Price.setColumns(10);
		Price.setBounds(164, 200, 144, 21);
		contentPane.add(Price);
		
		JLabel label_6 = new JLabel("折扣:");
		label_6.setBounds(67, 231, 54, 15);
		contentPane.add(label_6);
		
		Discount = new JTextField();
		Discount.setColumns(10);
		Discount.setBounds(164, 228, 144, 21);
		contentPane.add(Discount);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"食品", "饮料烟酒", "粮油", "生鲜", "文体办公", "家电五金", "洗浴用品"}));
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(ItemEvent.SELECTED==e.getStateChange()){
					categoryId = "00"+(comboBox.getSelectedIndex()+1);
				}
			}
		});
		comboBox.setBounds(164, 78, 82, 21);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("保存");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Products product = new Products();
				if(ProductNo.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"商品编号不能为空","错误提示",JOptionPane.ERROR_MESSAGE);
				}else{
					product.setProductNo(ProductNo.getText());
					product.setCategoryId(categoryId);
					if(ProductName.getText().isEmpty()){
						JOptionPane.showMessageDialog(null,"商品名称不能为空","错误提示",JOptionPane.ERROR_MESSAGE);
					}else{
						product.setProductName(ProductName.getText());
						product.setType(Type.getText());
						if(Unit.getText().isEmpty()){
							JOptionPane.showMessageDialog(null,"商品单位不能为空","错误提示",JOptionPane.ERROR_MESSAGE);
						}else{
							product.setUnit(Unit.getText());
							if(Count.getText().isEmpty()){
								JOptionPane.showMessageDialog(null,"库存数量不能为空","错误提示",JOptionPane.ERROR_MESSAGE);
							}else{
								product.setCount(Integer.valueOf(Count.getText()));
								String str1=Price.getText();
								int a = 0;
								try {
								    a = Integer.parseInt(str1);
								} catch (NumberFormatException e1) {
								    e1.printStackTrace();
								}
								if(Price.getText().isEmpty()||a<=0){
									JOptionPane.showMessageDialog(null,"单价不能为空或负数","错误提示",JOptionPane.ERROR_MESSAGE);
								}else{
									product.setPrice(Double.valueOf(Price.getText()));
									if(Discount.getText().isEmpty()){
										JOptionPane.showMessageDialog(null,"折扣不能为空","错误提示",JOptionPane.ERROR_MESSAGE);
									}else{
										product.setDiscount(Double.valueOf(Discount.getText()));
										ProductsService ps = new ProductsService();
										if(ps.checkProductsNoDao(ProductNo.getText())){
											if(ps.newProductsService(product)){
												JOptionPane.showMessageDialog(NewProducts.this, "添加商品成功!");
												NewProducts.this.dispose();
												NewProducts np = new NewProducts();
												np.setVisible(true);
											}else{
												JOptionPane.showMessageDialog(NewProducts.this, "添加商品失败!");
											}
										}
									}
								}
							}
						}
					}
				}
			}
		});
		btnNewButton.setBounds(115, 275, 175, 23);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("返回商品管理界面");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewProducts.this.dispose();
				GoodsAdministrator ga = new GoodsAdministrator();
				ga.setVisible(true);
			}
		});
		button.setBounds(115, 316, 175, 23);
		contentPane.add(button);
	}
}
