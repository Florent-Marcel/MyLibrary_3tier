package be.iccbxl.poo.data;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;
import be.iccbxl.poo.matcher.MyMatcher;

/**
 * Représente la classe data qui gère les données et les chargents/stocks dans un fichier XML.
 * @author Marcel F.
 */
@Root
public class DataXML extends DataFile {
	
	/**
	 * permet de lire et écrire.
	 */
	private Serializer serial;
	
	/**
	 * Le chemin du fichier XML.
	 */
	private String filename;
	
	/**
	 * Le fichier XML.
	 */
	private File f;
	
	/**
	 * Le constructeur pour créer une instance de DataXML.
	 * @param filename Le chemin du fichier XML.
	 */
	public DataXML(String filename) {
		people = new ArrayList<Person>();
		books = new ArrayList<Book>();
		
		serial = new Persister(new MyMatcher());
		
		this.setFilename(filename);
		f = new File(filename);
		
		dataLoad();
	}
	
	/**
	 * Constructeur sans paramètres pour permettre plus de liberté avec Spring. a utiliser avec les setters.
	 */
	public DataXML() {
		super();
	}
	
	/**
	 * Charge les données des membres et des livres a partir des fichiers.
	 */
	public void dataLoad() {
		try {
			 IData tmpData = serial.read(DataXML.class, f);
			 this.books = tmpData.getBooks();
			 this.people = tmpData.getPeople();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * écrit les données dans les fichiers.
	 */
	public void dataWrite() {
		
		try {
			serial.write(this, f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Change le chemin du fichier XML.
	 * @param filename Le chemin du fichier XML.
	 */
	public void setFilename(String filename) {
		this.filename = filename;
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
