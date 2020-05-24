package be.iccbxl.poo.logic;

import java.util.List;

import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;
import be.iccbxl.poo.exception.NotEnregistredException;

/**
 * Interface pour gèrer toutes les opérations logiques.
 * @author Marcel F.
 */
public interface ILogic {
	//Data access
	
	/**
	 * Enregistre une personne.
	 * @param p La personne a enregister.
	 */
	public void register(Person p);
	
	/**
	 * Enregistre un livre.
	 * @param b Le livre a enregister.
	 */
	public void register(Book b);
	
	/**
	 * Supprime une personne de <code>people</code>
	 * @param p La personne a supprimer
	 * @return true si la personne a été supprimé, sinon false.
	 */
	public boolean unRegister(Person p);
	
	/**
	 * Supprime un livre de <code>books</code>
	 * @param b Le livre a supprimer
	 * @return true si le livre a été supprimé, sinon false.
	 */
	public boolean unRegister(Book b);
	
	/**
	 * Retounr la liste de personnes.
	 * @return La liste de personnes.
	 */
	public List<Person> getPeople();
	
	/**
	 * Retounr la liste de livres.
	 * @return La liste de livres.
	 */
	public List<Book> getBooks();
	
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
	 * ecrit les données dans la base de données.
	 */
	public void save();
	
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
	public void upadte(Book b, String title, String author, short totalPages, byte loanPeriod, double rentalPrice, String language);
	
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
	
	//Logic
	
	/**
	 * Calcule le nombre de jours restants a l'emprunt.
	 * @param b Le livre emprunté.
	 * @return Le nombre de jours restants a l'emprunt.
	 */
	public int computeRemainingDays(Book b);
	
	/**
	 * Calcule le nombre de jours dépassant la date limite d'emprunt.
	 * @param b Le livre emprunté.
	 * @return Le nombre de jours dépassant la date limite d'emprunt.
	 */
	public int computeDaysLate(Book b);
	
	/**
	 * Calcule l'amende dû a un retour en retard. (0,50€ par semaine de retard plus 1€ de taxe administratif).
	 * @param b Le livre emprunté.
	 * @return L'amende dû a un retour en retard.
	 */
	public double computeFine(Book b);
	
	/**
	 * permet de savoir si la période d'emprunt a été dépassé.
	 * @param b Le livre emprunté.
	 * @return true si la période d'emprunt a été dépassé, false sinon.
	 */
	public boolean computeIsLoanPeriodExceeded(Book b);
	
	/**
	 * Calcule le prix total des emprunts d'une personne.
	 * @param p La personne.
	 * @return Le prix total des emprunts d'une personne.
	 */
	public double computeTotalLoanCost(Person p);
	
	
}
