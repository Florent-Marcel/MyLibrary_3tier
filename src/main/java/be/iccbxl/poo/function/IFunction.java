package be.iccbxl.poo.function;

import java.util.List;

import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;

public interface IFunction {
	//Data access
	public List<Person> loadMembres();
	public List<Book> loadBooks();
	
	public boolean register(Person p);
	public boolean register(Book b);
	
	public boolean unRegister(Person p);
	public boolean unRegister(Book b);
	
	public boolean update(Person p);
	public boolean update(Book b);
	
	public List<Person> getPeople();
	public List<Book> getBooks();
	
	public List<Person> findByPerson(String property, String value);
	
	public List<Book> findByBook(String property, String value);
	
	//Logic
	public int computeRemainingDays(Book b);
	
	public void save();
}
