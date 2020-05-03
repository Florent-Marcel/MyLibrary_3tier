package be.iccbxl.poo.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.simpleframework.xml.ElementList;
import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;

public abstract class DataFile implements IData{
	@ElementList(inline = true, entry = "person", required=false)
	protected List<Person> people;
	
	@ElementList(inline = true, entry = "book", required=false)
	protected List<Book> books;
	
	public DataFile() {
		if(books == null) {
			books = new ArrayList<Book>();
		}
		if(people == null) {
			people = new ArrayList<Person>();
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
		} else if(prop.equals("id")) {
			for(Book b : books) {
				if(b.getId().toString().equals(value)) {
					bookFound.add(b);
					System.out.println("ok");
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
	
	public boolean borrows(Person p, Book b) {
		if(p.canBorrows() && !b.isBorrowed()) {
			p.borrows(b);
			b.borrows(p);
			return true;
		}
		return false;
	}
	
	public boolean returns(Person p, Book b) {
		if(p == null) {
			throw new NullPointerException("Person p is null");
		} else if( b ==null) {
			throw new NullPointerException("Book b is null");
		}
		System.out.println(p.getBooks());
		System.out.println(b.getId());
		
		if(p.getBooks().contains(b.getId()) && b.getBorrowerID() != null && b.getBorrowerID().equals(p.getId())) {
			p.unsetBorrow(b);
			b.unsetBorrows();
			return true;
		}
		return false;
	}
	
}
