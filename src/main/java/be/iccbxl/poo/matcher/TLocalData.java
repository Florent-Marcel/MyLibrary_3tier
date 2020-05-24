package be.iccbxl.poo.matcher;

import java.time.LocalDate;

import org.simpleframework.xml.transform.Transform;

/**
 * Classe permettant de sérialiser la classe LocalDate.
 * @author Marcel F.
 */
public class TLocalData implements Transform<LocalDate>{

	/**
	 * Retourne une instance de <code>LocalDate</code> via un <code>String</code>.
	 * @return Une instance de <code>LocalDate</code>.
	 */
	@Override
	public LocalDate read(String value) throws Exception {
		return LocalDate.parse(value);
	}

	/**
	 * Retourne un <code>String</code> représentant une instance de <code>LocalDate</code>
	 * @return Retourne un <code>String</code> représentant une instance de <code>LocalDate</code>
	 */
	@Override
	public String write(LocalDate value) throws Exception {
		return value.toString();
	}

}
