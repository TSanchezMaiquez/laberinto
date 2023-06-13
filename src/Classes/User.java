package classes;

public class User {
	//Propiedades
	public int id;
	public String username;
	private String password;
	public String name;
	public String nif;
	public String email;
	public String address;
	public String birthdate;
	public String role;
	
	
	public User() {
		super();
	}
	public User(int id, String username, String password, String name, String nif, String email, String address, String birthdate,
			String role) {
		super();
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.nif = nif;
		this.email = email;
		this.address = address;
		this.birthdate = birthdate;
		this.role = role;
	}
	
	public User(int id, String username, String name, String nif, String email, String address, String birthdate,
			String role) {
		super();
		
		this.id = id;
		this.username = username;
		this.name = name;
		this.nif = nif;
		this.email = email;
		this.address = address;
		this.birthdate = birthdate;
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
