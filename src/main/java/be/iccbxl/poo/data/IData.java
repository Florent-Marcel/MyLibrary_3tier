package be.iccbxl.poo.data;

import java.util.List;
import java.util.UUID;

import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;

public interface IData {
	
	public List<Person> findByPerson(String property, String value);
	public List<Book> findByBook(String property, String value);
	
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
	
	public void dataLoad();
	
	public void dataWrite();
	
	public boolean borrows(Person p, Book b);
	
	public boolean returns(Person p, Book b);
	
	public void update(Person p, String name, byte maxBooks);
	
	//public void writeXml();
}
