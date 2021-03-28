package fileCleaner;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public final class PersonTextFile implements DAO<Person>{

	private List<Person> persons = null;
	private Path personsPath = null;
	private File personsFile = null;
	private final String FIELD_SEP = ",";
	
	public PersonTextFile(String location) {
		personsPath = Paths.get(location);
		personsFile = personsPath.toFile();
		persons = this.getAll();
	}
	
	@Override
	public List<Person> getAll() {
		
		if(persons != null) {
			return persons;
		}
		
		persons = new ArrayList<>();
		if(Files.exists(personsPath)) {
			
			try(BufferedReader in = new BufferedReader(new FileReader(personsFile))){
				String line = in.readLine();
				
				while(line != null) {
					String [] fields = line.split(FIELD_SEP);
					String firstName = fields[0];
					String lastName = fields[1];
					String email = fields[2];
					
					Person p = new Person(firstName, lastName, email);
					persons.add(p);
					line = in.readLine();
				}
			}catch(IOException e) {
				System.out.println(e);
				return null;
			}
		}
		else {
			System.out.println(personsPath.toAbsolutePath() + "doesn't exist");
			return null;
		}
		
		return persons;
		
	}
	
	public List<Person> saveAll(){
		
		persons = new ArrayList<>();
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(personsFile)))){
			
			for(Person p : persons) {
				out.print(p.getFirstName() + FIELD_SEP);
				out.print(p.getLastName() + FIELD_SEP);
				out.print(p.getEmail());
			}
		}
		catch(IOException e) {
			System.out.println(e);
			return null;
		}
		
		return persons;
	}

}
