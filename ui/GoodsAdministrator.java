package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

import entity.ProductInfo;

import service.ProductsService;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GoodsAdministrator extends JFrame {

	private JPanel contentPane;
	private JTextField ProductNo;
	private JTextField ProductName;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodsAdministrator frame = new GoodsAdministrator();
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
	public GoodsAdministrator() {
		this.setTitle("商品管理员界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 603, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 53, 570, 224);
		contentPane.add(scrollPane);
		
		String[] cols={"商品编号","商品类型","商品名称","规格","单位","数量","单价","折扣"};
		final DefaultTableModel model = new DefaultTableModel(cols,0);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		
		JLabel lblNewLabel = new JLabel("商品编号:");
		lblNewLabel.setBounds(36, 290, 70, 15);
		contentPane.add(lblNewLabel);
		
		ProductNo = new JTextField();
		ProductNo.setBounds(116, 287, 102, 21);
		contentPane.add(ProductNo);
		ProductNo.setColumns(10);
		
		JLabel label = new JLabel("商品名称:");
		label.setBounds(241, 290, 68, 15);
		contentPane.add(label);
		
		ProductName = new JTextField();
		ProductName.setColumns(10);
		ProductName.setBounds(308, 287, 102, 21);
		contentPane.add(ProductName);
		
		JButton button = new JButton("查询指定商品");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productNo = "";
				String productName = "";
				productNo = ProductNo.getText();
				productName = ProductName.getText();
				ProductsService ps = new ProductsService();
				ProductInfo productInfo = new ProductInfo();
				if(!productNo.isEmpty()){
					model.setRowCount(0);
					productInfo = ps.getProductInfoByProductNoService(productNo);
					Object[] row = {productInfo.getProductNo(),productInfo.getCategoryName(),productInfo.getProductName(),productInfo.getType()
							,productInfo.getUnit(),productInfo.getCount(),productInfo.getPrice(),productInfo.getDiscount()};
					model.addRow(row);
				}else if(!productName.isEmpty()){
					model.setRowCount(0);
					productInfo = ps.getProductInfoByproductNameService(productName);
					Object[] row = {productInfo.getProductNo(),productInfo.getCategoryName(),productInfo.getProductName(),productInfo.getType()
							,productInfo.getUnit(),productInfo.getCount(),productInfo.getPrice(),productInfo.getDiscount()};
					model.addRow(row);
				}else{
					JOptionPane.showMessageDialog(GoodsAdministrator.this, "请输入要查询的商品信息","错误提示",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(451, 287, 117, 23);
		contentPane.add(button);
		
		JButton btnNewButton = new JButton("增加新商品");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoodsAdministrator.this.dispose();
				NewProducts np = new NewProducts();
				np.setVisible(true);
			}
		});
		btnNewButton.setBounds(308, 10, 117, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("删除商品信息");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex<0){
					JOptionPane.showMessageDialog(GoodsAdministrator.this, "请选择要删除的行","错误提示",JOptionPane.ERROR_MESSAGE);
				}else{
					String productNo =   (String) model.getValueAt(rowIndex, 0);
					if(JOptionPane.showConfirmDialog(GoodsAdministrator.this, "确定删除第"+(rowIndex+1)+"行?")==0){
						ProductsService ps = new ProductsService();
						if(ps.deleteProductsService(productNo)){
							JOptionPane.showMessageDialog(GoodsAdministrator.this, "删除成功!");
							GoodsAdministrator.this.dispose();
							GoodsAdministrator frame = new GoodsAdministrator();
							frame.setVisible(true);
						}
					}
				}
			}
		});
		btnNewButton_1.setBounds(446, 10, 122, 23);
		contentPane.add(btnNewButton_1);
		
		JButton showAllProducts = new JButton("查询所有商品");
		showAllProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				ProductsService ps = new ProductsService();
				List<ProductInfo> rows = ps.getAllProductsService();
				for (ProductInfo productInfo : rows) {
					Object[] row = {productInfo.getProductNo(),productInfo.getCategoryName(),productInfo.getProductName(),productInfo.getType()
							,productInfo.getUnit(),productInfo.getCount(),productInfo.getPrice(),productInfo.getDiscount()};
					model.addRow(row);
				}
			}
		});
		showAllProducts.setBounds(27, 10, 117, 23);
		contentPane.add(showAllProducts);
		
		JButton button_1 = new JButton("更新库存信息");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex<0){
					JOptionPane.showMessageDialog(GoodsAdministrator.this, "请选择要更新的行","错误提示",JOptionPane.ERROR_MESSAGE);
				}else{
					Object[] row = {model.getValueAt(rowIndex, 0),model.getValueAt(rowIndex, 2),model.getValueAt(rowIndex, 5),model.getValueAt(rowIndex, 7)};
					GoodsAdministrator.this.dispose();
					UpdateProducts up = new UpdateProducts(row);
					up.setVisible(true);
				}
			}
		});
		button_1.setBounds(172, 10, 126, 23);
		contentPane.add(button_1);
	}
}
