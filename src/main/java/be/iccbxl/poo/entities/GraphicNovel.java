package be.iccbxl.poo.entities;

import java.util.UUID;

/**
 * représente un livre graphique.
 * @author Marcel F.
 */
public class GraphicNovel extends Book {
	/**
	 * Le dessinateur du livre.
	 */
	private String cartoonist;
	/**
	 * La couleur du livre.
	 */
	private boolean color;
	
	/**
	 * Permet d'instancier un objet de <code>GraphicNovel</code>
	 * @param id L'ID du livre.
	 * @param title Le titre du livre.
	 * @param author L'auteur du livre.
	 * @param totalPages Le nombre de pages du livre.
	 * @param language La langue du livre.
	 * @param cartoonist Le dessinateur du livre.
	 */
	public GraphicNovel(UUID id, String title, String author, short totalPages, String language, String cartoonist) {
		super(id, title, author, totalPages, language);
		this.cartoonist = cartoonist;
		this.color = true;
	}

	/**
	 * Retourne le dessinateur du livre.
	 * @return Le dessinateur du livre.
	 */
	public String getCartoonist() {
		return cartoonist;
	}

	/**
	 * Change le dessinateur du livre.
	 * @param cartoonist Le nouveau dessinateur du livre.
	 */
	public void setCartoonist(String cartoonist) {
		this.cartoonist = cartoonist;
	}

	/**
	 * permet de savoir si le livre contient des couleurs.
	 * @return true si le livre contient des couleurs, sinon false.
	 */
	public boolean isColor() {
		return color;
	}

	/**
	 * Change le livre en coloré ou non.
	 * @param color true si coloré, false sinon.
	 */
	public void setColor(boolean color) {
		this.color = color;
	}

	/**
	 * Retourne le livre sous forme de texte.
	 */
	@Override
	public String toString() {
		return "GraphicNovel [cartoonist=" + cartoonist + ", color=" + color + ", id=" + id + ", title=" + title
				+ ", author=" + author + ", totalPages=" + totalPages + ", loanPeriod=" + loanPeriod + ", rentalPrice="
				+ rentalPrice + ", borrowingDate=" + borrowingDate + ", language=" + language + ", borrower=" + borrowerID
				+ "]";
	}
	
}
