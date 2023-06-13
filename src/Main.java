/* Main.java
 * Programa principal del sistema para resolver un laberinto
 * TSM - 2023.01.11
 * version 1.1.0
 */
import classes.Admin;
import classes.Config;
import classes.Interface;
import classes.Log;
import classes.Maze;
import classes.Session;

public class Main {
	
	public static Session userSession = new Session();
	public static Maze maze = new Maze();
	

	
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println(Config.WELCOME);
		
		int option;
		do {
			if(userSession.isLogged()) {
				if(userSession.getUsername().compareToIgnoreCase("admin")==0) {
					option = Interface.getInt("\n"+Config.LOGGED_MENU_ADMIN);
					
				
				}else {
					option = Interface.getInt("\n"+Config.LOGGED_MENU);
				}
					System.out.println();
					loggedAction(option);
				
			
			}else {
			option = Interface.getInt("\n"+Config.UNLOGGED_MENU);
			System.out.println();
			Log.write("Inicio de programa", "Usuario desconocido");
			unloggedAction(option);
			}
			
			
		}while(option!=0);
		Log.write("Cierre de programa", "Fin de los eventos");
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
		public static void loggedAction (int option) throws InterruptedException {
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
				maze.seekingWays();
				break;
			case 5:
				userSession.showUser(null);
				break;
			case 6:
				if(userSession.getUsername().compareToIgnoreCase("admin")==0) {
					Admin admin =new Admin();
					admin.userManadgementOption();
					break;
				}
				userSession.modifyUser();
				break;
			case 7:
				userSession.logout();
				System.out.println("Has decidido cerrar sesion. Hasta la proxima");
				maze = new Maze();
				break;
			case 0:
				break;
			default:
				System.out.println("Opcion no valida");
				break;
			}
			
		}
	}