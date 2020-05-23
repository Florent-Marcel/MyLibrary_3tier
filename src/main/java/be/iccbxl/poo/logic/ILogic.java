package be.iccbxl.poo.logic;

import java.util.List;

import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;

public interface ILogic {
	//Data access
	
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
	
	public void save();
	
	public boolean borrows(Person p, Book b);
	
	public boolean returns(Person p, Book b);
	
	public void update(Person p, String name, byte maxBooks);
	
	public void upadte(Book b, String title, String author, short totalPages, byte loanPeriod, double rentalPrice, String language);
	
	//Logic
	public int computeRemainingDays(Book b);
	
	
}
