package be.iccbxl.poo.matcher;

import java.util.UUID;

import org.simpleframework.xml.transform.Transform;

/**
 * Permet de sérialiser des instances de la classe UUID.
 * @author Marcel F.
 */
public class TUUID implements Transform<UUID>{

	/**
	 * Retourne une instance de <code>UUID</code> via un <code>String</code>.
	 * @return Une instance de <code>UUID</code>.
	 */
	@Override
	public UUID read(String value) throws Exception {
		return UUID.fromString(value);
	}

	/**
	 * Retourne un <code>String</code> représentant une instance de <code>UUID</code>
	 * @return Retourne un <code>String</code> représentant une instance de <code>UUID</code>
	 */
	@Override
	public String write(UUID value) throws Exception {
		return value.toString();
	}

}

