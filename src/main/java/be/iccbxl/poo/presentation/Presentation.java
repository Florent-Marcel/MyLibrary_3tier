package be.iccbxl.poo.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;
import be.iccbxl.poo.function.Function;
import be.iccbxl.poo.function.IFunction;

public class Presentation implements IPresentation {
	private IFunction func;
	
	private Scanner s;
	
	private String message;
	
	
	
	public Presentation() {
		s = new Scanner(System.in);
		func = new Function();
		
		
	}

	public void run() {
		int choix = 0;
		do {
			showMenu();
			choix = nextInt();
			System.out.println("");
			
			switch(choix) {
				case 0:
					break;
				
				case 1:
					printPeople(func.getPeople());
					break;
				
				case 2:
					addMember();
					break;
					
				case 3:
					removeMember();
					break;
					
				case 4:
					printBooks(func.getBooks());
					break;
					
				case 5:
					addBook();
					break;
				
				default:
					System.out.println("Commande inconnue.");
			}
			
		}while(choix != 0);
	}
	
	private void showMenu() {
		System.out.println("\n1 - Afficher tous les membres.");
		System.out.println("2 - Ajouter un membre.");
		System.out.println("3 - Retirer un membre.");
		System.out.println("4 - Afficher tous les livres.");
		System.out.println("5 - Ajouter un livre.");
		System.out.println("0 - Quitter.");
	}
	
	private void printPeople(List<Person> people) {
		int i = 0;
		for(Person p : people) {
			System.out.println(++i + ".\tNom: " + p.getName() + "\tInscrit le: " + p.getRegistrationDate());
		}
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
		
		func.register(new Person(UUID.randomUUID(), name));
	}
	
	private void removeMember() {
		String name;
		List<Person> pf = new ArrayList<Person>(); // people found
		Person ptr = null; // person to remove
		System.out.println("Veuillez entrer le nom: ");
		name = s.nextLine();
		
		pf = func.findByPerson("name", name);
		
		if(pf.size() == 0) {
			System.out.println("Aucune personne trouvée");
		} else {
			if(pf.size() == 1) {
				ptr = pf.get(0);
			} else {
				System.out.println("Entrez le numéro de la personne a retirer: ");
				printPeople(pf);
				
				int choice = nextInt() - 1;
				
				if(choice < pf.size() && choice >= 0) {
					ptr = pf.get(choice);
				}
			}
			if(ptr != null && func.unRegister(ptr)) {
				System.out.println("Le membre a été supprimé avec succès");
			} else {
				System.out.println("Il y a eu une erreur lors de la suppresion");
			}
			
		}
		
	}
	
	private void printBooks(List<Book> books) {
		for(Book b : func.getBooks()) {
			System.out.println("1.\tAuteur: " + b.getAuthor() + "\t\tTitre: " + b.getTitle() );
		}
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
		
		func.register(new Book(UUID.randomUUID(), title, author, nbPages, language));
	}

}
