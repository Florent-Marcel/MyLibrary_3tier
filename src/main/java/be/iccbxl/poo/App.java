package be.iccbxl.poo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.iccbxl.poo.config.AppConfiguration;
import be.iccbxl.poo.presentation.IPresentation;


public class App implements CommandLineRunner{
    public static void main( String[] args ) 
    {
    	
        System.out.println( "Bonjour!" );
        
        // Spring with annotations
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
        
        //Spring with XML
        //ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("be\\iccbxl\\poo\\config\\AppXMLConfiguration.xml");
        
        IPresentation presentation = ctx.getBean(IPresentation.class);
        
        presentation.run();
        
        ctx.close();
        System.out.println( "Au revoir !" );
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
