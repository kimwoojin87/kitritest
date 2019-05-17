package com.kitri.service;

import java.util.List;

import com.kitri.dao.ProductDAO;
import com.kitri.dto.Product;

public class ProductService {//완충역활레이어.목적은 use케이스별로 작성하는것.db와의 일처리는 dao에서 마무리를 지어야함.서비스에선 그 dao를 호출만 해야함.
	private ProductDAO dao;
	
	public ProductService() {
		dao = new ProductDAO();
	}
	
	public List<Product>findAll(){
		return dao.selectAll();
	}
	
	public Product findByNo(String prodNo) {
		return dao.SelectByNo(prodNo);
	}
	
}
