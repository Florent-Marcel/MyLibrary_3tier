package be.iccbxl.poo.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.simpleframework.xml.ElementList;
import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;
import be.iccbxl.poo.exception.AlreadyEnregistredException;
import be.iccbxl.poo.exception.NotEnregistredException;

/**
 * Classe abstraite représentant les données du programme.
 * @author Marcel F.
 */
public abstract class DataFile implements IData{
	/**
	 * La liste des membres.
	 */
	@ElementList(inline = true, entry = "person", required=false)
	protected List<Person> people;
	
	/**
	 * La liste des livres.
	 */
	@ElementList(inline = true, entry = "book", required=false)
	protected List<Book> books;
	
	/**
	 * Constructeur par défaut pour Spring.
	 */
	public DataFile() {
		if(books == null) {
			books = new ArrayList<Book>();
		}
		if(people == null) {
			people = new ArrayList<Person>();
		}
	}
	
	/**
	 * Supprime une personne de la liste a partir d'un UUID.
	 * @param uuid L'id de la personne a retirer.
	 * @return true si la personne a été retiré de la liste avec succès, sinon false.
	 */
	public boolean deletePerson(UUID uuid) {
		List<Person> listP = findByPerson("id", uuid.toString());
		if(listP.size() > 0)
			return people.remove(listP.get(0));
		return false;
	}
	
	/**
	 * Suprimme la personne de la liste.
	 * @param p La personne a retirer.
	 * @return true si la personne a été retiré de la liste avec succès, sinon false.
	 */
	public boolean delete(Person p) {
		return people.remove(p);
	}

	/**
	 * Supprime un livre de la liste a partir d'un UUID.
	 * @param uuid L'id du livre a retirer.
	 * @return true si le livre a été retiré de la liste avec succès, sinon false.
	 */
	public boolean deleteBook(UUID uuid) {
		List<Book> listB = findByBook("id", uuid.toString());
		if(listB.size() > 0)
			return books.remove(listB.get(0));
		return false;
	}

	/**
	 * Suprimme le livre de la liste.
	 * @param b Le livre a retirer.
	 * @return true si le livre a été retiré de la liste avec succès, sinon false.
	 */
	public boolean delete(Book b) {
		return books.remove(b);
	}

	/**
	 * Ajoute une personne dans la liste <code>people</code>.
	 * @param p La personne a ajouter.
	 * @exception NullPointerException Si <code>p</code> est null.
	 * @exception AlreadyEnregistredException Si <code>p</code> est déja dans la liste <code>people</code>.
	 */
	public void save(Person p) {
		if(p == null) {
			throw new NullPointerException("La personne ne doit pas être nulle");
		}
		if(isEnregistred(p)) {
			throw new AlreadyEnregistredException("La personne est déja enregistrée");
		}
		people.add(p);
	}

	/**
	 * Ajoute un livre dans la liste <code>books</code>.
	 * @param b Le livre a ajouter.
	 * @exception NullPointerException Si <code>b</code> est null.
	 * @exception AlreadyEnregistredException Si <code>b</code> est déja dans la liste <code>books</code>.
	 */
	public void save(Book b) {
		if(b == null) {
			throw new NullPointerException("Le livre ne doit pas être null");
		}
		if(isEnregistred(b)) {
			throw new AlreadyEnregistredException("Le livre est déja enregistré");
		}
		books.add(b);
	}

	/**
	 * Cherche les personnes par un paramètre donné ainsi qu'une valeur.
	 * @param property Le paramètre sur lequel on recherche. Peut être <code>"name"</code> ou <code>"id"</code>.
	 * @return La listr de toutes les personnes trouvées qui correspondent aux critères de recherche. Retourne une liste vide si rien n'a été trouvé.
	 */
	public List<Person> findByPerson(String property, String value) {
		String prop = property.toLowerCase();
		String val = value.toLowerCase();
		List<Person> personFound = new ArrayList<Person>();
		
		if(prop.equals("name")){
			for(Person p : people) {
				if(p.getName().toLowerCase().equals(val)) {
					personFound.add(p);
				}
			}
		} else if(prop.equals("id")) {
			for(Person p : people) {
				if(p.getId().toString().equals(val)) {
					personFound.add(p);
				}
			}
		}
		return personFound;
	}

	/**
	 * Cherche les livres par un paramètre donné ainsi qu'une valeur.
	 * @param property Le paramètre sur lequel on recherche. Peut être <code>"title"</code>, <code>"id"</code> ou <code>"author"</code>.
	 * @return La listr de tous les livres trouvés qui correspondent aux critères de recherche. Retourne une liste vide si rien n'a été trouvé.
	 */
	public List<Book> findByBook(String property, String value) {
		String prop = property.toLowerCase();
		String val = value.toLowerCase();
		List<Book> bookFound = new ArrayList<Book>();
		
		if(prop.equals("title")){
			for(Book b : books) {
				if(b.getTitle().toLowerCase().equals(val)) {
					bookFound.add(b);
				}
			}
		} else if(prop.equals("id")) {
			for(Book b : books) {
				if(b.getId().toString().equals(value)) {
					bookFound.add(b);
				}
			}
		} else if(prop.equals("author")) {
			for(Book b : books) {
				if(b.getAuthor().toLowerCase().equals(val)) {
					bookFound.add(b);
				}
			}
		}
		return bookFound;
	}

