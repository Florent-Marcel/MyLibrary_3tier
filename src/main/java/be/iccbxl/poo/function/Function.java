package be.iccbxl.poo.function;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.iccbcl.poo.entities.Book;
import be.iccbcl.poo.entities.Person;
import be.iccbxl.poo.data.Data;

public class Function implements IFunction {
	Data data;
	
	private List<Person> people;
	
	private List<Book> books;
	
	public Function() {
		people = new ArrayList<Person>();
		books = new ArrayList<Book>();
		
		add(new Person(UUID.randomUUID(), "Gui"));
		add(new Person(UUID.randomUUID(), "Anis"));
		add(new Person(UUID.randomUUID(), "Max"));
	}


	public List<Person> loadMembres() {
		return data.findAllPersons();
	}

	public List<Book> loadBooks() {
		return data.findAllBooks();
	}

	public boolean register(Person p) {
		return data.save(p);
	}

	public boolean register(Book b) {
		return data.save(b);
	}

	public boolean unRegister(Person p) {
		return data.delete(p);
	}

	public boolean unRegister(Book b) {
		return data.delete(b);
	}

	public boolean update(Person p) {
		return data.update(p);
	}

	public boolean update(Book b) {
		return data.update(b);
	}

	public int computeRemainingDays(Book b) {
		return b.computeRemainingDays();
	}

	public boolean add(Person p) {
		people.add(p);
		//TODO manage duplicate
		return true;
	}

	public boolean add(Book b) {
		books.add(b);
		//TODO manage duplicate
		return true;
	}
	
	public List<Person> getPeople() {
		return people;
	}

	public List<Book> getBooks() {
		return books;
	}

}
