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
				
			}
			
		}while(choix != 0);
	}
	
	private void showMenu() {
		System.out.println("1 - Afficher tous les membres.");
		System.out.println("0 - Quitter");
	}
	
	private void printPeople() {
		for(Person p : func.getPeople()) {
			System.out.println("Nom: " + p.getName() + "\tInscrit le: " + p.getRegistrationDate());
		}
	}
	
	private int nextInt() {
		int i = s.nextInt();
		s.nextLine();
			
		return i;
	}

}
