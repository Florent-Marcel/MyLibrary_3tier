package be.iccbxl.poo.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;

public class Data implements IData {
	List<Person> people;
	List<Book> books;
	private String filename;
	File f;
	
	public Data(String filename) {
		this.filename = filename;
		f = new File(filename);
		people = new ArrayList<Person>();
		books = new ArrayList<Book>();
		
		people.add(new Person(UUID.randomUUID(), "Gui"));
		people.add(new Person(UUID.randomUUID(), "Anis"));
		people.add(new Person(UUID.randomUUID(), "Max"));
		
		books.add(new Book(UUID.randomUUID(), "Je suis une légende", "Richard Matheson", (short)200, "Français"));
		books.add(new Book(UUID.randomUUID(), "Des fleurs pour Algernon", "Daniel Keyes", (short)400, "Français"));
		
		if(f.exists()) {
			/*
			this.people = findAllPersons();
			this.books = findAllBooks();
			*/
		}
			
	}

	public boolean deletePerson(UUID uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(Person p) {
		
		// TODO delete in files + manage errors
		return people.remove(p);
	}

	public boolean deleteBook(UUID uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(Book b) {
		// TODO delete in files + manage errors
		return books.remove(b);
	}

	public boolean save(Person p) {
		// TODO add in files + manage errors
		return people.add(p);
	}

	public boolean save(Book b) {
		// TODO delete in files + manage errors
		return books.add(b);
	}

	public boolean update(Person p) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(Book b) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Person> loadPeople() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> loadBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Person> findByPerson(String property, String value) {
		String prop = property.toLowerCase();
		String val = value.toLowerCase();
		List<Person> personFound = new ArrayList<Person>();
		
		if(prop.equals("name")){
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
		String prop = property.toLowerCase();
		String val = value.toLowerCase();
		List<Book> bookFound = new ArrayList<Book>();
		
		if(prop.equals("title")){
			for(Book b : books) {
				if(b.getTitle().toLowerCase().equals(val)) {
					bookFound.add(b);
				}
			}
		}
		// TODO manage others properties
		return bookFound;
	}

	public List<Person> getPeople() {
		return people;
	}

	public List<Book> getBooks() {
		return books;
	}
}
