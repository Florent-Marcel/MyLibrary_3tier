package be.iccbxl.poo.exception;

/**
 * Exception pour éviter les incohérences pouvant survenir avec les emprunts. 
 * @author Marcel F.
 */
public class BookBorrowedException extends RuntimeException {

	/**
	 * Constructeur.
	 * @param message Message d'erreur.
	 */
	public BookBorrowedException(String message) {
		super(message);
	}

}
