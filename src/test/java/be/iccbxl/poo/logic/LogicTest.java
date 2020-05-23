package be.iccbxl.poo.logic;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import be.iccbxl.poo.data.DataCSV;
import be.iccbxl.poo.data.IData;
import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;

public class LogicTest {
	Logic logic;
	DataCSV data;
	
	Book b;
	Book b2;
	Book b3;
	
	Person p;
	
	@Before
	public void setUp() throws Exception {
		logic = new Logic();
		data = new DataCSV();
		logic.setData(data);
		
		b = new Book(UUID.randomUUID(), "test", "test", (short)200, "fr");
		b2 = new Book(UUID.randomUUID(), "test", "test", (short)200, "fr");
		b3 = new Book(UUID.randomUUID(), "test", "test", (short)200, "fr");
		
		p = new Person(UUID.randomUUID(), "jean");
		
		data.getBooks().add(b);
		data.getBooks().add(b2);
		data.getBooks().add(b3);
		
		data.getPeople().add(p);
	}

	@Test
	public void testComputeTotalLoanCost() {
		p.getBooks().add(b.getId());
		p.getBooks().add(b2.getId());
		p.getBooks().add(b3.getId());
		b.setBorrowingDate(LocalDate.now());
		b2.setBorrowingDate(LocalDate.now());
		b3.setBorrowingDate(LocalDate.now());
		
		double expected = b.getRentalPrice() + b2.getRentalPrice() + b3.getRentalPrice();
		double actual = logic.computeTotalLoanCost(p);
		
		assertEquals(expected, actual, 0);
	}
	
	@Test
	public void testComputeTotalLoanCostWithFine() {
		p.getBooks().add(b.getId());
		p.getBooks().add(b2.getId());
		p.getBooks().add(b3.getId());
		b.setBorrowingDate(LocalDate.now().minusDays(15));
		b2.setBorrowingDate(LocalDate.now());
		b3.setBorrowingDate(LocalDate.now().minusDays(21));
		
		double expected = b.getRentalPrice() + b2.getRentalPrice() + b3.getRentalPrice() + 1.5 + 2;
		double actual = logic.computeTotalLoanCost(p);
		
		assertEquals(expected, actual, 0);
	}

}
