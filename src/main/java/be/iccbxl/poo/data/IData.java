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
	
	public void save(Person p);
	public void save(Book b);
	
	public List<Person> getPeople();
	public List<Book> getBooks();
	
	public void dataLoad();
	
	public void dataWrite();
	
	public boolean borrows(Person p, Book b);
	
	public boolean returns(Person p, Book b);
	
	public void update(Person p, String name, byte maxBooks);
	public void update(Book b, String title, String author, short totalPages, byte loanPeriod, double rentalPrice, String language);
	
	public List<Book> getBooksLoaned(Person p);
	
	public boolean isEnregistred(Person p);
	public boolean isEnregistred(Book b);
}
