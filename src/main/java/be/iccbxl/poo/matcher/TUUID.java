package be.iccbxl.poo.matcher;

import java.util.UUID;

import org.simpleframework.xml.transform.Transform;



public class TUUID implements Transform<UUID>{

	@Override
	public UUID read(String value) throws Exception {
		return UUID.fromString(value);
	}

	@Override
	public String write(UUID value) throws Exception {
		return value.toString();
	}

}

