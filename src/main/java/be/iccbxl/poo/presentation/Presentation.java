package be.iccbxl.poo.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;
import be.iccbxl.poo.exception.AlreadyEnregistredException;
import be.iccbxl.poo.exception.BadBookParameterException;
import be.iccbxl.poo.exception.BadPersonParameterException;
import be.iccbxl.poo.exception.BookBorrowedException;
import be.iccbxl.poo.exception.NotEnregistredException;
import be.iccbxl.poo.logic.ILogic;

/**
 * Permet de gèrer l'interface utilisateur.
 * @author Marcel F.
 *
 */
public class Presentation implements IPresentation {
	
	/**
	 * Instance de ILogic pour les opérations logiques et l'accès aux données.
	 */
	private ILogic logic;
	
	/**
	 * Scanner pour recevoir les entrées utilisateur.
	 */
	private Scanner s;
	
	/**
	 * Constructeur.
	 */
	public Presentation() {
		s = new Scanner(System.in);
	}

	public void run() {
		int choice = 0;
		
		do {
			showMenu();
			choice = nextInt();
			
			switch(choice) {
				case 0:
					logic.save();
					System.out.println("Le programme a été quitté avec succès et les données ont été sauvegardées");
					break;
				
				case 1:
					printPeople(logic.getPeople());
					break;
				
				case 2:
					addMember();
					break;
					
				case 3:
					removeMember();
					break;
					
				case 4:
					printBooks(logic.getBooks());
					break;
					
				case 5:
					addBook();
					break;
					
				case 6:
					removeBook();
					break;
					
				case 7:
					BorrowsBook();
					break;
				
				case 8:
					returnBook();
					break;
					
				case 9:
					editAMember();
					break;
					
				case 10:
					editABook();
					break;
					
				case 11:
					ShowBooksLoaned();
					break;
				
				case 12:
					logic.save();
					System.out.println("Les données ont été sauvegardées");
					break;
					
				default:
					System.out.println("Commande inconnue.");
			}
			
		}while(choice != 0);
	}
	
	/**
	 * Affiche le menu.
	 */
	private void showMenu() {
		System.out.println("\n1  - Afficher tous les membres.");
		System.out.println("2  - Ajouter un membre.");
		System.out.println("3  - Retirer un membre.");
		System.out.println("4  - Afficher tous les livres.");
		System.out.println("5  - Ajouter un livre.");
		System.out.println("6  - Retirer un livre.");
		System.out.println("7  - Emprunter un livre.");
		System.out.println("8  - Retourner un livre.");
		System.out.println("9  - Modifier un membre.");
		System.out.println("10  - Modifier un livre.");
		System.out.println("11  - Afficher les livres empruntés d'un membre");
		System.out.println("12  - Sauvegarder les données");
		System.out.println("0  - Sauvegarder et quitter.");
	}
	
	/**
	 * Affiche une liste de personne.
	 * @param people La liste de personne.
	 */
	private void printPeople(List<Person> people) {
		System.out.println("");
		int i = 0;
		for(Person p : people) {
			System.out.printf("%-5s%-5s%-20s - %-12s%-10s - %-28s%-2s\n", ++i + ".", "nom: ", p.getName(), "inscrit le: ", p.getRegistrationDate(), "nombre de livres empruntés: ", p.getBooks().size());//(++i + ". " + p);
		}
		System.out.println("");
	}
	
	/**
	 * demande a l'utilisateur un <code>int<code> et le retourne. 
	 * @return L'<code>int<code> que l'utilisateur a entré.
	 */
	private int nextInt() {
		int i = s.nextInt();
		s.nextLine();
		
		return i;
	}
	
	/**
	 * demande a l'utilisateur un <code>short<code> et le retourne. 
	 * @return L'<code>short<code> que l'utilisateur a entré.
	 */
	private short nextShort() {
		short nb = s.nextShort();
		s.nextLine();
		
		return nb;
	}
	
