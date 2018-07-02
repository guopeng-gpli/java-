package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import service.CustomerService;
import service.ProductsService;
import service.SaleHistoryService;

import entity.ProductInfo;
import entity.SaleHistory;
import entity.SaleSummary;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CollectMoney extends JFrame {

	private JPanel contentPane;
	private double totalAmount = 0;
	private JTable table;
	private JTextField proNo;
	private JTextField number;
	private SaleSummary[] ss = new SaleSummary[20];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CollectMoney frame = new CollectMoney(1003, "000000");
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
	public CollectMoney(final int userId, final String customerNo) {
		this.setTitle("收银界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 599, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("当前操作收银员编号:" + userId);
		lblNewLabel.setBounds(27, 313, 159, 21);
		contentPane.add(lblNewLabel);

		final JLabel lblNewLabel_1 = new JLabel("购物总价:" + totalAmount);
		lblNewLabel_1.setBounds(406, 316, 106, 15);
		contentPane.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 563, 237);
		contentPane.add(scrollPane);

		String[] cols = { "产品编号", "数量", "单价", "商品折扣", "收银员编号", "销售时间", "会员编号",
				"会员折扣", "总价格" };
		final DefaultTableModel model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_2 = new JLabel("商品编号:");
		lblNewLabel_2.setBounds(10, 10, 70, 15);
		contentPane.add(lblNewLabel_2);

		proNo = new JTextField();
		proNo.setBounds(90, 7, 89, 21);
		contentPane.add(proNo);
		proNo.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("会员编号:" + customerNo);
		lblNewLabel_3.setBounds(242, 316, 106, 15);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("数量:");
		lblNewLabel_4.setBounds(10, 41, 54, 15);
		contentPane.add(lblNewLabel_4);

		number = new JTextField();
		number.setColumns(10);
		number.setBounds(90, 38, 89, 21);
		contentPane.add(number);

		JButton btnNewButton = new JButton("确认添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (proNo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入商品编号", "错误提示",
							JOptionPane.ERROR_MESSAGE);
				} else if (number.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入商品数量", "错误提示",
							JOptionPane.ERROR_MESSAGE);
				} else {
					ProductsService ps = new ProductsService();
					String ProductNo = proNo.getText();
					int num = Integer.valueOf(number.getText());
					ProductInfo pi = new ProductInfo();
					pi = ps.getProductInfoByProductNoService(ProductNo);
					SaleSummary s = new SaleSummary();
					if (pi.getCount() >= num) {
						s.setProductNo(ProductNo);
						s.setSaleCount(num);
						s.setPrice(pi.getPrice());
						s.setDiscount(pi.getDiscount());
						s.setUserId(userId);
						Calendar c = Calendar.getInstance();
						DateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						String time = format.format(c.getTime());
						s.setSaleTime(time);
						s.setCustomerNo(customerNo);
						CustomerService cs = new CustomerService();
						//保留两位小数
						DecimalFormat df = new DecimalFormat("#.00");
						/*
						 * 以下两行是判断会员折扣的
						 * */
						s.setMemberDiscount(Double.valueOf(df.format(cs
								.getMemberDiscountByCustomerNoService(customerNo))));
						double amount = pi.getPrice() * num * pi.getDiscount()
								* s.getMemberDiscount();
						
						amount = Double.valueOf(df.format(amount));
						s.setTotalAmount(amount);
						// 添加到数组中
						int i = 0;
						for (; ss[i] != null; i++) {
						}
						ss[i] = s;
						// 增加总金额
						totalAmount += amount;
						totalAmount = Double.valueOf(df.format(totalAmount));
						Object[] row = { s.getProductNo(), s.getSaleCount(),
								s.getPrice(), s.getDiscount(), 0000, time,
								customerNo, s.getMemberDiscount(),
								s.getTotalAmount() };
						model.addRow(row);
						proNo.setText("");
						number.setText("");
						lblNewLabel_1.setText("购物总价:" + totalAmount);
					} else {
						JOptionPane.showMessageDialog(null,
								"库存不足!剩余库存为" + pi.getCount(), "错误提示",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setBounds(223, 6, 93, 23);
		contentPane.add(btnNewButton);

		JButton button = new JButton("删除");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex<0){
					JOptionPane.showMessageDialog(CollectMoney.this, "请选择要删除的行","错误提示",JOptionPane.ERROR_MESSAGE);
				}else{
					if(JOptionPane.showConfirmDialog(CollectMoney.this, "确定删除第"+(rowIndex+1)+"行?")==0){
						int i = rowIndex;
						model.removeRow(i);
						double ramount = ss[i].getPrice()*ss[i].getSaleCount()*ss[i].getDiscount()*ss[i].getMemberDiscount();
						totalAmount -= ramount;
						lblNewLabel_1.setText("购物总价:" + totalAmount);
						for(;ss[i]!=null;i++){
							ss[i] = ss[i+1];
						}
						ss[i] = null;
					}
				}
			}
		});
		button.setBounds(223, 33, 93, 23);
		contentPane.add(button);

		JButton btnNewButton_1 = new JButton("清空");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				ss = new SaleSummary[20];
				totalAmount = 0;
				lblNewLabel_1.setText("购物总价:" + totalAmount);
			}
		});
		btnNewButton_1.setBounds(338, 6, 93, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("结算");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i= 0;ss[i]!=null;i++){
					SaleHistory sh = new SaleHistory();
					sh.setProductNo(ss[i].getProductNo());
					sh.setSaleCount(ss[i].getSaleCount());
					sh.setPrice(ss[i].getPrice());
					sh.setDiscount(ss[i].getDiscount());
					sh.setUserId(ss[i].getUserId());
					sh.setSaleTime(ss[i].getSaleTime());
					sh.setCustomerNo(ss[i].getCustomerNo());
					SaleHistoryService shs = new SaleHistoryService();
					shs.newSaleHistoryService(sh);
					ProductsService ps = new ProductsService();
					ps.updateProductsCountService(ss[i].getProductNo(), ss[i].getSaleCount());
				}
				CollectMoney.this.dispose();
				/*
				 * 如果金额大于等于200且不是会员，则发放一张会员卡
				 * */
//				if(customerNo.equals("000000")&&(totalAmount>200||totalAmount==200)){
//					NewCustomer nc = new NewCustomer();
//					nc.setVisible(true);
//				}
//				else {
//					System.out.println("无需新增会员");
//				}
				if(!customerNo.equals("000000")){
					System.out.println("新增积分");
					CustomerService cs = new CustomerService();
					cs.updateCustomerScoreDao(customerNo,(int)(totalAmount));
				}
				
					System.out.println("算账");
					GiveChange gc = new GiveChange(totalAmount,userId,customerNo);
					gc.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(454, 7, 106, 49);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("返回");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CollectMoney.this.dispose();
				Cashier c = new Cashier(userId);
				c.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(338, 33, 93, 23);
		contentPane.add(btnNewButton_3);

	}
}
