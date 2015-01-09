package domain;

import java.io.Serializable;

public class Scope implements Category,Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String subCategory;

	public Scope() {
		
	}
	
	public Scope(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setCategory(String s) {
		subCategory = s;
		
	}

}
