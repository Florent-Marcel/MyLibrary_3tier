package be.iccbxl.poo;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.iccbxl.poo.config.AppConfiguration;
import be.iccbxl.poo.presentation.IPresentation;
import be.iccbxl.poo.presentation.Presentation;


public class App implements CommandLineRunner{
    public static void main( String[] args ) 
    {
    	
        System.out.println( "Bonjour!" );
        
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
        //ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("be\\iccbxl\\poo\\config\\AppXMLConfiguration.xml");
        IPresentation presentation = ctx.getBean(IPresentation.class);
        
        presentation.run();
        
        System.out.println( "Au revoir !" );
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
