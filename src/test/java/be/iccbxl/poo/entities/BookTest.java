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
	
	@Test
	public void testComputeFineNoFine() {
		book.setBorrowingDate(LocalDate.now());
		double fine = book.computeFine();
		double expected = 0;
		
		assertEquals(expected, fine, 0);
	}
	
	@Test
	public void testComputeFineNoFine0DaysRemaining() {
		book.setBorrowingDate(LocalDate.now().minusDays(14));
		double fine = book.computeFine();
		double expected = 0;
		
		assertEquals(expected, fine, 0);
	}
	
	@Test
	public void testComputeFineWithFine1Week() {
		book.setBorrowingDate(LocalDate.now().minusDays(15));
		double fine = book.computeFine();
		double expected = 1.5;
		
		assertEquals(expected, fine, 0);
	}
	
	@Test
	public void testComputeFineWithFine3Weeks() {
		book.setBorrowingDate(LocalDate.now().minusDays(28));
		double fine = book.computeFine();
		double expected = 2.5;
		
		assertEquals(expected, fine, 0);
	}

}
