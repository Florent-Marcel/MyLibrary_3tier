package be.iccbxl.poo.data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import be.iccbxl.poo.entities.Book;
import be.iccbxl.poo.entities.Person;

public class DataCSV extends DataFile {
	
	private String folder;
	private String filenamePeople;
	private String filenameBooks;
	
	private File fPeople;
	private File fBooks;

	public DataCSV(String folder) {
		people = new ArrayList<Person>();
		books = new ArrayList<Book>();
		
		this.setFolder(folder);
		
		this.filenamePeople = folder + "\\people.csv";
		this.filenameBooks = folder + "\\books.csv";
		
		fPeople = new File(filenamePeople);
		fBooks =  new File(filenameBooks);
		
		this.dataLoad();
	}
	
	public DataCSV() {
		super();
	}
	
	public void dataLoad() {
		loadMembers();
		loadBooks();
	}

	public void dataWrite() {
		writeMembers();
		writeBooks();

	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	private void writeMembers() {
		CSVPrinter printer = null;
		
		if(!fPeople.exists()) {
			try {
				fPeople.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			try {
				Charset charset = Charset.forName("UTF-8");
				printer = CSVFormat.EXCEL
						.withDelimiter(';').print(fPeople, charset);
				
				printer.printRecord("id", "name", "maxBooks", "registrationDate", "booksBorrowed");
				
				for(Person p : people) {
					String registrationDate = p.getRegistrationDate()
							.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString();
					
					printer.print(p.getId());
					printer.print(p.getName());
					printer.print(p.getMaxBooks());
					printer.print(registrationDate);
					if(p.getBooks().size() > 0)
						printer.print(p.getBooks());
					else
						printer.print("null");
					printer.println();
				}
			} finally {
				printer.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadMembers() {
		if(fPeople.exists()) {
			FileReader fr = null;
			try {
				try {
					fr = new FileReader(fPeople);
					
					Person p;
					String id;
					String name;
					byte nbMaxBooks;
					String registrationDate;
					String booksBorrowedStr;
					
					List<String> listBooksBorrowedStr;
					ArrayList<UUID> booksBorrowed = new ArrayList<UUID>();
					
					Iterable<CSVRecord> records = CSVFormat.EXCEL
							.withSkipHeaderRecord(true)
							.withHeader("id", "name", "maxBooks", "registrationDate", "booksBorrowed")
							.withDelimiter(';').parse(fr);
					
					for(CSVRecord record : records) {
						listBooksBorrowedStr = new ArrayList<String>();
						booksBorrowed = new ArrayList<UUID>();
						
						id = record.get("id");
						name = record.get("name");
						nbMaxBooks = Byte.valueOf(record.get("maxBooks"));
						registrationDate = record.get("registrationDate");
						booksBorrowedStr = record.get("booksBorrowed");
						if(!booksBorrowedStr.equals("null")) {
							listBooksBorrowedStr = Arrays.asList(booksBorrowedStr.substring(1, booksBorrowedStr.length()-1).split(","));
							for(String s : listBooksBorrowedStr) {
								if(s.charAt(0) == ' ')
									s = s.substring(1);
								booksBorrowed.add(UUID.fromString(s));
							}
						}
						p = new Person(UUID.fromString(id), name, nbMaxBooks, LocalDate.parse(registrationDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")), booksBorrowed);
						people.add(p);
						
					}
				} finally {
					fr.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private void writeBooks() {
		
		CSVPrinter printer = null;
		
		if(!fBooks.exists()) {
			try {
				fBooks.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			try {
				Charset charset = Charset.forName("UTF-8");
				printer = CSVFormat.EXCEL
						.withDelimiter(';').print(fBooks, charset);
				
				printer.printRecord("id", "title", "author", "totalPages", "language", "loanPeriod", "rentalPrice", "borrowingDate", "borrowerID");
				
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
								.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString();
						
						printer.print(borrowingDate);
						printer.print(b.getBorrowerID());
					} else {
						printer.print("null");
						printer.print("null");
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
	
	private void loadBooks() {
		if(fPeople.exists()) {
			FileReader fr = null;
			try {
				try {
					fr = new FileReader(fBooks);
					
					Book b;
					String id;
					String title;
					String author;
					short totalPages;
					String language;
					byte loanPeriod;
					double rentalPrice;
					String borrowingDate;
					String borrowerID;
					
					LocalDate ldBorrowingDate = null;
					UUID borrowerUUID = null;
					
					Iterable<CSVRecord> records = CSVFormat.EXCEL
							.withSkipHeaderRecord(true)
							.withHeader("id", "title", "author", "totalPages", "language", "loanPeriod", "rentalPrice", "borrowingDate", "borrowerID")
							.withDelimiter(';').parse(fr);
					
					for(CSVRecord record : records) {
						ldBorrowingDate = null;
						borrowerUUID = null;
						id = record.get("id");
						title = record.get("title");
						author = record.get("author");
						totalPages = Short.valueOf(record.get("totalPages"));
						language = record.get("language");
						loanPeriod = Byte.valueOf(record.get("loanPeriod"));
						rentalPrice = Double.valueOf(record.get("rentalPrice"));
						borrowingDate = record.get("borrowingDate");
						borrowerID = record.get("borrowerID");
						
						if(!borrowingDate.equals("null") && !borrowerID.equals("null")) {
							ldBorrowingDate = LocalDate.parse(borrowingDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
							borrowerUUID = UUID.fromString(borrowerID);
						}
						
						b = new Book(UUID.fromString(id), title, author, totalPages, language, loanPeriod, rentalPrice, ldBorrowingDate, borrowerUUID);
						books.add(b);
						
					}
				} finally {
					fr.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
