package classes;



public class Session {
	
	public User user;
	private boolean logged;
	public Database database;
	
	public Session() {
		
		this.logged = false;
		this.user = new User();
		this.database = new Database();
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
		
		password= Utils.encryptMd5(password);
		
		if(password.length()==0) {
			System.err.println("Password incorrecto o vacio");
			return;
		}
		if(this.database.login(username, password)!=null) {
			this.user =this.database.login(username, password);
			this.logged = true;
		}
			if(this.isLogged()) {
				System.out.println("Inicio de sesion correcto");
				
			}else {
				System.out.println ("\nUsuario y/o password incorrecto");
			}
	}
	public void signup() {
		
		String username = Interface.getString("  Nombre de Usuario\n--------------------\n-Solo se admiten numeros, "
				+ "letras y el caracter \"_\"\n-Debe tener entre 3 y 20 digitos.\n\nIntroduce nick: ");
		if(username.length()==0) {
			System.out.println("El nombre de usuario no debe estar vacio\n");
			return;
		}	
		String CheckUname = "Uname,"+username;
		if(database.checkUser(CheckUname)) {
			System.out.println("\nEl usuario ya existe\n");
			return;
		}
		if (Utils.validateUsername(username)) {
		String password = Interface.getString("\n   Contraseña\n --------------\n-Debe tener entre 8 y 20 caracteres.\n"
				+ "-Debe tener al menos una letra minuscula,otra mayuscula, un numero y un simbolo.\n\nIntroduce contrase\u00f1a: ");
			if (Utils.validatePassword(password)) {
					password=Utils.encryptMd5(password);
					String name = Interface.getString("\n   Nombre y apellido/s\n---------------------------\n-Deben empezar por mayuscula."
							+ "\n-No se admiten numeros.\n\nIntroduce nombre completo: ");
				if(Utils.validareName(name)) {
						String nif =Interface.getString("\n   Nif\n--------\n-Debe estar compuesto por 8 digitos y una letra "
								+ "mayuscula.\n\nIntroduce nif: ");
							
						String CheckNif = "Unif,"+nif;
						if(database.checkUser(CheckNif)) {
							System.out.println("\nEl nif ya existe\n");
							return;
						}	
						
					if(Utils.validateNif(nif)) {
							String email =Interface.getString("\n   Email\n----------\n\nIntroduce email: ");
							
							String CheckEmail = "Uemail,"+email;
							if(database.checkUser(CheckEmail)) {
								System.out.println("\nEl email ya existe\n");
								return;
							}	
							
						if(Utils.validateEmail(email)) {
								String address =Interface.getString("\n   Direccion\n--------------\n\nIntroduce dirección: ");
								String birthdate =Interface.getString("\n  Fecha de nacimiento\n----------------------\n"
										+ "-Debe seguir el siguiente formato: "
										+ "dd/mm/yyyy\n\nIntroduce fecha de nacimiento: ");
							if(Utils.validateDate(birthdate)) {
								birthdate=Utils.formatDateSQL(birthdate);
								if(database.signup(0, username, password, name, nif, email, address, birthdate)) {
									System.out.println("Registro de usuario realizado");
								}else {
									System.out.println("Ha habido un error y no se ha registrado el usuario");
									}
						}else
								System.out.println("la fecha introducida no es correcta");
					}else {
						System.out.println("El email introducido no es valido");
					}
				}else {
					System.out.println("El nif introducido no es valido");
					}
			}else {
				System.out.println("Los datos introducidos no son validos");
				}
		}else {
			System.out.println("La contraseña introducida no es valida");
			}
	}else {
		System.out.println("Ese nombre de usuario no es valido");
		}
	}
	public void showUser() {
		
		System.out.println("DATOS DEL USUARIO\n -------------------");
		System.out.println("Id: "+this.user.id);
		System.out.println("Nombre: "+this.user.username);
		System.out.println("Nombre completo: "+ this.user.name);
		System.out.println("Dni: "+ this.user.nif);
		System.out.println("Email: "+this.user.email);
		System.out.println("Direccion: "+this.user.address);
		this.user.birthdate=Utils.formatDateEU(this.user.birthdate);
		System.out.println("Fecha de nacimiento: "+this.user.birthdate);
		System.out.println("Edad: "+ Utils.getAge(this.user.birthdate));
		System.out.println("Rol: "+this.user.role);
	}
	
	public void logout() {
		this.logged=false;
		this.user = new User();
	}
}
