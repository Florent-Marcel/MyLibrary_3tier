package be.iccbxl.poo.exception;

/**
 * Exception pour les mauvais paramètres dans la classe <code>Person</code>.
 * @author Marcel F.
 */
public class BadPersonParameterException extends RuntimeException {

	/**
	 * Constructeur.
	 * @param message Message d'erreur.
	 */
	public BadPersonParameterException(String message) {
		super(message);
	}

}
