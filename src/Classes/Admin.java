package classes;

import java.util.ArrayList;

public class Admin {
	private Session session = new Session();
	private Database database = new Database();
	private User user;
	private static final String SUCESS = "Cambio realizado con exito";
	private static final String FAIL = "Cambio no realizado";
	
	public void userManadgementOption() {
	
		int option = Interface.getInt(Config.ADMIN_MENU);
		
		switch (option) {
		
			case 1:
				session.signup();
				break;
			case 2:
				seeAllUsers();
				break;
			default:
				System.out.println("Opcion no valida");
		}
	}
	public void seeAllUsers() {
		
		ArrayList <User> users =database.allUsers();
		System.out.println("\n\n - MOSTRANDO USUARIOS -\n------------------------\nNumero total de usuarios: "+ users.size()+"\n");
		System.out.println();
		
			for(int i=0; i<users.size(); i++) {
				System.out.println("\nUsuario "+(i+1));
				System.out.println("------------");
				System.out.println("Id: "+ users.get(i).id);
				System.out.println("Username: "+ users.get(i).username);
				System.out.println("Password: "+ users.get(i).getPassword());
				System.out.println("Name: "+ users.get(i).name);
				System.out.println("Nif: "+ users.get(i).nif);
				System.out.println("Email: "+ users.get(i).email);
				System.out.println("address: "+ users.get(i).address);
				System.out.println("Birthdate: "+ users.get(i).birthdate);
				String fecha= Utils.formatDateEU(users.get(i).birthdate);
				System.out.println("Edad: "+ Utils.getAge(fecha));
				System.out.println("Role: "+ users.get(i).role);
			}	
		String option= Interface.getString("\n------- FIN -------\n¿Quieres gestionar un usuario?S/N: ");
		if(option.compareToIgnoreCase("S")==0) {
			changeViewOrDeleteUser();
		}
		userManadgementOption();
	}
	public void changeViewOrDeleteUser() {
		
		String idUser=Interface.getString("\nIntroduce el numero de id del usuario que quieres gestionar: ");
		String checkId="Uid,"+idUser;
		if (!database.checkUser(checkId)) {
			System.out.println("\nEl numero introducido no se corresponde con ningun id de usuario");
			return;
		}
		//String option = Interface.getString("\n      - MENU -\n --------------------\n-1 Ver usuario\n-2 Modificar usuario.\n-3 Eliminar usuario\n\nIntroduce numero: ");
		int option = Interface.getInt("\n      - MENU -\n --------------------\n-1 Ver usuario\n-2 Modificar usuario.\n-3 Eliminar usuario\n\nIntroduce numero: ");
		
		switch (option){	
			case 1:
				user = new User();
				user=database.login(idUser, "");
				session.showUser(user);
				break;	
			case 2:
				changeUser(idUser);
				break;	
			case 3:
				deleteUser(idUser);
				break;	
			default:
				 System.out.println("Opcion no valida");
				    break;
		}
	}
	public String changeUser(String idUser) {
		
		int secondOption = Interface.getInt (Config.MODIFY_USER);
		switch (secondOption) {
			case 1:
				String password = Interface.getString("\nLa contrase\u00f1a debe tener entre 8 y 20 caracteres.\n"
									+ "Debe tener al menos una letra minuscula,otra mayuscula, un numero y un simbolo."
									+ "\nIntroduce la nueva contrase\u00f1a: ");
				if(Utils.validatePassword(password)) {
					password=Utils.encryptMd5(password);
					String changeP= "password,"+password+","+idUser ;
					if (database.updateUserInformation(changeP)) {
						System.out.println(Admin.SUCESS);
						return "password,"+password;
					}
				}
				System.out.println(Admin.FAIL);
				break;
			case 2:
				String name = Interface.getString("\nDeben empezar por mayuscula y no se admiten numeros."
						+ "\nIntroduce nombre completo: ");
				if(Utils.validareName(name)) {
					String changeN="name,"+name+","+idUser;
					if (database.updateUserInformation(changeN)) {
						System.out.println(Admin.SUCESS);
						return "name,"+name;
					}
				}
				System.out.println(Admin.FAIL);
				break;
			case 3:
				String nif =Interface.getString("\nDebe estar compuesto por 8 digitos y una letra "
						+ "mayuscula.\nIntroduce nif: ");	
				if(Utils.validateNif(nif)) {
					String checkNif = "Unif,"+nif;
				
					if(database.checkUser(checkNif)) {
					System.out.println("\nEse nif ya existe en la base de datos\nCambio no realizado");
					break;
				
					}else {
						String changeNif="nif,"+nif+","+idUser;
						if (database.updateUserInformation(changeNif)) {
							System.out.println(Admin.SUCESS);
							return "nid,"+nif;
						}
					}
				}
				System.out.println(Admin.FAIL);
				break;
			case 4:
				String email =Interface.getString("\nIntroduce email: ");
				
				if(Utils.validateEmail(email)) {
					String CheckEmail = "Uemail,"+email;
					
					if(database.checkUser(CheckEmail)) {
						System.out.println("\nEse email ya existe en la base de datos\nCambio no realizado");
						break;
					}else {
						String changeE="email,"+email+","+idUser;
						if (database.updateUserInformation(changeE)) {
							System.out.println(Admin.SUCESS);
							return "email,"+email;
						}
					}
				}
				System.out.println(Admin.FAIL);
				break;
			case 5:
				String address =Interface.getString("\nIntroduce dirección: ");
				String changeA="address,"+address+","+idUser;
				if (database.updateUserInformation(changeA)) {
					System.out.println(Admin.SUCESS);
					return "address,"+address;
				}else {
					System.out.println(Admin.FAIL);
					break;
			}
			case 6:
				String birthdate =Interface.getString("\nDebe seguir el siguiente formato: "
						+ "dd/mm/yyyy\nIntroduce fecha de nacimiento: ");
				if(Utils.validateDate(birthdate)) {
					birthdate=Utils.formatDateSQL(birthdate);
					String changeB="birthdate,"+birthdate+","+idUser;
					if(database.updateUserInformation(changeB)) {
						System.out.println(Admin.SUCESS);
						return "birthdate,"+birthdate;
					}
				}
				System.out.println(Admin.FAIL);
				break;	
			default:
			    System.out.println("Opcion no valida");
			    break;
		}
		return "";
	}
	private void deleteUser(String data) {
		
			int choose = Interface.getInt("\nIntroduce 1 si estas seguro de que quieres eliminar al usuario: ");
			if (choose==1) {
				if(database.deleteUser(data)) {
					System.out.println("El usuario ha sido eliminado");
				}
		
			}else {
				System.out.println("Saliendo de eliminar usuario");
			}
	}
}
