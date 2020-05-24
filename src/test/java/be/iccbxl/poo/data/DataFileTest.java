package be.iccbxl.poo.data;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;
import be.iccbxl.poo.exception.AlreadyEnregistredException;
import be.iccbxl.poo.exception.NotEnregistredException;

public class DataFileTest {
	private DataFile data;
	
	private Book b;
	private Book b2;
	private Book b3;
	
	private Person p;
	private Person p2;

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
		p2 = new Person(UUID.randomUUID(), "Marc");
		
		data.getPeople().add(p);
		data.getPeople().add(p2);
	}

	
	@Test
	public void testDataFile(){
		assertNotNull(data);
		assertNotNull(data.getBooks());
		assertNotNull(data.getPeople());
		
		assertTrue(data instanceof IData);
		
	}
	
	@Test
	public void testDeletePersonID() {
		assertTrue(data.deletePerson(p.getId()));
		
		assertEquals(1, data.getPeople().size());
		assertNotEquals(p, data.getPeople().get(0));
	}
	
	@Test
	public void testDeletePersonIDNotPresent() {
		assertFalse(data.deletePerson(UUID.randomUUID()));
		
		assertEquals(2, data.getPeople().size());
	}
	
	@Test
	public void testDeletePerson() {
		assertTrue(data.delete(p));
		
		assertEquals(1, data.getPeople().size());
		assertNotEquals(p, data.getPeople().get(0));
	}
	
	@Test
	public void testDeletePersonNotPresent() {
		assertFalse(data.delete(new Person(UUID.randomUUID(), "Max")));
		
		assertEquals(2, data.getPeople().size());
	}
	
	@Test
	public void testDeleteBookID() {
		assertTrue(data.deleteBook(b.getId()));
		
		assertEquals(2, data.getBooks().size());
		assertNotEquals(b, data.getBooks().get(0));
	}
	
	@Test
	public void testDeleteBookIDNotPresent() {
		assertFalse(data.deleteBook(UUID.randomUUID()));
		
		assertEquals(3, data.getBooks().size());
	}
	
	@Test
	public void testDeleteBook() {
		assertTrue(data.delete(b));
		
		assertEquals(2, data.getBooks().size());
		assertNotEquals(b, data.getBooks().get(0));
	}
	
	@Test
	public void testDeleteBookNotPresent() {
		assertFalse(data.delete(new Book(UUID.randomUUID(), "test5", "test5", (short) 50, "fr")));
		
		assertEquals(3, data.getBooks().size());
	}
	
	@Test
	public void savePerson() {
		Person pToAdd = new Person(UUID.randomUUID(), "Max");
		
		data.save(pToAdd);
		
		assertEquals(3, data.getPeople().size());
		assertEquals(pToAdd, data.getPeople().get(2));
	}
	
	@Test(expected=NullPointerException.class)
	public void savePersonNullExceptionExpected() {
		Person pToAdd = null;
		data.save(pToAdd);
		
		assertEquals(2, data.getPeople().size());
	}
	
	@Test(expected=AlreadyEnregistredException.class)
	public void savePersonAlreadyEnregistredExceptionExpected() {
		data.save(p);
		
		assertEquals(2, data.getPeople().size());
	}
	
	@Test
	public void saveBook() {
		Book bToAdd = new Book(UUID.randomUUID(), "test5", "test5", (short) 50, "fr");
		
		data.save(bToAdd);
		
		assertEquals(4, data.getBooks().size());
		assertEquals(bToAdd, data.getBooks().get(3));
	}
	
	@Test(expected=NullPointerException.class)
	public void saveBookNullExceptionExpected() {
		Book bToAdd = null;
		data.save(bToAdd);
		
		assertEquals(3, data.getBooks().size());
	}
	
	@Test(expected=AlreadyEnregistredException.class)
	public void saveBookAlreadyEnregistredExceptionExpected() {
		data.save(b);
		
		assertEquals(3, data.getBooks().size());
	}
	
	@Test
	public void findByPersonIdPresent(){
		List<Person> result = data.findByPerson("id", p.getId().toString());
		
		assertEquals(1, result.size());
		assertEquals(p, result.get(0));
	}
	
	@Test
	public void findByPersonIdNotPresent(){
		List<Person> result = data.findByPerson("id", UUID.randomUUID().toString());
		
		assertEquals(0, result.size());
	}
	
	@Test
	public void findByPersonNamePresent(){
		List<Person> result = data.findByPerson("name", "marc");
		
		assertEquals(1, result.size());
		assertEquals(p2, result.get(0));
	}
	
	@Test
	public void findByPersonNameMultipleResults(){
		Person p3 = new Person(UUID.randomUUID(), "Marc");
		data.getPeople().add(p3);
		
		List<Person> result = data.findByPerson("name", "marc");
		
		assertEquals(2, result.size());
		
		assertEquals(p2, result.get(0));
		assertEquals(p3, result.get(1));
	}
	
	@Test
	public void findByPersonNameNotPresent(){
		List<Person> result = data.findByPerson("Name", "marcLavoine");
		
		assertEquals(0, result.size());
	}
	
	@Test
	public void findByPersonBadProperty(){
		List<Person> result = data.findByPerson("nameds", "marc");
		
		assertEquals(0, result.size());
	}
	
	@Test
	public void findByBookIdPresent(){
		List<Book> result = data.findByBook("id", b.getId().toString());
		
		assertEquals(1, result.size());
		assertEquals(b, result.get(0));
	}
	
	@Test
	public void findByBookIdNotPresent(){
		List<Book> result = data.findByBook("id", UUID.randomUUID().toString());
		
		assertEquals(0, result.size());
	}
	
	@Test
	public void findByBookTitlePresent(){
		List<Book> result = data.findByBook("title", "forTest");
		
		assertEquals(1, result.size());
		assertEquals(b, result.get(0));
	}
	
	@Test
	public void findByBookTitleMultipleResults(){
		Book b4 = new Book(UUID.randomUUID(), "forTest", "forTest", (short)100, "fr");
		data.getBooks().add(b4);
		
		List<Book> result = data.findByBook("Title", "forTest");
		
		assertEquals(2, result.size());
		
		assertEquals(b, result.get(0));
		assertEquals(b4, result.get(1));
	}
	
	@Test
	public void findByBookTitleNotPresent(){
		List<Book> result = data.findByBook("Title", "marcLavoine");
		
		assertEquals(0, result.size());
	}
	
	@Test
	public void findByBookAuthorPresent(){
		List<Book> result = data.findByBook("author", "forTest");
		
		assertEquals(1, result.size());
		assertEquals(b, result.get(0));
	}
	
	@Test
	public void findByBookAuthorMultipleResults(){
		Book b4 = new Book(UUID.randomUUID(), "forTest", "forTest", (short)100, "fr");
		data.getBooks().add(b4);
		
		List<Book> result = data.findByBook("Author", "forTest");
		
		assertEquals(2, result.size());
		
		assertEquals(b, result.get(0));
		assertEquals(b4, result.get(1));
	}
	
	@Test
	public void findByBookAuthorNotPresent(){
		List<Book> result = data.findByBook("author", "marcLavoine");
		
		assertEquals(0, result.size());
	}
	
	@Test
	public void findByBookBadProperty(){
		List<Book> result = data.findByBook("titlesd", "marc");
		
		assertEquals(0, result.size());
	}
	
	@Test
	public void borrows() {
		assertTrue(data.borrows(p, b));
		
		
		assertTrue(p.isBorrower());
		assertEquals(1, p.getBooks().size());
		assertEquals(b.getId(), p.getBooks().get(0));
		
		assertEquals(LocalDate.now(), b.getBorrowingDate());
		assertEquals(p.getId(), b.getBorrowerID());
		assertTrue(b.isBorrowed());
	}
	
	@Test
	public void borrowsMaxBooksReached() {
		data.borrows(p, b);
		data.borrows(p, b2);
		data.borrows(p, b3);
		
		Book b4 = new Book(UUID.randomUUID(), "forTest", "forTest", (short)100, "fr");
		data.getBooks().add(b4); //pour éviter l'exception
		
		assertFalse(data.borrows(p, b4));
		
		assertEquals(3, p.getBooks().size());
		
		assertNull(b4.getBorrowingDate());
		assertNull(b4.getBorrowerID());
		assertFalse(b4.isBorrowed());
	}
	
	@Test
	public void borrowsBookAlreadyBorrowed() {
		data.borrows(p2, b);
		
		assertFalse(data.borrows(p, b));
		
		assertFalse(p.isBorrower());
		assertEquals(0, p.getBooks().size());
		
		assertEquals(p2.getId(), b.getBorrowerID());
	}
	
	@Test(expected=NullPointerException.class)
	public void borrowsPersonNullExceptionExpected() {
		data.borrows(null, b);
	}
	
	@Test(expected=NullPointerException.class)
	public void borrowsBookNullExceptionExpected() {
		data.borrows(p, null);
	}
	
	@Test(expected=NotEnregistredException.class)
	public void borrowsPersonNotEnregistredExceptionExpected() {
		data.borrows(new Person(UUID.randomUUID(), "x"), b);
	}
	
	@Test(expected=NotEnregistredException.class)
	public void borrowsBookNotEnregistredExceptionExpected() {
		data.borrows(p, new Book(UUID.randomUUID(), "forTest", "forTest", (short)100, "fr"));
	}
	
	@Test
	public void returns() {
		data.borrows(p, b);
		
		assertTrue(data.returns(p, b));
		
		assertFalse(p.isBorrower());
		assertEquals(0, p.getBooks().size());
		
		assertNull(b.getBorrowingDate());
		assertNull(b.getBorrowerID());
		assertFalse(b.isBorrowed());
	}
	
	@Test
	public void returnsNotBorrowedByThePerson() {
		data.borrows(p, b);
		data.borrows(p2, b2);
		
		assertFalse(data.returns(p, b2));
		
		assertTrue(p.isBorrower());
		assertEquals(1, p.getBooks().size());
		
		assertEquals(p2.getId(), b2.getBorrowerID());
		assertTrue(b2.isBorrowed());
	}
	
	@Test
	public void returnsTwoBorrows() {
		data.borrows(p, b);
		data.borrows(p, b2);
		
		assertTrue(data.returns(p, b));
		
		assertTrue(p.isBorrower());
		assertEquals(1, p.getBooks().size());
		assertEquals(b2.getId(), p.getBooks().get(0));
	}
	
	@Test(expected=NullPointerException.class)
	public void returnsPersonNullExceptionExpected() {
		data.borrows(null, b);
	}
	
	@Test(expected=NullPointerException.class)
	public void returnsBookNullExceptionExpected() {
		data.borrows(p, null);
	}
	
	@Test(expected=NotEnregistredException.class)
	public void returnsPersonNotEnregistredExceptionExpected() {
		data.returns(new Person(UUID.randomUUID(), "x"), b);
	}
	
	@Test(expected=NotEnregistredException.class)
	public void returnsBookNotEnregistredExceptionExpected() {
		data.returns(p, new Book(UUID.randomUUID(), "forTest", "forTest", (short)100, "fr"));
	}
	
	@Test
	public void testUpdatePerson(){
		data.update(p, "Jean", (byte) 2);
		
		assertEquals("Jean", p.getName());
		assertEquals(2, p.getMaxBooks());
	}
	
	@Test
	public void testUpdateBook(){
		data.update(b, "test", "test", (short)5, (byte) 30, 2, "en");
		
		assertEquals("test", b.getTitle());
		assertEquals("test", b.getTitle());
		assertEquals(5, b.getTotalPages());
		assertEquals(30, b.getLoanPeriod());
		assertEquals(2, b.getRentalPrice(), 0);
		assertEquals("en", b.getLanguage());
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
	
	@Test
	public void isEnregistredPersonTrue() {
		assertTrue(data.isEnregistred(p));
	}
	
	@Test
	public void isEnregistredPersonFalse() {
		assertFalse(data.isEnregistred(new Person(UUID.randomUUID(), "Marc")));
	}
	
	@Test
	public void isEnregistredBooksTrue() {
		assertTrue(data.isEnregistred(b));
	}
	
	@Test
	public void isEnregistredBookFalse() {
		assertFalse(data.isEnregistred(new Book(UUID.randomUUID(), "test5", "test5", (short) 50, "fr")));
	}

}
