package be.iccbxl.poo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import be.iccbxl.poo.config.AppConfiguration;
import be.iccbxl.poo.presentation.IPresentation;
import be.iccbxl.poo.presentation.Presentation;


public class App{
    public static void main( String[] args ) 
    {
        System.out.println( "Hello World!" );
        
        FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("src\\main\\java\\be\\iccbxl\\poo\\config\\AppXMLConfiguration.xml");
        IPresentation presentation = ctx.getBean(IPresentation.class);
        
        presentation.run();
        
        System.out.println( "Au revoir !" );
    }


}
