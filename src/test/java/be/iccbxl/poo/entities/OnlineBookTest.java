package be.iccbxl.poo.entities;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class OnlineBookTest {
	String title = "Je suis une legende";
	String author = "Richard Matheson";
	short nbPages = 200;
	String language = "Francais";
	OnlineBook onlinebook;

	@Before
	public void setUp() throws Exception {
		UUID idBook = UUID.randomUUID();
		onlinebook = new OnlineBook(idBook, title, author, nbPages, language);
	}

	@Test
	public void testOnlineBook() {
		assertNotNull(onlinebook);
		assertTrue(onlinebook instanceof Book);
		
		assertEquals(title, onlinebook.getTitle());
		assertEquals(author, onlinebook.getAuthor());
		assertEquals(nbPages, onlinebook.getTotalPages());
		assertEquals(language, onlinebook.getLanguage());
		assertEquals(2, onlinebook.getMaxPeople());
		assertEquals("", onlinebook.getContent());
	}

}
