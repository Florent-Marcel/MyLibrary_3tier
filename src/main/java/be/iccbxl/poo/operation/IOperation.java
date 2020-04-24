package be.iccbxl.poo.operation;

import java.util.List;

import be.iccbcl.poo.entities.Book;
import be.iccbcl.poo.entities.Person;

public interface IOperation {
	//Data access
	public List<Person> getMembres();
	public List<Book> getBooks();
	
	public boolean register(Person P);
	public boolean register(Book b);
	
	public boolean unRegister(Person P);
	public boolean unRegister(Book b);
	
	public boolean update(Person P);
	public boolean update(Book b);
	
	//Logic
	public int computeRemainingDays(Book b);
}
