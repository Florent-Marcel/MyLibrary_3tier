package be.iccbxl.poo.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class Person {
	
	@Attribute(name="id")
	protected UUID id;
	
	@Attribute(name="name")
	private String name;
	
	@Attribute(name="maxBooks")
	private byte maxBooks;
	
	@Attribute(name="registrationDate")
	private LocalDate registrationDate;
	
	@ElementList(inline = true, entry = "bookRef", name="books", required=false)
	private ArrayList<UUID> books;
	
	public Person(UUID id, String name, byte maxBooks, LocalDate registrationDate, ArrayList<UUID> books) {
		this.id = id;
		this.name = name;
		this.maxBooks = maxBooks;
		this.registrationDate = registrationDate;
		this.books = books;
	}
	
	public Person(UUID id, String name) {
		this(id, name, (byte)3, LocalDate.now(), new ArrayList<UUID>());
	}
	
	public Person() {
		// pour sérialiser
		if(books == null) {
			books = new ArrayList<UUID>();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getMaxBooks() {
		return maxBooks;
	}

	public void setMaxBooks(byte maxBooks) {
		this.maxBooks = maxBooks;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public UUID getId() {
		return id;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public ArrayList<UUID> getBooks() {
		return books;
	}

	@Override
	public String toString() {
		return "Nom: " + name + "\tInscrit le: " + registrationDate + "\tNumber of books borrowed: " + books.size();
	}

	public void borrows(Book book) {
		this.books.add(book.getId());
		//book.setBorrower(this);
		//book.borrowingDate = LocalDate.now();
	}

	public void unsetBorrow(Book book) {
		this.books.remove(book.getId());
		//book.setBorrower(null);
		//book.borrowingDate = null;
	}
	
	public void updatePerson(Person p) {
		this.name = p.getName();
		this.maxBooks = p.getMaxBooks();
	}
	
	public void updatePerson(String name, byte maxBooks) {
		this.name = name;
		this.maxBooks = maxBooks;
	}
	
	public boolean canBorrows() {
		return books.size() < maxBooks;
	}
	
	public boolean isBorrower() {
		return books.size() > 0;
	}
	
}