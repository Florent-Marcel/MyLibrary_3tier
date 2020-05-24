package be.iccbxl.poo.data;

import java.util.List;
import java.util.UUID;

import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;
import be.iccbxl.poo.exception.AlreadyEnregistredException;
import be.iccbxl.poo.exception.NotEnregistredException;

/**
 * Interface représentant les fonctions de traitement des données de l'applicatioN.
 * @author Marcel F.
 */
public interface IData {
	
	/**
	 * Cherche les personnes par un paramètre donné ainsi qu'une valeur.
	 * @param property Le paramètre sur lequel on recherche. Peut être <code>"name"</code> ou <code>"id"</code>.
	 * @param value La valeur a chercher.
	 * @return La listr de toutes les personnes trouvées qui correspondent aux critères de recherche. Retourne une liste vide si rien n'a été trouvé.
	 */
	public List<Person> findByPerson(String property, String value);
	
	/**
	 * Cherche les livres par un paramètre donné ainsi qu'une valeur.
	 * @param property Le paramètre sur lequel on recherche. Peut être <code>"title"</code>, <code>"id"</code> ou <code>"author"</code>.
	 * @param value La valeur a chercher
	 * @return La listr de tous les livres trouvés qui correspondent aux critères de recherche. Retourne une liste vide si rien n'a été trouvé.
	 */
	public List<Book> findByBook(String property, String value);
	
	/**
	 * Supprime une personne de la liste a partir d'un UUID.
	 * @param uuid L'id de la personne a retirer.
	 * @return true si la personne a été retiré de la liste avec succès, sinon false.
	 */
	public boolean deletePerson(UUID uuid);
	
	/**
	 * Suprimme la personne de la liste.
	 * @param p La personne a retirer.
	 * @return true si la personne a été retiré de la liste avec succès, sinon false.
	 */
	public boolean delete(Person p);
	
	/**
	 * Supprime un livre de la liste a partir d'un UUID.
	 * @param uuid L'id du livre a retirer.
	 * @return true si le livre a été retiré de la liste avec succès, sinon false.
	 */
	public boolean deleteBook(UUID uuid);
	
	/**
	 * Suprimme le livre de la liste.
	 * @param b Le livre a retirer.
	 * @return true si le livre a été retiré de la liste avec succès, sinon false.
	 */
	public boolean delete(Book b);
	
	/**
	 * Ajoute une personne dans la liste <code>people</code>.
	 * @param p La personne a ajouter.
	 * @exception NullPointerException Si <code>p</code> est null.
	 * @exception AlreadyEnregistredException Si <code>p</code> est déja dans la liste <code>people</code>.
	 */
	public void save(Person p);
	
	/**
	 * Ajoute un livre dans la liste <code>books</code>.
	 * @param b Le livre a ajouter.
	 * @exception NullPointerException Si <code>b</code> est null.
	 * @exception AlreadyEnregistredException Si <code>b</code> est déja dans la liste <code>books</code>.
	 */
	public void save(Book b);
	
	/**
	 * Retourne la liste des personnes.
	 * @return La liste des personnes.
	 */
	public List<Person> getPeople();
	
	/**
	 * Retourne la liste des livres.
	 * @return La liste des livres.
	 */
	public List<Book> getBooks();
	
	/**
	 * Charge les données.
	 */
	public void dataLoad();
	
	/**
	 * ecrit les données dans la base de données.
	 */
	public void dataWrite();
	
	/**
	 * Permet a la personne <code>p</code> d'emprunter le livre <code>b</code>.
	 * @param p La personne qui souhaite emprunter.
	 * @param b Le livre a emprunter.
	 * @return true si le livre a été emprunté avec succès, sinon false.
	 * @exception NullPointerException Si la personne <code>p</code> ou le livre <code>b</code> est null.
	 * @exception NotEnregistredException Si la personne <code>p</code> ou le livre <code>b</code> ne sont pas enregistré dans les listes. Ceci pour garder une base de données cohérente.
	 */
	public boolean borrows(Person p, Book b);
	
	/**
	 * Permet a la personne <code>p</code> de retourner le livre <code>b</code>.
	 * @param p La personne qui souhaite retourner un livre.
	 * @param b Le livre a retourner.
	 * @return true si le livre a été retourneé avec succès, sinon false.
	 * @exception NullPointerException Si la personne <code>p</code> ou le livre <code>b</code> est null.
	 * @exception NotEnregistredException Si la personne <code>p</code> ou le livre <code>b</code> ne sont pas enregistré dans les listes. Ceci pour garder une base de données cohérente.
	 */
	public boolean returns(Person p, Book b);
	
	/**
	 * Permet de mettre a jour un membre
	 * @param p La personne a mettre a jour.
	 * @param name Le nouveau nom
	 * @param maxBooks Le nouveau nombre de livres maximum empruntable par la personne.
	 */
	public void update(Person p, String name, byte maxBooks);
	
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
	public void update(Book b, String title, String author, short totalPages, byte loanPeriod, double rentalPrice, String language);
	
	/**
	 * Retourne tous les livres emprunté par une personne.
	 * @param p La personne.
	 * @return La liste des livres emprunté par la personne <code>p</code>. Si elle n'a emprunté aucun livre, retourne une liste vide.
	 */
	public List<Book> getBooksLoaned(Person p);
	
	/**
	 * Permet de savoir si une personne est présente dans la liste <code>people</code>
	 * @param p La personne a vérifier.
	 * @return true si la personne <code>p</code> est présente dans la liste <code>people</code>, sinon false.
	 */
	public boolean isEnregistred(Person p);
	
	/**
	 * Permet de savoir si un livre est présent dans la liste <code>books</code>
	 * @param b Le livre a vérifier.
	 * @return true si le livre <code>b</code> est présent dans la liste <code>books</code>, sinon false.
	 */
	public boolean isEnregistred(Book b);
}
