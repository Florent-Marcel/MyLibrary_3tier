package be.iccbxl.poo.function;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.iccbxl.poo.data.Data;
import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;

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
		return data.loadPeople();
	}

	public List<Book> loadBooks() {
		return data.loadBooks();
	}

	public boolean register(Person p) {
		return data.save(p);
	}

	public boolean register(Book b) {
		return data.save(b);
	}

	public boolean unRegister(Person p) {
		people.remove(p);
		// TODO return data.delete(p);
		return true;
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


	public List<Person> findByPerson(String property, String value) {
		String prop = property.toLowerCase();
		String val = value.toLowerCase();
		List<Person> personFound = new ArrayList<Person>();
		
		if(property.equals("name")){
			for(Person p : people) {
				if(p.getName().toLowerCase().equals(val)) {
					personFound.add(p);
				}
			}
		}
		// TODO manage others properties
		return personFound;
	}


	public List<Book> findByBook(String property, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
