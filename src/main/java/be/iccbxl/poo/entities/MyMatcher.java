package be.iccbxl.poo.entities;

import java.time.LocalDate;
import java.util.UUID;

import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;

public class MyMatcher implements Matcher {
    @Override
    @SuppressWarnings("unchecked")
    public Transform match(Class type) throws Exception {
        if (type.equals(UUID.class))
            return new CustomUUID();
        if(type.equals(LocalDate.class))
        	return new CustomLocalData();
        return null;
    }
}