	/**
	 * retourne la liste des membres.
	 * @param La liste des membres.
	 */
	public List<Person> getPeople() {
		return people;
	}

	/**
	 * Retourne la liste des livres.
	 * @param La liste des livres.
	 */
	public List<Book> getBooks() {
		return books;
	}
	
	/**
	 * Permet a la personne <code>p</code> d'emprunter le livre <code>b</code>.
	 * @param p La personne qui souhaite emprunter.
	 * @param b Le livre a emprunter.
	 * @return true si le livre a été emprunté avec succès, sinon false.
	 * @exception NullPointerException Si la personne <code>p</code> ou le livre <code>b</code> est null.
	 * @exception NotEnregistredException Si la personne <code>p</code> ou le livre <code>b</code> ne sont pas enregistré dans les listes. Ceci pour garder une base de données cohérente.
	 */
	public boolean borrows(Person p, Book b) {
		if(p == null || b == null) {
			throw new NullPointerException("Le livre et la personne ne doivent pas être nulls");
		}
		if(!isEnregistred(p) || !isEnregistred(b)) {
			throw new NotEnregistredException("Le livre et la personne doivent être présentes dans la base de donnée");
		}
		
		if(p.canBorrows() && !b.isBorrowed()) {
			p.borrows(b);
			b.borrows(p);
			return true;
		}
		return false;
	}
	
	/**
	 * Permet a la personne <code>p</code> de retourner le livre <code>b</code>.
	 * @param p La personne qui souhaite retourner un livre.
	 * @param b Le livre a retourner.
	 * @return true si le livre a été retourneé avec succès, sinon false.
	 * @exception NullPointerException Si la personne <code>p</code> ou le livre <code>b</code> est null.
	 * @exception NotEnregistredException Si la personne <code>p</code> ou le livre <code>b</code> ne sont pas enregistré dans les listes. Ceci pour garder une base de données cohérente.
	 */
	public boolean returns(Person p, Book b) {
		if(p == null || b == null) {
			throw new NullPointerException("Le livre et la personne ne doivent pas être nulls");
		}
		if(!isEnregistred(p) || !isEnregistred(b)) {
			throw new NotEnregistredException("Le livre et la personne doivent être présentes dans la base de donnée");
		}
		
		if(p.getBooks().contains(b.getId()) && b.getBorrowerID() != null && b.getBorrowerID().equals(p.getId())) {
			p.unsetBorrow(b);
			b.unsetBorrows();
			return true;
		}
		return false;
	}
	
	/**
	 * Permet de mettre a jour un membre
	 * @param p La personne a mettre a jour.
	 * @param name Le nouveau nom
	 * @param maxBooks Le nouveau nombre de livres maximum empruntable par la personne.
	 */
	public void update(Person p, String name, byte maxBooks) {
		p.updatePerson(name, maxBooks);
	}
	
	/**
	 * Permet de mettre a jour un livre.
	 * @param b Le livre a mettre a jour.
	 * @param title Le nouveau titre.
	 * @param author Le nouvel auteur.
	 * @param totalPages Le nouveau nombre de pages.
	 * @param loanPeriod La nouvelle période d'emprunt.
	 * @param rentalPrice Le nouveau prix d'emprunt.
	 * @param language La nouvelle langue du livre.
	 */
	public void update(Book b, String title, String author, short totalPages, byte loanPeriod, double rentalPrice, String language) {
		b.updateBook(title, author, totalPages, loanPeriod, rentalPrice, language);
	}
	
	/**
	 * Retourne tous les livres emprunté par une personne.
	 * @param p La personne.
	 * @return La liste des livres emprunté par la personne <code>p</code>. Si elle n'a emprunté aucun livre, retourne une liste vide.
	 */
	public List<Book> getBooksLoaned(Person p){
		List<Book> booksLoaned = new ArrayList<Book>();
		for(UUID id : p.getBooks()) {
			booksLoaned.addAll(findByBook("id", id.toString()));
		}
		return booksLoaned;
	}
	
	/**
	 * Permet de savoir si une personne est présente dans la liste <code>people</code>
	 * @param p La personne a vérifier.
	 * @return true si la personne <code>p</code> est présente dans la liste <code>people</code>, sinon false.
	 */
	public boolean isEnregistred(Person p) {
		for(Person pers : people) {
			if(pers.equals(p)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Permet de savoir si un livre est présent dans la liste <code>books</code>
	 * @param b Le livre a vérifier.
	 * @return true si le livre <code>b</code> est présent dans la liste <code>books</code>, sinon false.
	 */
	public boolean isEnregistred(Book b) {
		for(Book book : books) {
			if(book.equals(b)) {
				return true;
			}
		}
		return false;
	}
}
