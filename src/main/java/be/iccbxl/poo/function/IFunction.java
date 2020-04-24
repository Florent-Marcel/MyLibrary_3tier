package be.iccbxl.poo.function;

import java.util.List;

import be.iccbcl.poo.entities.Book;
import be.iccbcl.poo.entities.Person;

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
	
	//Logic
	public int computeRemainingDays(Book b);
	
	public boolean add(Person p);
	public boolean add(Book b);
	
	public List<Person> getPeople();
	public List<Book> getBooks();
}