package be.iccbxl.poo.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import be.iccbcl.poo.entities.Book;
import be.iccbcl.poo.entities.Person;
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
			
			switch(choix) {
				case 0:
					break;
				
				case 1:
					printPeople();
					break;
				
				case 2:
					addMember();
					break;
					
				case 3:
					removeMember();
					break;
			}
			
		}while(choix != 0);
	}
	
	private void showMenu() {
		System.out.println("\n1 - Afficher tous les membres.");
		System.out.println("2 - Ajouter un membre.");
		System.out.println("3 - Retirer un membre.");
		System.out.println("0 - Quitter");
	}
	
	private void printPeople() {
		printPeople(func.getPeople());
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
	
	private void addMember() {
		String name;
		System.out.println("Veuillez entrer le nom: ");
		name = s.nextLine();
		
		func.add(new Person(UUID.randomUUID(), name));
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

}
