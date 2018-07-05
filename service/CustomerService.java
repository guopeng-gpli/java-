package service;

import java.util.List;

import dao.CustomerDao;

import entity.Customers;

public class CustomerService {
	public List<Customers> getAllCustomersService(){
		CustomerDao cd = new CustomerDao();
		return cd.getAllCustomersDao();
	}
	public int newCustomersService(Customers customer){
		CustomerDao cd = new CustomerDao();
		return cd.newCustomersDao(customer);
	}
	public boolean deleteCustomersService(String customerNo){
		CustomerDao cd = new CustomerDao();
		return cd.deleteCustomersDao(customerNo);
	}
	public boolean checkCustomerDao(int customerNo){
		CustomerDao cd = new CustomerDao();
		return cd.checkCustomerDao(customerNo);
	}
	public double getMemberDiscountByCustomerNoService(int customerNo){
		CustomerDao cd = new CustomerDao();
		return cd.getMemberDiscountByCustomerNoDao(customerNo);
	}
	public boolean updateCustomerScoreDao(int customerNo,int score){
		CustomerDao cd = new CustomerDao();
		return cd.updateCustomerScoreDao(customerNo, score);
	}
	public boolean checkCustomerNoDao(String customerNo){
		CustomerDao cd = new CustomerDao();
		return cd.checkCustomerNoDao(customerNo);
	}
}
