package be.iccbxl.poo.data;

import java.util.List;
import java.util.UUID;

import be.iccbcl.poo.entities.Book;
import be.iccbcl.poo.entities.Person;

public interface IData {

	public List<Person> findAllPerson();
	public List<Person> findByPerson(String property, String value);
	
	public List<Book> findAllBook();
	public List<Book> findByBook(String property, String value);
	
	
	public boolean deletePerson(UUID uuid);
	public boolean deletePerson(Person p);
	
	public boolean deleteBook(UUID uuid);
	public boolean deleteBook(Book b);
	
	public int saveAll();
	
	public boolean savePerson(Person p);
	public boolean saveBook(Book b);
	
	public boolean updatePerson(Person p);
	public boolean updateBook(Book b);
}
