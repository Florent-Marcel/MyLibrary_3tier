package be.iccbxl.poo.data;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import be.iccbxl.poo.entities.MyMatcher;

public class DataXml {
	private static  String file = "data\\save.xml";
	private static Serializer serial = new Persister(new MyMatcher());
	private static File f = new File(file);
	
	
	public static Data dataLoad() {
		try {
			return serial.read(Data.class, f);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void dataWrite(IData data) {
		try {
			serial.write(data, f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
