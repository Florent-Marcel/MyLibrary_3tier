package be.iccbxl.poo.entities;

import java.util.UUID;

/**
 * représente un livre en ligne.
 * @author Marcel F.
 */
public class OnlineBook extends Book {
	/**
	 * Le nombre maximum de personne pouvant emprunté le livre simultanément.
	 */
	private byte maxPeople;
	
	/**
	 * Le contenu du livre.
	 */
	private String content;
	
	/**
	 * Permet d'instancier un objet de <code>OnlineBook</code>
	 * @param id L'id du livre.
	 * @param title Le titre du livre.
	 * @param author L'auteur du livre.
	 * @param totalPages Le nombre de pages du livre.
	 * @param language La langue du livre.
	 */
	public OnlineBook(UUID id, String title, String author, short totalPages, String language) {
		super(id, title, author, totalPages, language);
		this.maxPeople = 2;
		this.content = "";
	}

	/**
	 * Retourne le nombre maximum de personne pouvant emprunter ce livre simultanément.
	 * @return Le nombre maximum de personne pouvant emprunter ce livre simultanément.
	 */
	public byte getMaxPeople() {
		return maxPeople;
	}

	/**
	 * Change le nombre maximum de personne pouvant emprunter ce livre simultanément.
	 * @param maxPeople Le nouveau nombre maximum de personne pouvant emprunter ce livre simultanément.
	 */
	public void setMaxPeople(byte maxPeople) {
		this.maxPeople = maxPeople;
	}

	/**
	 * Retourne le contenu du livre.
	 * @return Le contenu du livre.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Change le contenu du livre.
	 * @param content Le nouveau contenu du livre.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Représente un livre en ligne sous forme texte.
	 */
	@Override
	public String toString() {
		return "OnlineBook [maxPeople=" + maxPeople + ", content=" + content + ", id=" + id + ", title=" + title
				+ ", author=" + author + ", totalPages=" + totalPages + ", loanPeriod=" + loanPeriod + ", rentalPrice="
				+ rentalPrice + ", borrowingDate=" + borrowingDate + ", language=" + language + ", borrower=" + borrowerID
				+ "]";
	}
	
}
