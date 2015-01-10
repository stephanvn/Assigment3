package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Context {
	private ArrayList<Category> purposes;
	private ArrayList<Category> scopes;
	private ArrayList<Pattern> patterns;

	public Context() {
		purposes = new ArrayList<Category>();
		scopes = new ArrayList<Category>();
		patterns = new ArrayList<Pattern>();
	}
	
	public void addCategory(ArrayList<Category> c) {
		for (Category categories : c){
			if(categories instanceof Purpose) {
				purposes.add(categories);
			}
			else if(categories instanceof Scope) {
				scopes.add(categories);
			}
		}
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
	
	public void addPatterns(ArrayList<Pattern> p) {
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
	
	public void checkOldPattern(Pattern p) {
		for(Pattern pa : patterns) {
			if(pa.getName().equals(p.getName())) {
				patterns.remove(pa);
				break;
			}
		}
	}
	
	public boolean parseToPattern(String path,String file,String ext) {
		boolean b = true;
		if(ext.equals(".txt")) {			
			try {
				FileReader fr = new FileReader(path); 
				BufferedReader br = new BufferedReader(fr); 
				ArrayList<String> strings = new ArrayList<String>();
				String s; 
				while((s = br.readLine()) != null) { 
					strings.add(s);
				} 
				fr.close(); 
				Category pur = searchCategory(strings.get(6));				
				Category scp = searchCategory(strings.get(7));
				if(strings.get(0).isEmpty() || strings.get(1).isEmpty() || strings.get(2).isEmpty() || strings.get(3).isEmpty() || strings.get(4).isEmpty() || strings.get(5).isEmpty() || pur==null || scp==null) {
					throw new Exception();
				}
				Pattern p = new Pattern(strings.get(0),strings.get(1),strings.get(2),strings.get(3),strings.get(4),strings.get(5),pur,scp);
				checkOldPattern(p);
				patterns.add(p);
				writeObject(p);
			}
			catch(Exception e) {
				b = false;
			}
			
		}
		else if(ext.equals(".xml")) {
			try {
				File fXmlFile = new File(path);
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);			 
			
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("pattern");
				for (int temp = 0; temp < nList.getLength(); temp++) {					 
					Node nNode = nList.item(temp); 			 
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {			 
						Element eElement = (Element) nNode;						
						Category pur = searchCategory(eElement.getElementsByTagName("purpose").item(0).getTextContent());
						Category scp = searchCategory(eElement.getElementsByTagName("scope").item(0).getTextContent());
						if(pur==null || scp==null) {
							throw new Exception();
						}
						Pattern p = new Pattern(eElement.getElementsByTagName("name").item(0).getTextContent(),eElement.getElementsByTagName("context").item(0).getTextContent(),eElement.getElementsByTagName("problem").item(0).getTextContent(),eElement.getElementsByTagName("solution").item(0).getTextContent(),eElement.getElementsByTagName("diagram").item(0).getTextContent(),eElement.getElementsByTagName("consequences").item(0).getTextContent(),pur,scp);
						checkOldPattern(p);
						patterns.add(p);
						writeObject(p);
					}
				}
			}
			catch(Exception e) {
				b = false;
			}
		}
		return b;
	}
	
	public void writeObject(Pattern p) {
		try{
		 
		FileOutputStream fos = new FileOutputStream("objecten/"+p.getName()+".obj");
		ObjectOutputStream oos = new ObjectOutputStream(fos);   
		oos.writeObject(p);
		oos.close();
 
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
	}
}
