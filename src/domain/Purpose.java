package domain;

import java.io.Serializable;

public class Purpose implements Category,Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String subCategory;
	
	public Purpose() {
		
	}
	
	public Purpose(String n) {
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
