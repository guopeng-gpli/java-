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
import javax.swing.JTable;
import javax.swing.JButton;

import entity.Customers;

import service.CustomerService;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerManagement extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerManagement frame = new CustomerManagement();
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
	public CustomerManagement() {
		this.setTitle("顾客信息管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 450, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 414, 165);
		contentPane.add(scrollPane);
		
		String[] cols={"会员编号","会员用户名","会员联系方式","会员积分"};
		final DefaultTableModel model = new DefaultTableModel(cols,0);
		table = new JTable(model);
		CustomerService cs = new CustomerService();
		List<Customers> rows = cs.getAllCustomersService();
		for (Customers customers : rows) {
			Object[] row = {customers.getCustomerNo(),customers.getCustomerName(),customers.getPhone(),customers.getScore()};
			model.addRow(row);
		}
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("新增");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerManagement.this.dispose();
				NewCustomer nc = new NewCustomer();
				nc.setVisible(true);
			}
		});
		button.setBounds(214, 25, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("删除");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex<0){
					JOptionPane.showMessageDialog(CustomerManagement.this, "请选择要删除的行","错误提示",JOptionPane.ERROR_MESSAGE);
				}else{
					String cstNo = (String) model.getValueAt(rowIndex, 0);
					if(JOptionPane.showConfirmDialog(CustomerManagement.this, "确定删除第"+(rowIndex+1)+"行?")==0){
						CustomerService cs = new CustomerService();
						if(cs.deleteCustomersService(cstNo)){
							JOptionPane.showMessageDialog(CustomerManagement.this, "删除成功!");
							CustomerManagement.this.dispose();
							CustomerManagement frame = new CustomerManagement();
							frame.setVisible(true);
						}
					}
				}
			}
		});
		button_1.setBounds(331, 25, 93, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("返回主管理员界面");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerManagement.this.dispose();
				RootAdministrator ra = new RootAdministrator();
				ra.setVisible(true);
			}
		});
		button_2.setBounds(279, 233, 145, 26);
		contentPane.add(button_2);
	}
}
