package classes;

public class Config {

	public static final String VERSION = "v1.2.0";
	public static final String FILE_PATH =".\\assets\\files\\";
	public static final String MAZES_PATH = ".\\assets\\mazes\\";
	public static final String USERS_FILE = "users.txt";
	public static final String WELCOME = "Bienvenido. Ha accedido al programa LABERINTO(" + VERSION +")\nEste programa recorre un laberinto y encuentra la salida del mismo";
	public static final String GOODBYE = "\nHa decidido cerrar el programa. Que tenga un buen dia";
	public static final String UNLOGGED_MENU = "\n     - MENU -\n -----------------\n 1 Iniciar sesion \n 2 Registro\n 0 Salir\n\nSelecciona una opcion: ";
	public static final String LOGGED_MENU_ADMIN= "\n      - MENU -\n --------------------\n 1 Cargar laberinto\n 2 Ver laberinto actual\n 3 Establecer casillas de entrada y salida\n 4 Buscar caminos\n 5 Ver usuario actual\n 6 Gestion de usuarios\n 7 Cerrar sesion\n 0 Salir\n\nSelecciona una opcion: ";
	public static final String SEEKING_A_PATH = " - MENU -\n -----------------\n 1 El primer camino posible \n 2 Camino mas corto\n 0 Cancelar\n\nSelecciona una opcion: ";
	public static final String LOGGED_MENU = "\n      - MENU -\n --------------------\n 1 Cargar laberinto\n 2 Ver laberinto actual\n 3 Establecer casillas de entrada y salida\n 4 Buscar caminos\n 5 Ver usuario actual\n 6 Modificar usuario\n 7 Cerrar sesion\n 0 Salir\n\nSelecciona una opcion: ";
	public static final String ADMIN_MENU = "\n      - MENU -\n --------------------\n 1 Crear nuevo usuario\n 2 Gestion de usuarios\n 0 Salir\n\nSelecciona una opcion: ";
	public static final String MODIFY_USER = "\n¿Qué quieres modificar?\n-1 Contrase\u00f1a\n-2 Nombre\n-3 Nif\n-4 Email\n-5 Direccion\n-6 Fecha de nacimiento\nElige opcion: ";
	
}

