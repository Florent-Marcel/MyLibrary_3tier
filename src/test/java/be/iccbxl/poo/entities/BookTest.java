package be.iccbxl.poo.entities;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class BookTest {
	String title = "Je suis une legende";
	String author = "Richard Matheson";
	short nbPages = 200;
	String language = "Francais";
	Book book;
	UUID idBook;

	@Before
	public void setUp() throws Exception {
		idBook = UUID.randomUUID();
		book = new Book(idBook, title, author, nbPages, language);
	}

	@Test
	public void testBook() {
		assertNotNull(book);
		
		assertEquals(title, book.getTitle());
		assertEquals(author, book.getAuthor());
		assertEquals(nbPages, book.getTotalPages());
		assertEquals(language, book.getLanguage());
		assertEquals(idBook, book.getId());
	}
	
	@Test
	public void testComputeRemainingDays() {
		book.setBorrowingDate(LocalDate.now());
		
		assertEquals(14,book.computeRemainingDays());
	}
	
	@Test
	public void testComputeRemainingDaysDepassed() {
		book.setBorrowingDate(LocalDate.now().minusDays(24));
		
		assertEquals(-10,book.computeRemainingDays());
	}

}
