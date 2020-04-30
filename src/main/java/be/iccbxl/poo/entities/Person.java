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
	
	public Person() {
		// pour sérialiser
		if(books == null) {
			books = new ArrayList<UUID>();
		}
	}
	
	public Person(UUID id, String name) {
		this.id = id;
		this.name = name;
		this.maxBooks = 3;
		this.registrationDate = LocalDate.now();
		this.books = new ArrayList<UUID>();
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
		final int maxLen = 3;
		return "Person [id=" + id + ", name=" + name + ", maxBooks=" + maxBooks + ", registrationDate="
				+ registrationDate + ", books="
				+ (books != null ? books.subList(0, Math.min(books.size(), maxLen)) : null) + "]";
	}

	public void borrows(Book book) {
		this.books.add(book.getId());
		book.setBorrower(this);
		book.borrowingDate = LocalDate.now();
	}

	public void returns(Book book) {
		this.books.remove(book.getId());
		book.setBorrower(null);
		book.borrowingDate = null;
	}
	
	public void updatePerson(Person p) {
		this.name = p.getName();
		this.maxBooks = p.getMaxBooks();
	}
	
	public boolean canBorrows() {
		return books.size() < maxBooks;
	}
	
}