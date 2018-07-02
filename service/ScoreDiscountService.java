package service;

import java.util.List;

import dao.ScoreDiscountDao;

import entity.ScoreDiscount;

public class ScoreDiscountService {
	public List<ScoreDiscount> getAllScoreDiscountService(){
		ScoreDiscountDao sdd = new ScoreDiscountDao();
		return sdd.getAllScoreDiscountDao();
	}
}
