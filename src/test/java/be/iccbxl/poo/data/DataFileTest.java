package be.iccbxl.poo.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;

public class DataFileTest {
	private DataFile data;
	private Book b;
	private Book b2;
	private Book b3;
	private Person p;

	@Before
	public void setUp() throws Exception {
		data = new DataCSV();
		b = new Book(UUID.randomUUID(), "forTest", "forTest", (short)100, "fr");
		b2 = new Book(UUID.randomUUID(), "forTes2", "forTest2", (short)100, "fr");
		b3 = new Book(UUID.randomUUID(), "forTes3", "forTest3", (short)100, "fr");
		
		data.getBooks().add(b);
		data.getBooks().add(b2);
		data.getBooks().add(b3);
		
		p = new Person(UUID.randomUUID(), "Jean");
		
		data.getPeople().add(p);
	}

	@Test
	public void testGetBooksLoaned() {
		p.getBooks().add(b.getId());
		p.getBooks().add(b2.getId());
		p.getBooks().add(b3.getId());
		
		List<Book> expected = new ArrayList<Book>();
		expected.add(b);
		expected.add(b2);
		expected.add(b3);
		
		assertEquals(expected, data.getBooksLoaned(p));
	}

}
