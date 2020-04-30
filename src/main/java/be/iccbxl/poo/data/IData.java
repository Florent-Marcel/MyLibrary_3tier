package be.iccbxl.poo.data;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;
import be.iccbxl.poo.matcher.MyMatcher;

public interface IData {
	
	public List<Person> findByPerson(String property, String value);
	public List<Book> findByBook(String property, String value);
	
	public List<Person> loadPeople();
	public List<Book> loadBooks();
	
	public boolean deletePerson(UUID uuid);
	public boolean delete(Person p);
	
	public boolean deleteBook(UUID uuid);
	public boolean delete(Book b);
	
	public boolean save(Person p);
	public boolean save(Book b);
	
	public boolean update(Person p);
	public boolean update(Book b);
	
	public List<Person> getPeople();
	public List<Book> getBooks();
	
	public Data dataLoad();
	
	public void dataWrite();
	
	//public void writeXml();
}
