package be.iccbxl.poo.entities;

import java.util.UUID;

import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;



public class CustomUUID implements Transform<UUID>{

	@Override
	public UUID read(String value) throws Exception {
		return UUID.fromString(value);
	}

	@Override
	public String write(UUID value) throws Exception {
		return value.toString();
	}

}

