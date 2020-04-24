package be.iccbxl.poo.function;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import be.iccbcl.poo.entities.Book;
import be.iccbcl.poo.entities.Person;
import be.iccbxl.poo.data.Data;

public class Function implements IFunction {
	Data data;

	public List<Person> getMembres() {
		return data.findAllPersons();
	}

	public List<Book> getBooks() {
		return data.findAllBooks();
	}

	public boolean register(Person p) {
		return data.save(p);
	}

	public boolean register(Book b) {
		return data.save(b);
	}

	public boolean unRegister(Person p) {
		return data.delete(p);
	}

	public boolean unRegister(Book b) {
		return data.delete(b);
	}

	public boolean update(Person p) {
		return data.update(p);
	}

	public boolean update(Book b) {
		return data.update(b);
	}

	public int computeRemainingDays(Book b) {
		return b.computeRemainingDays();
	}

}
