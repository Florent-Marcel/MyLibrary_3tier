package be.iccbxl.poo.exception;

/**
 * Exception pour gèrer les doublons.
 * @author Marcel F.
 */
public class AlreadyEnregistredException extends RuntimeException {

	/**
	 * Constructeur.
	 * @param arg0 Le message d'erreur.
	 */
	public AlreadyEnregistredException(String arg0) {
		super(arg0);
	}

}
