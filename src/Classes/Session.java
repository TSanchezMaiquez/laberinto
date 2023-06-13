package classes;

public class Session {
	
	public User user;
	private boolean logged;
	public Database database;
	
	public Session() {
		
		this.logged = false; // Indica que el usuario no está logueado al inicio de la sesión
		this.user = new User(); // Inicializa el objeto User para ser utilizado en la sesión
		this.database = new Database(); // Inicializa el objeto Database para ser utilizado en la sesión
		}

	public boolean isLogged () {
		return this.logged;
	}
	public void login() {
		Log.write("Acceso a login()","Usuario desconocido" );
		String username = Interface.getString("Nombre usuario: ");
		
		 // Si el nombre de usuario está vacío, se muestra un mensaje de error y se registra en el log.
		if(username.length()==0) {
			System.err.println("Nombre de usuario vacio");
			return;
		}
		String password = Interface.getString("Password: ");
		
		// Se encripta la contraseña con MD5
		password= Utils.encryptMd5(password);
		
		// Si la contraseña está vacía, se muestra un mensaje de error y se registra en el log.
		if(password.length()==0) {
			System.err.println("Password incorrecto o vacio");
			return;
		}
		// Si las credenciales son correctas, el usuario se registra como "logueado" y se muestra un mensaje de éxito en el log.
		if(this.database.login(username, password)!=null) {
			this.user =this.database.login(username, password);
			this.logged = true;
		}
			if(this.isLogged()) {
				this.user.username=username;
				this.user.setPassword(password);
				System.out.println("Inicio de sesion correcto");
				Log.write("Inicio de sesion correcto", username);
				
			// Si las credenciales son incorrectas, se muestra un mensaje de error en la consola y se registra en el log.       
			}else {
				System.out.println ("\nUsuario y/o password incorrecto");
				Log.write("Intento de inicio de sesion fallido", username);
			}
	}
	public String getUsername() {
		return user.role;
	}
	public void signup() {
		// Se solicita al usuario que ingrese un nombre de usuario
		String username = Interface.getString("  Nombre de Usuario\n--------------------\n-Solo se admiten numeros, "
				+ "letras y el caracter \"_\"\n-Debe tener entre 3 y 20 digitos.\n\nIntroduce nick: ");
		
		// Se comprueba que el nombre de usuario ingresado no esté vacío
		if(username.length()==0) {
			System.out.println("El nombre de usuario no debe estar vacio\n");
			return;
		}
		// Se verifica que el nombre de usuario ingresado no esté ya en la base de datos
		String CheckUname = "Uname,"+username;
		if(database.checkUser(CheckUname)) {
			System.out.println("\nEl usuario ya existe\n");
			Log.write("Registro fallido", username);
			return;
		}
		// Se verifica que el nombre de usuario ingresado cumpla con los requisitos de formato
		if (Utils.validateUsername(username)) {
			// Se solicita al usuario que ingrese una contraseña
		String password = Interface.getString("\n   Contrase\u00f1a\n --------------\n-Debe tener entre 8 y 20 caracteres.\n"
				+ "-Debe tener al menos una letra minuscula,otra mayuscula, un numero y un simbolo.\n\nIntroduce contrase\u00f1a: ");
		
		    // Se verifica que la contraseña ingresada cumpla con los requisitos de formato
			if (Utils.validatePassword(password)) {
				
					// Se encripta la contraseña con el algoritmo MD5
					password=Utils.encryptMd5(password);
					
					// Se solicita al usuario que ingrese su nombre completo
					String name = Interface.getString("\n   Nombre y apellido/s\n---------------------------\n-Deben empezar por mayuscula."
							+ "\n-No se admiten numeros.\n\nIntroduce nombre completo: ");
				
					// Se verifica que el nombre ingresado cumpla con los requisitos de formato
					if(Utils.validareName(name)) {
						
						// Se solicita al usuario que ingrese su NIF
						String nif =Interface.getString("\n   Nif\n--------\n-Debe estar compuesto por 8 digitos y una letra "
								+ "mayuscula.\n\nIntroduce nif: ");
							
						String CheckNif = "Unif,"+nif;
						
						// Se verifica que el NIF ingresado no esté ya en la base de datos
						if(database.checkUser(CheckNif)) {
							System.out.println("\nEl nif ya existe\n");
							Log.write("Registro fallido", username);
							return;
						}		
					// Se verifica que el NIF ingresado cumpla con los requisitos de formato	
					if(Utils.validateNif(nif)) {
						
							// Se solicita al usuario que ingrese su dirección de correo electrónico
							String email =Interface.getString("\n   Email\n----------\n\nIntroduce email: ");
							
							String CheckEmail = "Uemail,"+email;
							
							// Si el email ya está en la base de datos muestra mensaje indicando que el email ya existe
							if(database.checkUser(CheckEmail)) {
								System.out.println("\nEl email ya existe\n");
								Log.write("Registro fallido", username);
								return;
							}	
							// Se verifica que el email sea valido
						if(Utils.validateEmail(email)) {
							// Se solicita al usuario que ingrese su direccion y fecha de nacimiento
								String address =Interface.getString("\n   Direccion\n--------------\n\nIntroduce dirección: ");
								String birthdate =Interface.getString("\n  Fecha de nacimiento\n----------------------\n"
										+ "-Debe seguir el siguiente formato: "
										+ "dd/mm/yyyy\n\nIntroduce fecha de nacimiento: ");
							// se verifica si la fecha de nacimiento es valida
							if(Utils.validateDate(birthdate)) {
								//Se convierte a formato SQL
								birthdate=Utils.formatDateSQL(birthdate);
								
								//Si el metodo devuelve true significa que el registro de nuevo usuario se ha realizado con exito
								if(database.dbSignUp(0, username, password, name, nif, email, address, birthdate)) {
									System.out.println("Registro de usuario realizado correctamente");
									Log.write("Registro de usuario realizado correctamente", username);
									return;
								}else {
									System.out.println("Ha habido un error y no se ha registrado el usuario");
									Log.write("Error durante el registro", username);
									return;
								}
							}
						}
					}
				}	
			}
		}
		System.out.println("Dato incorrecto");
	}
	public void showUser(User user) {
		
		
		if(user==null) {
			user=this.user;
		}
		
		//Muestra todos los datos del usuario guardados en el objeto user de la clase User
		Log.write("Acceso a showUser()", user.username+ " id: "+user.id);
		System.out.println("\n\nDATOS DEL USUARIO\n -------------------");
		System.out.println("Id: "+user.id);
		System.out.println("Nombre: "+user.username);
		System.out.println("Nombre completo: "+ user.name);
		System.out.println("Dni: "+ user.nif);
		System.out.println("Email: "+user.email);
		System.out.println("Direccion: "+user.address);
		user.birthdate=Utils.formatDateEU(user.birthdate);
		System.out.println("Fecha de nacimiento: "+user.birthdate);
		System.out.println("Edad: "+ Utils.getAge(user.birthdate));
		System.out.println("Rol: "+user.role);
		
		
		
	}
		public void modifyUser () {
			
			Admin admin= new Admin();
			int secondOption = Interface.getInt("\n      - MENU -\n --------------------\n-1 Modificar usuario.\n-2 Eliminar usuario\n-0 Salir\n\nIntroduce numero: ");
			
			String id = String.valueOf(this.user.id);
			
			if(secondOption ==1) {
				updateUser(admin.changeUser(id));
				return;	
			}else if(secondOption==2) {
				String password = Interface.getString("Introduce contraseña si estas seguro de eliminar el usuario: ");
				password=Utils.encryptMd5(password);
				if(password.equals(this.user.getPassword())) {
					database.deleteUser(id);
					String checkData = "Uid,"+id;
						if(!database.checkUser(checkData)) {
								System.out.println("Usuario eliminado");
								logout();
								return;
						}
				}	
				System.out.println("El usuario no ha podido ser eliminado");
			}
			
		}
	private void updateUser(String update) {
		String [] updateDate = update.split(",");
		
		if (updateDate[0].equals("password")) {
			this.user.setPassword(updateDate[1]);
		}
		else if (updateDate[0].equals("name")) {
			this.user.name=(updateDate[1]);
		}
		else if (updateDate[0].equals("nif")) {
			this.user.nif=(updateDate[1]);
		}
		else if (updateDate[0].equals("email")) {
			this.user.email=(updateDate[1]);
		}
		else if (updateDate[0].equals("address")) {
			this.user.address=(updateDate[1]);
		}
			
	}
	public void logout() {
		
		// Escribe en el registro de actividad que se ha accedido al método logout y quién lo ha hecho
		Log.write("Acceso a logOut()", user.username);
		
		// Establece el valor de logged a falso para indicar que el usuario ya no está conectado
		this.logged=false;
		
		// Crea un nuevo objeto User para eliminar la información del usuario actual
		this.user = new User();
	}
}
