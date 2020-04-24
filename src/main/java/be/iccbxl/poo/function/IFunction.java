package be.iccbxl.poo.function;

import java.util.List;

import be.iccbcl.poo.entities.Book;
import be.iccbcl.poo.entities.Person;

public interface IFunction {
	//Data access
	public List<Person> getMembres();
	public List<Book> getBooks();
	
	public boolean register(Person p);
	public boolean register(Book b);
	
	public boolean unRegister(Person p);
	public boolean unRegister(Book b);
	
	public boolean update(Person p);
	public boolean update(Book b);
	
	//Logic
	public int computeRemainingDays(Book b);
}
