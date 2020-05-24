package be.iccbxl.poo.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.simpleframework.xml.ElementList;
import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;
import be.iccbxl.poo.exception.AlreadyEnregistredException;
import be.iccbxl.poo.exception.NotEnregistredException;

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
		List<Person> listP = findByPerson("id", uuid.toString());
		if(listP.size() > 0)
			return people.remove(listP.get(0));
		return false;
	}
	
	public boolean delete(Person p) {
		return people.remove(p);
	}

	public boolean deleteBook(UUID uuid) {
		List<Book> listB = findByBook("id", uuid.toString());
		if(listB.size() > 0)
			return books.remove(listB.get(0));
		return false;
	}

	public boolean delete(Book b) {
		return books.remove(b);
	}

	public void save(Person p) {
		if(p == null) {
			throw new NullPointerException("La personne ne doit pas être nulle");
		}
		if(isEnregistred(p)) {
			throw new AlreadyEnregistredException("La personne est déja enregistrée");
		}
		people.add(p);
	}

	public void save(Book b) {
		if(b == null) {
			throw new NullPointerException("Le livre ne doit pas être null");
		}
		if(isEnregistred(b)) {
			throw new AlreadyEnregistredException("Le livre est déja enregistré");
		}
		books.add(b);
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
		} else if(prop.equals("id")) {
			for(Person p : people) {
				if(p.getId().toString().equals(val)) {
					personFound.add(p);
				}
			}
		}
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
				}
			}
		} else if(prop.equals("author")) {
			for(Book b : books) {
				if(b.getAuthor().toLowerCase().equals(val)) {
					bookFound.add(b);
				}
			}
		}
		return bookFound;
	}

	public List<Person> getPeople() {
		return people;
	}

	public List<Book> getBooks() {
		return books;
	}
	
	public boolean borrows(Person p, Book b) {
		if(p == null || b == null) {
			throw new NullPointerException("Le livre et la personne ne doivent pas être nulls");
		}
		if(!isEnregistred(p) || !isEnregistred(b)) {
			throw new NotEnregistredException("Le livre et la personne doivent être présentes dans la base de donnée");
		}
		
		if(p.canBorrows() && !b.isBorrowed()) {
			p.borrows(b);
			b.borrows(p);
			return true;
		}
		return false;
	}
	
	public boolean returns(Person p, Book b) {
		if(p == null || b == null) {
			throw new NullPointerException("Le livre et la personne ne doivent pas être nulls");
		}
		if(!isEnregistred(p) || !isEnregistred(b)) {
			throw new NotEnregistredException("Le livre et la personne doivent être présentes dans la base de donnée");
		}
		
		if(p.getBooks().contains(b.getId()) && b.getBorrowerID() != null && b.getBorrowerID().equals(p.getId())) {
			p.unsetBorrow(b);
			b.unsetBorrows();
			return true;
		}
		return false;
	}
	
	public void update(Person p, String name, byte maxBooks) {
		p.updatePerson(name, maxBooks);
	}
	
	public void update(Book b, String title, String author, short totalPages, byte loanPeriod, double rentalPrice, String language) {
		b.updateBook(title, author, totalPages, loanPeriod, rentalPrice, language);
	}
	
	public List<Book> getBooksLoaned(Person p){
		List<Book> booksLoaned = new ArrayList<Book>();
		for(UUID id : p.getBooks()) {
			booksLoaned.addAll(findByBook("id", id.toString()));
		}
		return booksLoaned;
	}
	
	public boolean isEnregistred(Person p) {
		for(Person pers : people) {
			if(pers.equals(p)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isEnregistred(Book b) {
		for(Book book : books) {
			if(book.equals(b)) {
				return true;
			}
		}
		return false;
	}
}
