package be.iccbxl.poo.exception;

/**
 * Exception pour éviter les incohérences avec des entités non enregistrés dans la base de données.
 * @author Marcel F.
 */
public class NotEnregistredException extends RuntimeException {

	public NotEnregistredException(String arg0) {
		super(arg0);
	}

}
