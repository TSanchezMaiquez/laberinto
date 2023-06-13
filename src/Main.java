/**
 * Main.java
 * Programa principal del sistema para resolver un laberinto
 * TSM - 2023.01.11
 * version 0.1.0
 */
import java.io.IOException;

import Classes.Config;
import Classes.Interface;
import Classes.Maze;
import Classes.Session;

public class Main {
	
	public static Session userSession = new Session();
	public static Maze maze = new Maze();
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		System.out.println(Config.WELCOME);
		
		int option;
		do {
			if(userSession.isLogged()) {
			option = Interface.getInt("\n"+Config.LOGGED_MENU);
			System.out.println();
			loggedAction(option);
			}else {
			option = Interface.getInt("\n"+Config.UNLOGGED_MENU);
			System.out.println();
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
	public static void loggedAction (int option) throws InterruptedException, IOException {
		switch(option) {
		
		case 1:
			maze.loadMaze();
			System.out.println();
			break;
			
		case 2:
			maze.showMap();
			break;
		
		case 3:
			maze.setEntranceExit();
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
			maze = new Maze();
			break;
		}
		
	}
}