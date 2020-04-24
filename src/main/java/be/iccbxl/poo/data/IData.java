package be.iccbxl.poo.data;

import java.util.List;
import java.util.UUID;

import be.iccbcl.poo.entities.Book;
import be.iccbcl.poo.entities.Person;

public interface IData {

	public List<Person> findAllPersons();
	public List<Person> findByPerson(String property, String value);
	
	public List<Book> findAllBooks();
	public List<Book> findByBook(String property, String value);
	
	
	public boolean deletePerson(UUID uuid);
	public boolean delete(Person p);
	
	public boolean deleteBook(UUID uuid);
	public boolean delete(Book b);
	
	public int saveAll();
	
	public boolean save(Person p);
	public boolean save(Book b);
	
	public boolean update(Person p);
	public boolean update(Book b);
}
