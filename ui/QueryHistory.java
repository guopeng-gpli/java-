package ui;

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
import service.HistoryService;
import entity.HistoryInfo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class QueryHistory extends JFrame {

	private JPanel contentPane;
	private double totalAmount = 0;
	private JTable table;
	private JTextField CusNo;
	private JTextField caNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueryHistory frame = new QueryHistory(1003, "000000");
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
	public QueryHistory( int userId,  String customerNo) {
		final int nuserid = 0;
		 final String ncustomerNo = null;
		this.setTitle("历史信息查询界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 750, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 700, 237);
		contentPane.add(scrollPane);

		String[] cols = { "会员编号", "收银员编号", "产品编号", "数量", "单价", "商品折扣", "销售时间", "总价格" };
		final DefaultTableModel model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_2 = new JLabel("会员编号:");
		lblNewLabel_2.setBounds(100, 10, 70, 15);
		contentPane.add(lblNewLabel_2);

		CusNo = new JTextField();
		CusNo.setBounds(180, 7, 89, 21);
		contentPane.add(CusNo);
		CusNo.setColumns(10);



		JLabel lblNewLabel_4 = new JLabel("收银台编号:");
		lblNewLabel_4.setBounds(100, 41, 70, 15);
		contentPane.add(lblNewLabel_4);

		caNo = new JTextField();
		caNo.setColumns(10);
		caNo.setBounds(180, 39, 89, 21);
		contentPane.add(caNo);
		
		
		JButton Jerase = new JButton("清空查询记录");	
		Jerase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
			}
		});
		Jerase.setBounds(460, 6, 180, 23);
		contentPane.add(Jerase);
		
		JButton Jback = new JButton("返回管理界面");
		Jback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueryHistory.this.dispose();
				RootAdministrator r = new RootAdministrator();
				r.setVisible(true);
			}
		});
		Jback.setBounds(460, 39, 180, 23);
		contentPane.add(Jback);


		JButton btnNewButton = new JButton("查询会员");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (CusNo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入会员编号", "错误提示",
							JOptionPane.ERROR_MESSAGE);
				} 
				 else {
					HistoryService hs = new HistoryService();
					String customerNo= CusNo.getText();

					HistoryInfo hi = new HistoryInfo();

					table = new JTable(model);

					List<HistoryInfo> rows = hs.getHistoryInfoByCustomerNoService(customerNo);
					for (HistoryInfo his : rows) {
						Object[] row = {customerNo , his.getcaNo(),his.getProductNo(),his.getCount(),
								his.getPrice(), his.getDiscount(), his.getTime(),his.getCount()*his.getPrice()*his.getDiscount()*0.9};
						model.addRow(row);
					}
						CusNo.setText("");
						caNo.setText("");				
			}}
		});
		btnNewButton.setBounds(300, 6, 120, 23);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton1 = new JButton("查询收银台");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (caNo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "请输入收银台编号", "错误提示",
							JOptionPane.ERROR_MESSAGE);
				} 
				 else {
					HistoryService hs = new HistoryService();
					String CaNo= caNo.getText();

					List<HistoryInfo> rows = hs.getHistoryInfoBycaNoService(CaNo);
					for (HistoryInfo his : rows) {
						Object[] row = {his.getCustomerNo() ,CaNo,his.getProductNo(),his.getCount(),
								his.getPrice(), his.getDiscount(), his.getTime(),his.getCount()*his.getPrice()*his.getDiscount()*0.9};
						model.addRow(row);
					}
						caNo.setText("");
					
				
			}}
		});
		btnNewButton1.setBounds(300, 39, 120, 23);
		contentPane.add(btnNewButton1);	
	}
}
