package be.iccbxl.poo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import be.iccbxl.poo.data.DataXML;
import be.iccbxl.poo.data.IData;
import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;

public class Logic implements ILogic {
	
	private IData data;
	
	
	public Logic() {
		//data = new Data("");
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
		data.dataWrite();
	}

	public void setData(IData data) {
		this.data = data;
	}

	@Override
	public boolean borrows(Person p, Book b) {
		return data.borrows(p, b);
	}


	

}