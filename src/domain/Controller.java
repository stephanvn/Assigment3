package domain;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import userinterface.MainFrame;

public class Controller {

	public static void main(String[] args) {
		Context c = new Context();
		
		Category p1 = new Purpose("Creational"); c.addCategory(p1);
		Category p2 = new Purpose("Structural"); c.addCategory(p2);
		Category p3 = new Purpose("Behavioral"); c.addCategory(p3);
		
		Category s1 = new Scope("Class"); c.addCategory(s1);
		Category s2 = new Scope("Object"); c.addCategory(s2);
		
	
		File folder = new File("objecten");
		File[] listOfFiles = folder.listFiles();
		
		
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	String ext = listOfFiles[i].getName().substring(listOfFiles[i].getName().lastIndexOf('.'));
	      if (listOfFiles[i].isFile() && ext.equals(".obj")) {
	        try(
	  	  	      InputStream file = new FileInputStream("objecten/"+listOfFiles[i].getName());
	  	  	      InputStream buffer = new BufferedInputStream(file);
	  	  	      ObjectInput input = new ObjectInputStream (buffer);
	  	  	    ){
	        		Pattern p = (Pattern)input.readObject();
	        		c.addPattern(p);	        		
	  	  	    }
	  	  	    catch(Exception ex){
	  	  	    	ex.printStackTrace();
	  	  	    }
	      } else if (listOfFiles[i].isDirectory()) {
	        System.out.println("Directory " + listOfFiles[i].getName());
	      }
	    }	    
		
		new MainFrame(c);		
	}

}
