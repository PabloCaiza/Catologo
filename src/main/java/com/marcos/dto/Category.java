package com.marcos.dto;

import java.util.List;

public class Category {
	private String gender;
	private List<String> types;
	
	
	public Category(String gender, List<String> types) {
		super();
		this.gender = gender;
		this.types = types;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public List<String> getTypes() {
		return types;
	}


	public void setTypes(List<String> types) {
		this.types = types;
	}
	
	
	
	
	

}
