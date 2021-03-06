package be.iccbxl.poo.matcher;

import java.time.LocalDate;
import java.util.UUID;

import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;

/**
 * Classe permettant de sérialiser des instances de classe ne le permettant pas.
 * @author Marcel F.
 */
public class MyMatcher implements Matcher {
	
	/**
	 * permet de sérialiser des instances de classe ne le permettant pas.
	 */
    @Override
    @SuppressWarnings("unchecked")
    public Transform match(Class type) throws Exception {
        if (type.equals(UUID.class))
            return new TUUID();
        if(type.equals(LocalDate.class))
        	return new TLocalData();
        return null;
    }
}