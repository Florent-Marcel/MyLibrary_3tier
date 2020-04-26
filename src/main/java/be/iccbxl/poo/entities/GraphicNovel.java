package be.iccbxl.poo.entities;

import java.util.UUID;

public class GraphicNovel extends Book {
	private String cartoonist;
	private boolean color;
	
	public GraphicNovel(UUID id, String title, String author, short totalPages, String language, String cartoonist) {
		super(id, title, author, totalPages, language);
		this.cartoonist = cartoonist;
		this.color = true;
	}

	public String getCartoonist() {
		return cartoonist;
	}

	public void setCartoonist(String cartoonist) {
		this.cartoonist = cartoonist;
	}

	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "GraphicNovel [cartoonist=" + cartoonist + ", color=" + color + ", id=" + id + ", title=" + title
				+ ", author=" + author + ", totalPages=" + totalPages + ", loanPeriod=" + loanPeriod + ", rentalPrice="
				+ rentalPrice + ", borrowingDate=" + borrowingDate + ", language=" + language + ", borrower=" + borrowerName
				+ "]";
	}
	
}
