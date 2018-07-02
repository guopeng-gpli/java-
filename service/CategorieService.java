package service;

import dao.CategorieDao;

public class CategorieService {
	public String getCategoryNameService(String categoryId){
		CategorieDao cd = new CategorieDao();
		return cd.getCategoryNameDao(categoryId);
	}
}
