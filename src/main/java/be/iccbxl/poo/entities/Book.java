package be.iccbxl.poo.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import be.iccbxl.poo.exception.BadBookParameterException;
import be.iccbxl.poo.exception.BookBorrowedException;

/**
 * représente un livre.
 * @author Marcel F.
 */
@Root
public class Book {
	
	/**
	 * L'id du livre.
	 */
	@Attribute(name="id")
	protected UUID id;
	
	/**
	 * Le titre du livre.
	 */
	@Attribute(name="title")
	protected String title;
	
	/**
	 * L'auteur du livre.
	 */
	@Attribute(name="author")
	protected String author;
	
	/**
	 * Le nombre de pages du livre.
	 */
	@Attribute(name="totalPages")
	protected short totalPages;
	
	/**
	 * La période d'emprunt du livre.
	 */
	@Attribute(name="loanPeriod")
	protected byte loanPeriod;
	
	/**
	 * Le prix d'emprunt du livre.
	 */
	@Attribute(name="rentalPrice")
	protected double rentalPrice;
	
	/**
	 * La date d'emprunt du livre. Est null si le livre n'est pas emprunté.
	 */
	@Element(required=false)
	protected LocalDate borrowingDate;
	
	/**
	 * La langue du livre.
	 */
	@Attribute(name="language")
	protected String language;
	
	/**
	 * L'id de l'emprunteur du livre. Est null si le livre n'est pas emprunté.
	 */
	@Element(name="borrowerRef", required=false)
	protected UUID borrowerID;
	
	/**
	 * Constructeur sans paramètres pour SimpleXML.
	 */
	public Book() {
		// pour sérialiser
	}
	
	/**
	 * Constructeur de <code>Book</code>.
	 * @param id L'id du livre.
	 * @param title Le titre du livre.
	 * @param author L'auteur du livre.
	 * @param totalPages Le nomvre de pages du livre.
	 * @param language La langue du livre.
	 * @exception BadBookParameterException Si il y a des chaines vides, des objets null ou un prix négatif.
	 */
	public Book(UUID id, String title, String author, short totalPages, String language) {
		this(id, title, author, totalPages, language, (byte)14, 1.25, null, null);
	}
	
	/**
	 * Constructeur de <code>Book</code>.
	 * @param id L'id du livre.
	 * @param title Le titre du livre.
	 * @param author L'auteur du livre.
	 * @param totalPages Le nomvre de pages du livre.
	 * @param language La langue du livre.
	 * @param loanPeriod Le nombre de jours que le livre peut être emprunté.
	 * @param rentalPrice Le prix d'emprunt.
	 * @param borrowingDate La date d'emprunt. Null si le livre n'est pas emprunté.
	 * @param borrowerID L'id de l'emprunteur. Null si le livre n'est pas emprunté.
	 */
	public Book(UUID id, String title, String author, short totalPages, String language, byte loanPeriod, double rentalPrice,
			LocalDate borrowingDate, UUID borrowerID) {
		if(title.equals("") || author.equals("") || language.equals("")) {
			throw new BadBookParameterException("Le titre, l'auteur et la langue du livre ne doivent pas être vide");
		}
		if(totalPages < 1 || loanPeriod < 1) {
			throw new BadBookParameterException("Le nombre total de pages et la durée d'emprunt ne doivent pas être nuls ou négatifs");
		}
		if(rentalPrice < 0) {
			throw new BadBookParameterException("Le prix d'emprunt ne doit pas être négatif");
		}
		if(id == null) {
			throw new BadBookParameterException("id ne doit pas être null");
		}
		this.id = id;
		this.title = title;
		this.author = author;
		this.totalPages = totalPages;
		this.loanPeriod = loanPeriod;
		this.rentalPrice = rentalPrice;
		this.borrowingDate = borrowingDate;
		this.language = language;
		this.borrowerID = borrowerID;
	}

	/**
	 * Retourne le titre du livre.
	 * @return le titre du livre.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Change le titre du livre.
	 * @param title Le nouveau titre du livre.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Retourne l'auteur du livre.
	 * @return L'auteur du livre.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Change l'auteur du livre.
	 * @param author Le nouvel auteur du livre.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Retourne le nombre de pages du livre.
	 * @return Le nombre de pages du livre.
	 */
	public short getTotalPages() {
		return totalPages;
	}

