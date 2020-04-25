package be.iccbxl.poo.entities;

import java.time.LocalDate;

import org.simpleframework.xml.transform.Transform;

public class TLocalData implements Transform<LocalDate>{

	@Override
	public LocalDate read(String value) throws Exception {
		return LocalDate.parse(value);
	}

	@Override
	public String write(LocalDate value) throws Exception {
		return value.toString();
	}

}
