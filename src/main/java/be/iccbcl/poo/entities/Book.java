package be.iccbcl.poo.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class Book {
	protected UUID id;
	protected String title;
	protected String author;
	protected short totalPages;
	protected byte loanPeriod;
	protected double rentalPrice;
	protected LocalDate borrowingDate;
	protected String language;
	protected Person borrower;
	
	public Book(UUID id, String title, String author, short totalPages, String language) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.totalPages = totalPages;
		this.language = language;
		this.loanPeriod = 14;
		this.rentalPrice = 1.25;
		this.borrower = null;
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

	public Person getBorrower() {
		return borrower;
	}

	public void setBorrower(Person borrower) {
		this.borrower = borrower;
	}

	public UUID getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", totalPages=" + totalPages
				+ ", loanPeriod=" + loanPeriod + ", rentalPrice=" + rentalPrice + ", borrowingDate=" + borrowingDate
				+ ", language=" + language + ", borrower=" + ((borrower!=null) ? borrower.getName():null) + "]";
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
	
}