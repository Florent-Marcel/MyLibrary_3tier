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
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteBook(UUID uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(Book b) {
		// TODO Auto-generated method stub
		return false;
	}

	public int saveAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean save(Person p) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean save(Book b) {
		// TODO Auto-generated method stub
		return false;
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

}
