package be.iccbxl.poo.function;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.iccbxl.poo.data.Data;
import be.iccbxl.poo.data.IData;
import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;

public class Function implements IFunction {
	IData data;
	static String f = "data\\save.xml";
	
	public Function() {
		//data = new Data("data\\save.xml");
		data = IData.dataLoad(f);
	}

	public List<Person> loadMembres() {
		return data.loadPeople();
	}

	public List<Book> loadBooks() {
		return data.loadBooks();
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
	
	public List<Person> getPeople() {
		return data.getPeople();
	}

	public List<Book> getBooks() {
		return data.getBooks();
	}

	public List<Person> findByPerson(String property, String value) {
		return data.findByPerson(property, value);
	}


	public List<Book> findByBook(String property, String value) {
		return data.findByBook(property, value);
	}

	public void save() {
		// TODO Auto-generated method stub
		IData.dataWrite(f, data);
	}


	

}