	/**
	 * Demande a l'utilisateur d'ajouter un membre.
	 */
	private void addMember() {
		String name;
		System.out.println("Veuillez entrer le nom: ");
		name = s.nextLine();
		
		try{
			logic.register(new Person(UUID.randomUUID(), name)); 
			System.out.println(name + " a bien été ajouté");
			
		} catch(NullPointerException e) {
			System.out.println(name + " n'a pas pu être ajoute");
			System.out.println(e.getMessage());
			
		} catch(AlreadyEnregistredException e) {
			System.out.println(name + " n'a pas pu être ajoute");
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Demande a l'utilisateur de retirer un membre.
	 */
	private void removeMember() {
		Person p = askPerson();
		if(p != null && logic.unRegister(p)) {
			System.out.println("Le membre a été supprimé avec succès");
		} else {
			System.out.println("La suppression n'a pas pu être éxecutée");
		}
	}
	
	/**
	 * permet a l'utilisateur de choisir une personne.
	 * @return La personne choisie par l'utilisateur. <code>Null</code> si aucune personne n'a été trouvé.
	 */
	private Person askPerson() {
		String name;
		List<Person> pers = new ArrayList<Person>(); // people found
		printPeople(logic.getPeople());
		System.out.println("Veuillez entrer le nom: ");
		name = s.nextLine();
		
		pers = logic.findByPerson("name", name);
		
		if(pers.size() == 0) {
			System.out.println("Aucune personne trouvée");
		} else {
			if(pers.size() == 1) {
				return pers.get(0);
			} else {
				System.out.println("Entrez le numéro de la personne: ");
				printPeople(pers);
				
				int choice = nextInt() - 1;
				
				if(choice < pers.size() && choice >= 0) {
					return pers.get(choice);
				}
			}
		}
		return null;
	}
	
	/**
	 * affiche une liste de livre.
	 * @param books La liste de livre.
	 */
	private void printBooks(List<Book> books) {
		System.out.println("");
		int i = 0;
		for(Book b : books) {
			System.out.printf("%-5s%-8s%-20s - %-7s%-30s - %-10s%-17s\n", ++i + ".", "Auteur: ", b.getAuthor(), "Titre: ", b.getTitle(), "emprunté: ", ((b.getBorrowerID()!=null) ? "oui le " + b.getBorrowingDate() : "non"));
		}
		
		System.out.println("");
	}
	
	/**
	 * Demande a l'utilisateur d'ajouter un livre.
	 */
	private void addBook() {
		String title, author, language;
		short nbPages;
		
		System.out.println("Veuillez entrer le titre: ");
		title = s.nextLine();
		
		System.out.println("Veuillez entrer l'auteur: ");
		author = s.nextLine();
		
		System.out.println("Veuillez entrer la langue: ");
		language = s.nextLine();
		
		System.out.println("Veuillez entrer le nombre de pages: ");
		nbPages = nextShort();
		
		try{
			logic.register(new Book(UUID.randomUUID(), title, author, nbPages, language));
			System.out.println(title + " a bien été ajoute");
			
		} catch(NullPointerException e) {
			System.out.println(title + " n'a pas pu être ajouté");
			System.out.println(e.getMessage());
			
		} catch(AlreadyEnregistredException e) {
			System.out.println(title + " n'a pas pu être ajouté");
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Demande a l'utilisateur de retirer un livre.
	 */
	private void removeBook() {
		Book book = askBook();
		if(book != null && logic.unRegister(book)) {
			System.out.println("Le livre a été supprimé avec succès");
		} else {
			System.out.println("La suppression n'a pas pu être éxecutée");
		}
	}
	
	/**
	 * permet a l'utilisateur de choisir un livre. retourne <code>null</code> si aucune livre n'a été trouvé.
	 * @return Le livre choisi par l'utilisateur. Retourne <code>null</code> si aucune livre n'a été trouvé.
	 */
	private Book askBook() {
		String title;
		List<Book> books = new ArrayList<Book>(); // people found
		printBooks(logic.getBooks());
		System.out.println("Veuillez entrer le titre: ");
		title = s.nextLine();
		
		books = logic.findByBook("title", title);
		
		if(books.size() == 0) {
			System.out.println("Aucun livre trouvé");
		} else {
			if(books.size() == 1) {
				return books.get(0);
			} else {
				System.out.println("Entrez le numéro du livre: ");
				printBooks(books);
				
				int choice = nextInt() - 1;
				
				if(choice < books.size() && choice >= 0) {
					return books.get(choice);
				}
			}
		}
		return null;
	}
	
	/**
	 * Demande a l'utilisateur de sélectionner une personne puis un livre et éxecute l'emprunt.
	 */
	private void BorrowsBook() {
		Person p;
		Book b;
		
		System.out.println("Choisissez la personne qui emprunte: ");
		
		p = askPerson();
		
		if(p != null) {
			System.out.println("Choisissez le livre a emprunter: ");
			
			b = askBook();
			
			if(b != null) {
				try{
					if(logic.borrows(p, b))
						System.out.println("Le livre " + b.getTitle() + " a été empruntré par " + p.getName());
					else
						System.out.println("Le livre a déja été emprunté ou la personne a dépassé son quota");
					
				} catch(NullPointerException e) {
					System.out.println(e.getMessage());
					System.out.println("L'emprunt n'a pas pu être éxecuté.");
					
				} catch(NotEnregistredException e) {
					System.out.println(e.getMessage());
					System.out.println("L'emprunt n'a pas pu être éxecuté.");
				}
			} else {
				System.out.println("L'emprunt n'a pas pu être éxecuté.");
			}
		} else {
			System.out.println("L'emprunt n'a pas pu être éxecuté.");
		}
	}
	
	/**
	 * Demande a l'utilisateur de sélectionner une personne puis un livre et éxecute le retour.
	 */
	private void returnBook() {
		int choice;
		Person borrower = null;
		Book bookBorrowed = null;
		List<Person> borrowers = new ArrayList<Person>();
		List<Book> booksBorrowed = new ArrayList<Book>();
		for(Person p : logic.getPeople()) {
			if(p.isBorrower()) {
				borrowers.add(p);
			}
		}
		
		System.out.println("Choisissez la personne qui retourne son livre: ");
		printPeople(borrowers);
		choice = nextInt() -1 ;
		if(choice >= 0 && choice < borrowers.size()) {
			borrower = borrowers.get(choice);
			for(UUID id : borrower.getBooks()) {
				booksBorrowed.addAll(logic.findByBook("id", id.toString()));
			}
			
			System.out.println("Choisissez le livre a retourner: ");
			printBooks(booksBorrowed);
			choice = nextInt() -1 ;
			if(choice >= 0 && choice < booksBorrowed.size()) {
				bookBorrowed = booksBorrowed.get(choice);
				if(logic.computeIsLoanPeriodExceeded(bookBorrowed)) {
					System.out.println("Vous avez " + logic.computeRemainingDays(bookBorrowed) *-1 + " jours de retards");
					System.out.println("Vous devez " + logic.computeFine(bookBorrowed) + "€ d'amende de retard");
				}
				try {
					if(logic.returns(borrower, bookBorrowed)) {
						System.out.println("Le livre " + bookBorrowed.getTitle() + " de " + borrower.getName() + " a bien été retourné");
					} else {
						System.out.println("Il y a eu une erreur lors du retour.");
					}
				} catch(NullPointerException e) {
					System.out.println(e.getMessage());
					System.out.println("Le retour n'a pas pu être éxécuté");
				} catch(NotEnregistredException e) {
					System.out.println(e.getMessage());
					System.out.println("Le retour n'a pas pu être éxécuté");
				}
				
			} else {
				System.out.println("Le choix du livre est invalide.");
			}
		} else {
			System.out.println("Le choix de la personne est invalide.");
		}
		
	}
	
	/**
	 * Demande a l'utilisateur de modifier un membre.
	 */
	private void editAMember() {
		Person pModif = null;
		String name;
		byte maxBooks;
		System.out.println("Choisissez la personne a modifier");
		pModif = askPerson();
		if(pModif != null) {
			System.out.println("Entrez le nouveau nom: ");
			name = s.nextLine();
			
			System.out.println("Entrez le nouveau nombre maximum de livre qu'il peut emprunter: ");
			maxBooks = s.nextByte();
			s.nextLine();
			try {
				logic.update(pModif, name, maxBooks);
				System.out.println(name + " a bien été modifié");
			} catch(BadPersonParameterException e) {
				System.out.println("paramètres invalides: " + e.getMessage());
			}
		}
	}
	
	/**
	 * Demande a l'utilisateur de modifier un livre.
	 */
	private void editABook() {
		String title;
		String author;
		short totalPages;
		byte loanPeriod;
		double rentalPrice;
		String language;
		
		Book bModif = null;
		
		System.out.println("Choisissez le livre a modifier");
		bModif = askBook();
		
		if(bModif != null) {
			System.out.println("Entrez le nouveau titre:");
			title = s.nextLine();
			
			System.out.println("Entrez le nouvel auteur:");
			author = s.nextLine();
			
			System.out.println("Entrez le nouveau nombre de pages:");
			totalPages = nextShort();
			
			System.out.println("Entrez la nouvelle période d'emprunt:");
			loanPeriod = s.nextByte();
			s.nextLine();
			
			System.out.println("Entrez la nouveau prix d'emprunt:");
			rentalPrice = s.nextDouble();
			s.nextLine();
			
			System.out.println("Entrez la nouvelle langue:");
			language = s.nextLine();
			
			try{
				logic.upadte(bModif, title, author, totalPages, loanPeriod, rentalPrice, language);
				System.out.println("le livre " + title + " a bien été modifié");
			} catch(BadBookParameterException e) {
				System.out.println("Paramètres invalides: " + e.getMessage());
			} catch(BookBorrowedException e) {
				System.out.println(e.getMessage());
			}
			
		}
		
	}
	
	/**
	 * Permet a l'utilisateur d'afficher les détails des livres empruntés par un membre.
	 */
	private void ShowBooksLoaned() {
		System.out.println("Choisissez la personne dont vous voulez voir les emprunts actuels");
		Person p = askPerson();
		if(p != null) {
			printBooks(logic.getBooksLoaned(p));
			for(Book b : logic.getBooksLoaned(p)) {
				System.out.println("---------------------------------");
				System.out.println(b.getTitle() + ":");
				if(!logic.computeIsLoanPeriodExceeded(b)) {
					System.out.println("Nombre de jours restants: " + logic.computeRemainingDays(b));
				} else {
					System.out.println("Nombre de jours en retard: " + logic.computeDaysLate(b));
				}
				System.out.println("Coût d'emprunt: " + b.getRentalPrice() + "€");
				System.out.println("Amende de retard: " + logic.computeFine(b) + "€");
				System.out.println("Coût total: " + (b.getRentalPrice() + logic.computeFine(b)) + "€");
				
				
			}
			System.out.println("----------------------------");
			System.out.println("Coût total des emprunts, amendes de retards incluses: " + logic.computeTotalLoanCost(p) + "€");
		}
		
	}
	
	/**
	 * Change l'instance de <code>Ilogic</code>.
	 * @param logic L'instance de <code>Ilogic</code>.
	 */
	public void setLogic(ILogic logic) {
		this.logic = logic;
	}

}
