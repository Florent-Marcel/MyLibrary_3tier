package be.iccbxl.poo.data;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import be.iccbxl.poo.entities.MyMatcher;

public class DataInitXml {
	private static  String file = "data\\save.xml";
	
	
	public static Data dataInit() {
		Serializer serial = new Persister(new MyMatcher());
		File f = new File(file);
		
		try {
			return serial.read(Data.class, f);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
