package com.kitri.service;

import java.util.List;

import com.kitri.dao.ProductDAO;
import com.kitri.dto.Product;

public class ProductService {//완충역활레이어
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