	/**
	 * Change le nombre de pages du livre.
	 * @param totalPages Nouveau nombre de pages du livre.
	 */
	public void setTotalPages(short totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * Retourne le nombre de jours que le livre peut être emprunté.
	 * @return Le nombre de jours que le livre peut être emprunté.
	 */
	public byte getLoanPeriod() {
		return loanPeriod;
	}

	/**
	 * Change le nombre de jours que le livre peut être emprunté.
	 * @param loanPeriod Le nouveau nombre de jours que le livre peut être emprunté.
	 */
	public void setLoanPeriod(byte loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	/**
	 * Retourne le prix d'emprunt du livre.
	 * @return Le prix d'emprunt du livre.
	 */
	public double getRentalPrice() {
		return rentalPrice;
	}

	/**
	 * Change le prix d'emprunt du livre.
	 * @param rentalPrice Le nouveau prix d'emprunt du livre.
	 */
	public void setRentalPrice(double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	/**
	 * Retourne la date d'emprunt du livre.
	 * @return La date d'emprunt du livre.
	 */
	public LocalDate getBorrowingDate() {
		return borrowingDate;
	}

	/**
	 * Change la date d'emprunt du livre.
	 * @param borrowingDate La nouvelle date d'emprunt du livre.
	 */
	public void setBorrowingDate(LocalDate borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	/**
	 * Retourne la langue du livre.
	 * @return La langue du livre.
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Change la langue du livre.
	 * @param language La nouvelle langue du livre.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Retourne l'ID de l'emprunteur du livre.
	 * @return L'ID de l'emprunteur du livre.
	 */
	public UUID getBorrowerID() {
		return borrowerID;
	}

	/**
	 * Change l'emprunteur du livre.
	 * @param borrower L'emprunteur du livre.
	 */
	public void setBorrower(Person borrower) {
		if(borrower == null) {
			this.borrowerID = null;
		} else {
			this.borrowerID = borrower.getId();
		}
	}
	
	/**
	 * Marque le livre comme emprunté et change la date d'emprunt a celle ou cette méthode est éxécutée.
	 * @param borrower La personne qui emprunte.
	 */
	public void borrows(Person borrower) {
		setBorrower(borrower);
		setBorrowingDate(LocalDate.now());
	}
	
	/**
	 * Marque le livre comme non emprunté.
	 */
	public void unsetBorrows() {
		this.borrowerID = null;
		this.borrowingDate = null;
	}

	/**
	 * Retourne l'ID du livre.
	 * @return L'ID du livre.
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Retourne le livre sous format texte.
	 */
	@Override
	public String toString() {
		return "Auteur: " + author + "\tTitre: " + title + "\tborrowed: " + ((borrowerID!=null) ? "yes on " + borrowingDate : "no");
	}

	/**
	 * Calcule le nombre de jours restants entre la date d'aujourd'hui
	 * et la date d'emprunt <code>borrowingDate</code>.
	 * 
	 * @return Le nombre de jours restants a l'emprunt.
	 */
	public byte computeRemainingDays() {
		byte nbDays;
		
		LocalDate today = LocalDate.now();
		LocalDate returnDate = borrowingDate.plusDays(loanPeriod);
				
		Period p = Period.between(today, returnDate);
		
		nbDays = (byte) p.getDays();
		
		return nbDays;
	}
	
	/**
	 * Calucle l'amende. (0,5€ par semaine dépassant la date d'emprunt + 1€ de taxe administratif).
	 * @return L'amende du livre.
	 */
	public double computeFine() {
		double finePerWeek = 0.5;
		double fine = 0;
		double tax = 1;
		
		byte nbDaysLate = computeRemainingDays();
		if(nbDaysLate < 0) {
			nbDaysLate *= -1;
			fine = ((nbDaysLate / 7) + 1) * finePerWeek + tax;
		}
		
		return fine;
	}

	/**
	 * Copie un livre et mets <code>this</code> a jour.
	 * @param book Le livre a copier.
	 */
	public void updateBook(Book book) {
		this.title = book.title;
		this.author = book.author;
		this.totalPages = book.totalPages;
		this.loanPeriod = book.loanPeriod;
		this.rentalPrice = book.rentalPrice;
		this.language = book.language;
	}
	
	/**
	 * Met a jour un livre.
	 * @param title Le nouveau titre du livre.
	 * @param author Le nouvel auteur du livre.
	 * @param totalPages Le nouveau nombre de pages du livre.
	 * @param loanPeriod Le noueau nombre de jours d'emprunt du livre.
	 * @param rentalPrice Le prix d'emprunt du livre.
	 * @param language La langue du livre.
	 * @exception BadBookParameterException Si des chaines sont vite, ou des nombres sont négatifs ou nuls (seul <code>rentalPrice</code> peut être nul).
	 * @exception BookBorrowedException Si le livre est actuellement emprunté. Ceci pour éviter des incohérences.
	 */
	public void updateBook(String title, String author, short totalPages, byte loanPeriod, double rentalPrice, String language) {
		if(title.equals("") || author.equals("") || language.equals("")) {
			throw new BadBookParameterException("Le titre, l'auteur et la langue du livre ne doivent pas être vide");
		}
		if(totalPages < 1 || loanPeriod < 1) {
			throw new BadBookParameterException("Le nombre total de pages et la durée d'emprunt ne doivent pas être nuls ou négatifs");
		}
		if(rentalPrice < 0) {
			throw new BadBookParameterException("Le prix d'emprunt ne doit pas être négatif");
		}
		if(isBorrowed()) {
			throw new BookBorrowedException("Un livre emprunté ne peut pas être modifié");
		}
		this.title = title;
		this.author = author;
		this.totalPages = totalPages;
		this.loanPeriod = loanPeriod;
		this.rentalPrice = rentalPrice;
		this.language = language;
	}
	
	/**
	 * Permet de savoir si le livre est emprunté ou non.
	 * @return true si le livre est emprunté, sinon false.
	 */
	public boolean isBorrowed() {
		return borrowerID != null && borrowingDate !=null;
	}
}