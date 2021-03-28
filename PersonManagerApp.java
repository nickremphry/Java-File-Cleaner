package fileCleaner;

import java.io.*;
import java.util.List;

public class PersonManagerApp {
	
	private static DAO<Person> personFile;
	
	public static void main(String args[]) {
		
		System.out.println("File Cleaner\n");
		
		personFile = new PersonTextFile("prospect.csv");
		
		System.out.println("Source File: prospect.csv");
		
		List<Person> persons = personFile.getAll();
		
		for(Person p : persons) {
			String firstName = p.getFirstName().trim();
			String lastName = p.getLastName().trim();
			String email = p.getEmail().trim();
			
			firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
			lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
			email = email.substring(0).toLowerCase();
			
			p.setFirstName(firstName);
			p.setLastName(lastName);
			p.setEmail(email);
			
		}
		
		
		File cleanFile = new File("prospect_clean.csv");
		
		try {
			cleanFile.createNewFile();
		}
		catch(IOException ioe) {
			System.out.println("Error while Creating File in Java" + ioe);
		}
		
		System.out.print("Cleaned File: " + cleanFile.getPath());
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prospect_clean.csv")))){
			
			for(Person p : persons) {
				out.print(p.getFirstName() + ", ");
				out.print(p.getLastName() + ", ");
				out.print(p.getEmail() + "\n");
			}
			
			System.out.println("\n\nCongratulations! Your file has been clean!");
		}
		catch(IOException e) {
			System.out.println(e);
		}
		
	}

}
