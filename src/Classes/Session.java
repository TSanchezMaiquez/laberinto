package Classes;

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner; 
import java.util.ArrayList;

public class Session {
	
	public User user;
	private boolean logged;
	
	public Session() {
		
		this.logged = false;
		this.user = new User();
		
	}
	public boolean isLogged () {
		return this.logged;
	}
	
	public void login() {
		String username = Interface.getString("Nombre usuario: ");
		
		if(username.length()==0) {
			System.err.println("Nombre de usuario vacio");
			return;
		}
		String password = Interface.getString("Password: ");
		if(password.length()==0) {
			System.err.println("Password incorrecto o vacio");
			return;
		}
		ArrayList<String> lines =this.readAllUsers();
		for(int i=0; i<lines.size(); i++) {
			String currentLine= lines.get(i);
			String [] currentUser = currentLine.split("#");
			if(currentUser[0].equalsIgnoreCase(username)&& currentUser[1].equals(password)) {
				this.logged = true;
				this.user.username = currentUser[0];
				this.user.name = currentUser[2];
				this.user.nif = currentUser[3];
				this.user.email = currentUser[4];
				this.user.address = currentUser[5];
				this.user.birthdate = currentUser[6];
				this.user.role = currentUser[7];
				
				break;
			}
		}
		if(this.logged) {
			System.out.println("Inicio de sesion correcto");
			
		}else {
			System.out.println ("\nUsuario y/o password incorrecto");
		}
	}
	
	private ArrayList<String> readAllUsers(){
		ArrayList<String> allUsers = new ArrayList<String>();
		
		 try {
		      File myObj = new File(Config.FILE_PATH + Config.USERS_FILE);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        allUsers.add(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("Ha ocurrido un error.");
		      e.printStackTrace();
		    }
		
		return allUsers;
	}
	
	public void signup() {
		
		String username = Interface.getString("Nombre de usuario: ");
		if(username.length()==0) {
			System.out.println("El nombre de usuario no debe estar vacio\n");
			return;
		}
		if(this.checkUser(username)) {
			System.out.println("\nEl usuario ya existe\n");
			return;
		}
		
		String password = Interface.getString("Introduce contrase\u00f1a: ");
		String name = Interface.getString("Introduce nombre completo: ");
		String nif =Interface.getString("Introduce nif: ");
		String email =Interface.getString("Introduce email: ");
		String address =Interface.getString("Introduce direccion: ");
		String birthdate =Interface.getString("Introduce fecha de nacimiento: ");
		
		String newLine = "\n" +username + "#" + password + "#" + name + "#" + nif + "#" + email + "#" + address + "#" + birthdate + "#user";
		 try {
		      FileWriter myWriter = new FileWriter(Config.FILE_PATH + Config.USERS_FILE, true);
		      myWriter.write(newLine);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("Ha ocurrido un error.");
		      e.printStackTrace();
		    }
		 System.out.println("Usuario guardado con exito");
		
	}
	private boolean checkUser(String username) {
		
		boolean exists = false;
		ArrayList <String> Lines = this.readAllUsers();
		for(int i=0; i<Lines.size(); i++) {
			
			String [] currentUser = Lines.get(i).split("#");
			if(currentUser[0].equalsIgnoreCase(username)) {
				exists = true;
				break;
			}
		}
		return exists;
	}
	public void showUser() {
		System.out.println("DATOS DEL USUARIO\n -------------------");
		System.out.println("Nombre: "+this.user.username);
		System.out.println("Nombre completo: "+ this.user.name);
		System.out.println("Dni: "+ this.user.nif);
		System.out.println("Email: "+this.user.email);
		System.out.println("Direccion: "+this.user.address);
		System.out.println("Fecha de nacimiento: "+this.user.birthdate);
		System.out.println("Rol: "+this.user.role);
	}
	
	public void logout() {
		this.logged=false;
		this.user = new User();
	}
}
