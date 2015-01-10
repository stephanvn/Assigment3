package domain;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import userinterface.MainFrame;

public class Controller {

	public static void main(String[] args) {
		Resource resource=new ClassPathResource("applicationContext.xml");
		BeanFactory factory=new XmlBeanFactory(resource);
		
		Context c = (Context)factory.getBean("contextBean");
		
		Category p1 = new Purpose("Creational"); c.addCategory(p1);
		Category p2 = new Purpose("Structural"); c.addCategory(p2);
		Category p3 = new Purpose("Behavioral"); c.addCategory(p3);
		
		Category s1 = new Scope("Class"); c.addCategory(s1);
		Category s2 = new Scope("Object"); c.addCategory(s2);
		
		new MainFrame(c);		
	}

}
