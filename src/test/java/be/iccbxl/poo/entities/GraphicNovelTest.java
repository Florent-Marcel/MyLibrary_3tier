package be.iccbxl.poo.entities;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

/**
 * Test de la classe GraphicNovel.
 * @author Marcel F.
 *
 */
public class GraphicNovelTest {
	private String title = "Berserk";
	private String author = "kentaro Miura";
	private short nbPages = 200;
	private String language = "Francais";
	private String cartoonist = author;
	private GraphicNovel graphicNovel;

	@Before
	public void setUp() throws Exception {
		UUID idBook = UUID.randomUUID();
		graphicNovel = new GraphicNovel(idBook, title, author, nbPages, language, cartoonist);
	}

	@Test
	public void testGraphicNovel() {
		assertNotNull(graphicNovel);
		
		assertTrue(graphicNovel instanceof Book);
		assertEquals(title, graphicNovel.getTitle());
		assertEquals(author, graphicNovel.getAuthor());
		assertEquals(nbPages, graphicNovel.getTotalPages());
		assertEquals(language, graphicNovel.getLanguage());
		assertEquals(cartoonist, graphicNovel.getCartoonist());
		assertTrue(graphicNovel.isColor());
	}

}
