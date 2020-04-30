package be.iccbxl.poo.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Book {
	
	@Attribute(name="id")
	protected UUID id;
	
	@Attribute(name="title")
	protected String title;
	
	@Attribute(name="author")
	protected String author;
	
	@Attribute(name="totalPages")
	protected short totalPages;
	
	@Attribute(name="loanPeriod")
	protected byte loanPeriod;
	
	@Attribute(name="rentalPrice")
	protected double rentalPrice;
	
	@Element(required=false)
	protected LocalDate borrowingDate;
	
	@Attribute(name="language")
	protected String language;
	
	@Element(name="borrowerRef", required=false)
	protected UUID borrowerID;
	
	public Book() {
		// pour sérialiser
	}
	
	public Book(UUID id, String title, String author, short totalPages, String language) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.totalPages = totalPages;
		this.language = language;
		this.loanPeriod = 14;
		this.rentalPrice = 1.25;
		this.borrowerID = null;
		this.borrowingDate = null;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public short getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(short totalPages) {
		this.totalPages = totalPages;
	}

	public byte getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(byte loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public double getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public LocalDate getBorrowingDate() {
		return borrowingDate;
	}

	public void setBorrowingDate(LocalDate borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public UUID getBorrowerID() {
		return borrowerID;
	}

	public void setBorrower(Person borrower) {
		if(borrower == null) {
			this.borrowerID = null;
		} else {
			this.borrowerID = borrower.getId();
		}
	}

	public UUID getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", totalPages=" + totalPages
				+ ", loanPeriod=" + loanPeriod + ", rentalPrice=" + rentalPrice + ", borrowingDate=" + borrowingDate
				+ ", language=" + language + ", borrower=" + ((borrowerID!=null) ? borrowerID:null) + "]";
	}

	/**
	 * Calcule le nombre de jours restants entre la date d'aujourd'hui
	 * et la date d'emprunt <code>borrowingDate</code>.
	 * 
	 * @return Le nombre de jours
	 */
	public byte computeRemainingDays() {
		byte nbDays;
		
		LocalDate today = LocalDate.now();
		LocalDate returnDate = borrowingDate.plusDays(loanPeriod);
				
		Period p = Period.between(today, returnDate);
		
		nbDays = (byte) p.getDays();
		
		return nbDays;
	}

	public void updateBook(Book book) {
		this.title = book.title;
		this.author = book.author;
		this.totalPages = book.totalPages;
		this.loanPeriod = book.loanPeriod;
		this.rentalPrice = book.rentalPrice;
		this.language = book.language;
	}
	
	public boolean isBorrowed() {
		return borrowerID != null && borrowingDate !=null;
	}
}