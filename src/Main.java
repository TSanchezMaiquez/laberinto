/**
 * Main.java
 * Programa principal del sistema para resolver un laberinto
 * TSM - 2023.01.11
 * version 0.1.0
 */
import Classes.Config;
import Classes.Interface;
import Classes.Session;

public class Main {
	
	public static Session userSession = new Session();
	
	public static void main(String[] args) {
		
		System.out.println(Config.WELCOME);
		
		int option;
		do {
			if(userSession.isLogged()) {
			option = Interface.getInt(Config.LOGGED_MENU);
			loggedAction(option);
			}else {
			option = Interface.getInt(Config.UNLOGGED_MENU);
			unloggedAction(option);
			}
			
			
		}while(option!=0);
		
		System.out.println(Config.GOODBYE);
		
	}
	
	public static void unloggedAction (int option) {
		switch(option) {
		
		case 1:
			userSession.login();
			break;
			
		case 2:
			userSession.signup();
			break;
		}
		
	}
	public static void loggedAction (int option) {
		switch(option) {
		
		case 1:
			System.out.println("Proximamente");
			break;
			
		case 2:
			System.out.println("Proximamente");
			break;
		
		case 3:
			System.out.println("Proximamente");
			break;
		case 4:
			System.out.println("Proximamente");
			break;
		case 5:
			userSession.showUser();
			break;
		case 6:
			userSession.logout();
			System.out.println("Has decidido cerrar sesion. Hasta la proxima");
			break;
		}
		
	}
}