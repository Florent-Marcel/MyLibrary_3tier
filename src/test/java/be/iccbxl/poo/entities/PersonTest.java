package be.iccbxl.poo.entities;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class PersonTest {
	Person person;
	String name = "Jean";
	UUID idperson;
	Book book;

	@Before
	public void setUp() throws Exception {
		idperson = UUID.randomUUID();
		person = new Person(idperson, name);
		
		String title = "Je suis une legende";
		String author = "Richard Matheson";
		short nbPages = 200;
		String language = "Francais";
		UUID idBook = UUID.randomUUID();
		book = new Book(idBook, title, author, nbPages, language);
	}

	@Test
	public void testPerson() {
		assertNotNull(person);
		
		assertEquals(name, person.getName());
		assertNotNull(person.getBooks());
		assertEquals(0, person.getBooks().size());
		assertEquals(3, person.getMaxBooks());
		assertEquals(idperson, person.getId());
		assertEquals(LocalDate.now(), person.getRegistrationDate());
		
	}

	@Test
	public void testBorrows() {
		person.borrows(book);
		
		assertEquals(book, person.getBooks().get(0));
		assertEquals(person.getId(), book.getBorrowerID());
		assertEquals(LocalDate.now(), book.getBorrowingDate());
	}

	@Test
	public void testUnsetBorrow() {
		person.borrows(book);
		
		person.unsetBorrow(book);
		assertEquals(0, person.getBooks().size());
	}
}
