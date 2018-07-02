package service;

import dao.SaleHistoryDao;
import entity.SaleHistory;

public class SaleHistoryService {
	public boolean newSaleHistoryService(SaleHistory saleHistory){
		SaleHistoryDao shd = new SaleHistoryDao();
		return shd.newSaleHistoryDao(saleHistory);
	}
}
