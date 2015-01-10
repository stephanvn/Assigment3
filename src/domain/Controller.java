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
		
		//Setup domain
		Context c = (Context)factory.getBean("contextBean");
		
		//Setup UI
		new MainFrame(c);		
	}

}
