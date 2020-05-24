package be.iccbxl.poo.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import be.iccbxl.poo.exception.BadPersonParameterException;

/**
 * Représente une personne
 * @author Marcel F.
 */
@Root
public class Person {
	
	/**
	 * L'ID de la personne.
	 */
	@Attribute(name="id")
	protected UUID id;
	
	/**
	 * Le nom de la personne.
	 */
	@Attribute(name="name")
	private String name;
	
	/**
	 * Le nombre maximum de livre que la personne peut emprunter.
	 */
	@Attribute(name="maxBooks")
	private byte maxBooks;
	
	/**
	 * La date d'enregistrement de la personne.
	 */
	@Attribute(name="registrationDate")
	private LocalDate registrationDate;
	
	/**
	 * La liste des livres empruntés de la personne.
	 */
	@ElementList(inline = true, entry = "bookRef", name="books", required=false)
	private ArrayList<UUID> books;
	
	/**
	 * Permet d'instancier une personne.
	 * @param id L'id de la personne.
	 * @param name Le nom de la personne.
	 * @param maxBooks Le nombre maximum de livre que peut emprunter la personne.
	 * @param registrationDate La date d'enregistrement de la personne.
	 * @param books La liste des livres empruntés par la personne.
	 * @exception BadPersonParameterException Si des chaines sont vide, ou si <code>registrationDate</code> est null,
	 * ou si <code>maxBook</code> est plus petit que le nombre actuel de livres emprunté. 
	 */
	public Person(UUID id, String name, byte maxBooks, LocalDate registrationDate, ArrayList<UUID> books) {
		if(name.equals("")) {
			throw new BadPersonParameterException("Le nom ne doit pas être vide");
		}
		if(id == null) {
			throw new BadPersonParameterException("id ne doit pas être null");
		}
		if(books == null) {
			throw new BadPersonParameterException("books ne doit pas être null");
		}
		if(maxBooks < 1 || maxBooks < books.size()) {
			throw new BadPersonParameterException("Le nombre maximum de livre ne doit pas être nul, négatif "
					+ "ou plus petit que le nombre de livre emprunté actuellement par la personne");
		}
		if(registrationDate == null) {
			throw new BadPersonParameterException("registrationDate ne doit pas être null");
		}
		this.id = id;
		this.name = name;
		this.maxBooks = maxBooks;
		this.registrationDate = registrationDate;
		this.books = books;
	}
	
	/**
	 * Permet d'instancier une personne avec comme valeure par défaut:
	 * <code>maxBooksw</code> a 3, la date a la date actuelle et une liste vide.
	 * @param id L'id de la personne.
	 * @param name Le nom de la personne.
	 */
	public Person(UUID id, String name) {
		this(id, name, (byte)3, LocalDate.now(), new ArrayList<UUID>());
	}
	
	/**
	 * Constructeur pour Spring et SimpleXML.
	 */
	public Person() {
		// pour sérialiser
		if(books == null) {
			books = new ArrayList<UUID>();
		}
	}

	/**
	 * Retourne le nom de la personne.
	 * @return Le nom de la personne.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Change le nom de la personne.
	 * @param name Le nouveau nom de la personne.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retourne le nombre maximum de livre empruntable par la personne.
	 * @return Le nombre maximum de livre empruntable par la personne.
	 */
	public byte getMaxBooks() {
		return maxBooks;
	}

	/**
	 * Change le nombre maximum de livre empruntable par la personne.
	 * @param maxBooks Le nouveau nombre maximum de livre empruntable par la personne.
	 */
	public void setMaxBooks(byte maxBooks) {
		this.maxBooks = maxBooks;
	}

	/**
	 * Change la date d'inscription de la personne.
	 * @param registrationDate La nouvelle date d'inscription de la personne.
	 */
	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * Retourne l'ID de la personne.
	 * @return L'ID de la personne.
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * retourne la date d'inscription de la personne.
	 * @return La date d'inscription de la personne.
	 */
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * Retourne la liste des livres de la personne.
	 * @return La liste des livres de la personne.
	 */
	public ArrayList<UUID> getBooks() {
		return books;
	}

	/**
	 * Représente une personne sous forme texte.
	 */
	@Override
	public String toString() {
		return "Nom: " + name + "\tInscrit le: " + registrationDate + "\tNumber of books borrowed: " + books.size();
	}

	/**
	 * Marque la personne comme emprunteur et ajoute le livre emprunté dans la liste <code>books</code>.
	 * @param book Le livre a emprunter.
	 */
	public void borrows(Book book) {
		this.books.add(book.getId());
		//book.setBorrower(this);
		//book.borrowingDate = LocalDate.now();
	}

	/**
	 * Retire le livre emprunté de <code>books</code>.
	 * @param book Le livre a retirer
	 */
	public void unsetBorrow(Book book) {
		this.books.remove(book.getId());
		//book.setBorrower(null);
		//book.borrowingDate = null;
	}
	
	/**
	 * Copie une personne et modifie <code>this</code>
	 * @param p La personne a copier.
	 */
	public void updatePerson(Person p) {
		this.name = p.getName();
		this.maxBooks = p.getMaxBooks();
	}
	
	/**
	 * Met a jour la personne.
	 * @param name Le nouveau nom de la personne.
	 * @param maxBooks Le nouveau nombre maximum que la personne peut emprunter.
	 * @exception BadPersonParameterException Si le nom est vide, 
	 * ou que le nombre maximum de livre empruntable est nul, négatif ou inférieur au nombre de livre emprunté actuel.
	 */
	public void updatePerson(String name, byte maxBooks) {
		if(name.equals("")) {
			throw new BadPersonParameterException("Le nom ne doit pas être vide");
		}
		if(maxBooks < 1 || maxBooks < books.size()) {
			throw new BadPersonParameterException("Le nombre maximum de livre ne doit pas être nul, négatif "
					+ "ou plus petit que le nombre de livre emprunté actuellement par la personne");
		}
		this.name = name;
		this.maxBooks = maxBooks;
	}
	
	/**
	 * Permet de savoir si la personne peut encore emprunter.
	 * @return true si la personne peut emprunter, false sinon.
	 */
	public boolean canBorrows() {
		return books.size() < maxBooks;
	}
	
	/**
	 * permet de savoir si la personne a emprunté au moins un livre actuellement..
	 * @return true si la personne a actuellement un livre emprunté, sinon false.
	 */
	public boolean isBorrower() {
		return books.size() > 0;
	}
	
}