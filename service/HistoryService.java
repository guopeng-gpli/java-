package service;

import java.util.List;
import dao.HistoryDao;
import entity.HistoryInfo;;


public class HistoryService {

	public List<HistoryInfo> getHistoryInfoByCustomerNoService(String CustomerNo){
		HistoryDao hd = new HistoryDao();
		return hd.getHistoryInfoByCustomerNo(CustomerNo);
	}
	
	public List<HistoryInfo> getHistoryInfoBycaNoService(String caNo){
		HistoryDao hd = new HistoryDao();
		return hd.getHistoryInfoBycaNo(caNo);
	}
	
}
