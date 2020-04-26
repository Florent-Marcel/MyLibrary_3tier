package be.iccbxl.poo.entities;

import java.util.UUID;

public class OnlineBook extends Book {
	private byte maxPeople;
	private String content;
	
	public OnlineBook(UUID id, String title, String author, short totalPages, String language) {
		super(id, title, author, totalPages, language);
		this.maxPeople = 2;
		this.content = "";
	}

	public byte getMaxPeople() {
		return maxPeople;
	}

	public void setMaxPeople(byte maxPeople) {
		this.maxPeople = maxPeople;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "OnlineBook [maxPeople=" + maxPeople + ", content=" + content + ", id=" + id + ", title=" + title
				+ ", author=" + author + ", totalPages=" + totalPages + ", loanPeriod=" + loanPeriod + ", rentalPrice="
				+ rentalPrice + ", borrowingDate=" + borrowingDate + ", language=" + language + ", borrower=" + borrowerName
				+ "]";
	}
	
}
