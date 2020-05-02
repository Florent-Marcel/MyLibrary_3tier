package be.iccbxl.poo.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.simpleframework.xml.core.Persister;

import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;
import be.iccbxl.poo.matcher.MyMatcher;

public class DataCSV extends DataFile {
	
	private String folder;
	private String filenamePeople;
	private String filenameBooks;

	public DataCSV(String folder) {
		people = new ArrayList<Person>();
		books = new ArrayList<Book>();
		
		people.add(new Person(UUID.randomUUID(), "Gui"));
		people.add(new Person(UUID.randomUUID(), "Anis"));
		people.add(new Person(UUID.randomUUID(), "Max"));
		
		books.add(new Book(UUID.randomUUID(), "Je suis une légende", "Richard Matheson", (short)200, "Français"));
		books.add(new Book(UUID.randomUUID(), "Des fleurs pour Algernon", "Daniel Keyes", (short)400, "Français"));
		
		people.get(0).borrows(books.get(0));
		people.get(0).borrows(books.get(1));
		
		this.setFolder(folder);
	}
	
	@Override
	public void dataLoad() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dataWrite() {
		writeMembers();
		writeBooks();

	}

	public void setFolder(String folder) {
		this.folder = folder;
		this.filenamePeople = folder + "\\people.csv";
		this.filenameBooks = folder + "\\books.csv";
		this.dataLoad();
		
	}
	
	private void writeMembers() {
		File f = new File(filenamePeople);
		
		CSVPrinter printer = null;
		
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			try {
				Charset charset = Charset.forName("Cp1252");
				printer = CSVFormat.EXCEL
						.withDelimiter(';').print(f, charset);
				
				printer.printRecord("id", "name", "maxBooks", "registrationDate", "booksBorrowed");
				
				for(Person p : people) {
					String registrationDate = p.getRegistrationDate()
							.format(DateTimeFormatter.ofPattern("dd-MM-yy")).toString();
					
					printer.print(p.getId());
					printer.print(p.getName());
					printer.print(p.getMaxBooks());
					printer.print(registrationDate);
					printer.print(p.getBooks());
					printer.println();
				}
			} finally {
				printer.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void writeBooks() {
		File f = new File(filenameBooks);
		
		CSVPrinter printer = null;
		
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			try {
				Charset charset = Charset.forName("Cp1252");
				printer = CSVFormat.EXCEL
						.withDelimiter(';').print(f, charset);
				
				printer.printRecord("id", "title", "author", "totalPages", "language", "loanPeriod", "rentalPrice", "BorrowingDate", "BorrowerID");
				
				for(Book b : books) {
					printer.print(b.getId());
					printer.print(b.getTitle());
					printer.print(b.getAuthor());
					printer.print(b.getTotalPages());
					printer.print(b.getLanguage());
					printer.print(b.getLoanPeriod());
					printer.print(b.getRentalPrice());
					
					if(b.getBorrowerID() != null && b.getBorrowingDate() != null) {
						String borrowingDate = b.getBorrowingDate()
								.format(DateTimeFormatter.ofPattern("dd-MM-yy")).toString();
						
						printer.print(b.getBorrowingDate());
						printer.print(b.getBorrowerID());
					}
					
					printer.println();
				}
			} finally {
				printer.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
