package be.iccbxl.poo.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;
import be.iccbxl.poo.logic.ILogic;

public class Presentation implements IPresentation {
	
	private ILogic logic;
	
	private Scanner s;
	
	public void setLogic(ILogic logic) {
		this.logic = logic;
	}

	public Presentation() {
		//AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
		s = new Scanner(System.in);
		//logic = new Logic();
		
		
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
					logic.save();
					System.out.println("Les données ont été sauvegardées");
					break;
					
				default:
					System.out.println("Commande inconnue.");
			}
			
		}while(choice != 0);
	}
	
	private void showMenu() {
		System.out.println("\n1  - Afficher tous les membres.");
		System.out.println("2  - Ajouter un membre.");
		System.out.println("3  - Retirer un membre.");
		System.out.println("4  - Afficher tous les livres.");
		System.out.println("5  - Ajouter un livre.");
		System.out.println("6  - Retirer un livre.");
		System.out.println("7  - Emprunter un livre.");
		System.out.println("8  - Retourner un livre.");
		System.out.println("9  - Sauvegarder les données");
		System.out.println("0  - Quitter.");
	}
	
	private void printPeople(List<Person> people) {
		System.out.println("");
		int i = 0;
		for(Person p : people) {
			System.out.printf("%-5s%-5s%-20s - %-12s%-10s - %-28s%-2s\n", ++i + ".", "nom: ", p.getName(), "inscrit le: ", p.getRegistrationDate(), "nombre de livres empruntés: ", p.getBooks().size());//(++i + ". " + p);
		}
		System.out.println("");
	}
	
	private int nextInt() {
		int i = s.nextInt();
		s.nextLine();
		
		return i;
	}
	
	private short nextShort() {
		short nb = s.nextShort();
		s.nextLine();
		
		return nb;
	}
	
	private void addMember() {
		String name;
		System.out.println("Veuillez entrer le nom: ");
		name = s.nextLine();
		
		if(logic.register(new Person(UUID.randomUUID(), name))) {
			System.out.println(name + " a bien été ajouté");
		} else {
			System.out.println(name + " n'a pas pu être ajoute");
		}
	}
	
	private void removeMember() {
		Person p = askPerson();
		if(p != null && logic.unRegister(p)) {
			System.out.println("Le membre a été supprimé avec succès");
		} else {
			System.out.println("La suppression n'a pas pu être éxecutée");
		}
	}
	
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
	
	private void printBooks(List<Book> books) {
		System.out.println("");
		int i = 0;
		for(Book b : books) {
			System.out.printf("%-5s%-8s%-20s - %-7s%-30s - %-10s%-17s\n", ++i + ".", "Auteur: ", b.getAuthor(), "Titre: ", b.getTitle(), "emprunté: ", ((b.getBorrowerID()!=null) ? "oui le " + b.getBorrowingDate() : "non"));
		}
		
		System.out.println("");
	}
	
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
		
		if(logic.register(new Book(UUID.randomUUID(), title, author, nbPages, language))) {
			System.out.println(title + " a bien été ajoute");
		} else {
			System.out.println(title + " n'a pas pu être ajouté");
		}
	}
	
	private void removeBook() {
		Book book = askBook();
		if(book != null && logic.unRegister(book)) {
			System.out.println("Le livre a été supprimé avec succès");
		} else {
			System.out.println("La suppression n'a pas pu être éxecutée");
		}
	}
	
	
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
	
	private void BorrowsBook() {
		Person p;
		Book b;
		
		System.out.println("Choisissez la personne qui emprunte: ");
		
		p = askPerson();
		
		if(p != null) {
			System.out.println("Choisissez le livre a emprunter: ");
			
			b = askBook();
			
			if(b != null) {
				if(logic.borrows(p, b)) {
					System.out.println("Le livre " + b.getTitle() + " a été empruntré par " + p.getName());
				} else {
					System.out.println("Le livre a déja été emprunté ou la personne a dépassé son quota");
				}
			} else {
				System.out.println("L'emprunt n'a pas pu être éxecuté.");
			}
		} else {
			System.out.println("L'emprunt n'a pas pu être éxecuté.");
		}
	}
	
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
				if(logic.returns(borrower, bookBorrowed)) {
					System.out.println("Le livre " + bookBorrowed.getTitle() + " de " + borrower.getName() + " a bien été retourné");
				} else {
					System.out.println("Il y a eu une erreur lors du retour.");
				}
			} else {
				System.out.println("Le choix du livre est invalide.");
			}
		} else {
			System.out.println("Le choix de la personne est invalide.");
		}
		
	}

}
