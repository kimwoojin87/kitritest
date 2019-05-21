package com.kitri.dto;

public class Customer {
	//객체지향의 목적.
	//재사용성을 높이자. 높이려면 여러 사람이 쓸 수 있어야함.
	//매개변수의 이름도 의미있는 이름을 사용하면 직관력이 높아짐.
	//JavaBeans의 
	private String id;
	private String pass;
	private String name;
	public Customer() {
		super();
	}
	public Customer(String id, String pass, String name) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", pass=" + pass + ", name=" + name + "]";
	}
	
	
	
	
}
