package be.iccbxl.poo.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;
import be.iccbxl.poo.matcher.MyMatcher;

@Root
public class DataXML extends DataFile {
	
	private Serializer serial;
	
	private String filename;
	
	private File f;
	
	public DataXML(String filename) {
		people = new ArrayList<Person>();
		books = new ArrayList<Book>();
		
		this.setFilename(filename);
		
		/*people.add(new Person(UUID.randomUUID(), "Gui"));
		people.add(new Person(UUID.randomUUID(), "Anis"));
		people.add(new Person(UUID.randomUUID(), "Max"));
		
		books.add(new Book(UUID.randomUUID(), "Je suis une légende", "Richard Matheson", (short)200, "Français"));
		books.add(new Book(UUID.randomUUID(), "Des fleurs pour Algernon", "Daniel Keyes", (short)400, "Français"));*/
	}
	
	public DataXML() {
		if(books == null) {
			books = new ArrayList<Book>();
		}
		if(people == null) {
			people = new ArrayList<Person>();
		}
	}


	public boolean deletePerson(UUID uuid) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void dataLoad() {
		try {
			 IData tmpData = serial.read(DataXML.class, f);
			 this.books = tmpData.getBooks();
			 this.people = tmpData.getPeople();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dataWrite() {
		
		try {
			serial.write(this, f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setFilename(String filename) {
		serial = new Persister(new MyMatcher());
		this.filename = filename;
		f = new File(filename);
		dataLoad();
	}
	
	/*public void writeXml() {
		try {
			serial.write(this, f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
