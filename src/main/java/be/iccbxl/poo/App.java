package be.iccbxl.poo;

import be.iccbxl.poo.presentation.IPresentation;
import be.iccbxl.poo.presentation.Presentation;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        IPresentation presentation = new Presentation();
        
        presentation.run();
        
        System.out.println( "aurevoir" );
    }
}
