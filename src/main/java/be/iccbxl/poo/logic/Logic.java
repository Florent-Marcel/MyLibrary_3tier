package be.iccbxl.poo.logic;

import java.util.List;

import be.iccbxl.poo.data.IData;
import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;

/**
 * Classe qui gère toutes les opérations logiques.
 * @author Marcel F.
 */
public class Logic implements ILogic {
	
	/**
	 * Instance de IData pour l'accès aux données.
	 */
	private IData data;
	
	/**
	 * Constructeur, initialiseé par Spring.
	 */
	public Logic() {
		//data = new Data("");
	}

	public void register(Person p) {
		data.save(p);
	}

	public void register(Book b) {
		data.save(b);
	}

	public boolean unRegister(Person p) {
		return data.delete(p);
	}

	public boolean unRegister(Book b) {
		return data.delete(b);
	}

	public int computeRemainingDays(Book b) {
		return b.computeRemainingDays();
	}
	
	public double computeFine(Book b) {
		return b.computeFine();
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

	public boolean borrows(Person p, Book b) {
		return data.borrows(p, b);
	}

	public boolean returns(Person p, Book b) {
		return data.returns(p, b);
	}

	public void update(Person p, String name, byte maxBooks) {
		data.update(p, name, maxBooks);
	}
	
	public void upadte(Book b, String title, String author, short totalPages, byte loanPeriod, double rentalPrice, String language) {
		data.update(b, title, author, totalPages, loanPeriod, rentalPrice, language);
	}

	public boolean computeIsLoanPeriodExceeded(Book b) {
		return computeRemainingDays(b) < 0;
	}

	public List<Book> getBooksLoaned(Person p) {
		return data.getBooksLoaned(p);
	}

	public double computeTotalLoanCost(Person p) {
		double cost = 0;
		List<Book> booksLoaned = getBooksLoaned(p);
		for(Book b : booksLoaned) {
			cost += b.getRentalPrice() + b.computeFine();
		}
		return cost;
	}
	
	public int computeDaysLate(Book b) {
		if(computeIsLoanPeriodExceeded(b)) {
			return computeRemainingDays(b) * -1;
		}
		return 0;
	}

	public boolean isEnregistred(Person p) {
		return data.isEnregistred(p);
	}

	public boolean isEnregistred(Book b) {
		return data.isEnregistred(b);
	}
	

}
