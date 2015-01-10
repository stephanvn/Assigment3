package domain;

import java.util.ArrayList;

public class Context {
	private ArrayList<Category> purposes;
	private ArrayList<Category> scopes;
	private ArrayList<Pattern> patterns;

	public Context() {
		purposes = new ArrayList<Category>();
		scopes = new ArrayList<Category>();
		patterns = new ArrayList<Pattern>();
	}
	
	public void setPurposes(ArrayList<Category> c) {
		purposes = c;
	}
	
	public void setScopes(ArrayList<Category> c) {
		scopes = c;
	}
	
	public String[] getCategoryString(char c) {
		String[] array = null;
		if(c=='p') {
			array = new String[purposes.size()];
			int counter = 0;
			for(Category p : purposes) {
				array[counter] = p.getName();
				counter++;
			}
		}
		else if(c=='s') {
			array = new String[scopes.size()];
			int counter = 0;
			for(Category s : scopes) {
				array[counter] = s.getName();
				counter++;
			}			
		}
		return array;
	}
	
	public ArrayList<Category> getCategories(char c) {
		ArrayList<Category> list = null;
		if(c=='p') {
			list = purposes;
		}
		else if(c=='s') {
			list =  scopes;
		}
		return list;
	}
	
	public Category searchCategory(String s) {
		Category c = null;
		for(Category ca: purposes) {
			if(ca.getName().equals(s)) {
				c = ca;
			}
		}
		for(Category ca: scopes) {
			if(ca.getName().equals(s)) {
				c = ca;
			}
		}
		return c;
	}	
	
	public void setPatterns(ArrayList<Pattern> p) {
		patterns = p;
	}
	
	public void removePattern(Pattern p) {
		patterns.remove(p);
	}
	
	public String[] getPatternsString() {
		String[] array = new String[patterns.size()];
		int counter = 0;
		for(Pattern p : patterns) {
			array[counter] = p.getName();
			counter++;
		}
		return array;
	}
	
	public ArrayList<Pattern> getPatterns() {
		return patterns;
	}
	
	public Pattern searchPattern(String s) {
		Pattern p = null;
		for(Pattern pa : patterns) {
			if(pa.getName().equals(s)) {
				p = pa;
			}
		}
		return p;
	}
	
}
