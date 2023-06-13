package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Database {

	private final String IP = "localhost";  // 127.0.0.1
	private final String PORT = "3306";		// Puerto
	private final String DB = "maze"; 		// Nombre base de datos
	private final String USER = "root"; 	// usuario
	private final String PASS = "1234";		// Contraseña
	private final String URL = "jdbc:mysql://" +IP+ ":"+PORT+"/"+DB;
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	
	
public User login (String uName, String password ) {
	
	User user = null;

    try {
    	 // Cargar el controlador JDBC y conectarse a la base de datos
        Class.forName(DRIVER);
        
        Connection conn = DriverManager.getConnection(URL, USER, PASS);

        Statement stmt = conn.createStatement();
        String query= "";
        ResultSet rs;
        
        	// Crear la consulta SQL en función de si se ha proporcionado una contraseña o no
	        if (password.equalsIgnoreCase("")) {
	        	query = "SELECT * FROM user WHERE id='" + uName + "'";
	        	
	        	
	        } else {
	        	query = "SELECT * FROM user WHERE username='" + uName + "' AND password='" + password + "'";
	        
	     	}
	    // Ejecutar la consulta y obtener el resultado
    	rs = stmt.executeQuery(query);
        if (rs.next()) {
        	 // Crear el objeto User a partir de los datos obtenidos de la base de datos
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String name = rs.getString("name");
            String nif = rs.getString("nif");
            String email = rs.getString("email");
            String address = rs.getString("address");
            String birthdate = rs.getString("birthdate");
            String role = rs.getString("role");
            
            user = new User(id, username, name, nif, email, address, birthdate, role);
            return user;
        }
        
        // Cerrar los objetos utilizados para la conexión y la consulta
        stmt.close();
        rs.close();
        conn.close();

    }catch (Exception e) {
    	 // Si ocurre alguna excepción, imprimir el mensaje de error
        e.printStackTrace();
    }
    // Si no se ha encontrado usuario, devolvera null
    return null;
}

public boolean checkUser(String data) {
	
	 // Dividir la entrada en un arreglo utilizando una coma como delimitador
	String [] checkData= data.split(",");
	 try {
		// Cargar el controlador JDBC
	        Class.forName(DRIVER);
	        
	        // Establecer la conexión con la base de datos
	        Connection conn = DriverManager.getConnection(URL, USER, PASS);
	        
	     // Crear un objeto Statement para ejecutar las consultas
	        Statement stmt = conn.createStatement();
	        
	        // Crear una cadena de consulta en función del tipo de dato a buscar
	        String query="";
	        if(checkData[0].equals("Uname")) {
	        	query = "SELECT * FROM user WHERE username='" + checkData[1] + "';";
	        }
	        if(checkData[0].equals("Unif")) {
	        	query = "SELECT * FROM user WHERE nif='" + checkData[1] + "';";
	        } 
	        if(checkData[0].equals("Uemail")) {
	        	query = "SELECT * FROM user WHERE email='" + checkData[1] + "';";
	        }
	        if(checkData[0].equals("Uid")) {
	        	query = "SELECT * FROM user WHERE id='" + checkData[1] + "';";
	        }
	        if(checkData[0].equals("Upassword")) {
	        	query = "SELECT * FROM user WHERE password='" + checkData[1] + "';";
	        } 
	        // Ejecutar la consulta
	        ResultSet rs = stmt.executeQuery(query);

	        boolean hasResult = rs.next(); // Comprobar si hay al menos un resultado
	        
	        // Cerrar los objetos Statement, ResultSet y Connection
	        stmt.close();
	        rs.close();
	        conn.close();
	        
	        // Devolver true si hay al menos un resultado, false en caso contrario
	        return hasResult;
	        
	        }catch (Exception e) {
	        e.printStackTrace();
	    }       
	// Si ocurre una excepción, devolver false por defecto
	return false;
}
public boolean dbSignUp(int id, String username, String password, String name, String nif, String email, String address, String birthdate) {
	
	 try {
	        Class.forName(DRIVER);
	        Connection conn = DriverManager.getConnection(URL, USER, PASS);

	        Statement stmt = conn.createStatement();
	        
	        String query = "insert into user(id, username, password, name, nif, email, address, birthdate) "
					+ "values ('"+id+"', '"+username+"', '"+password+"', '"+name+"', '"+nif+"', '"+email+"', '"+address+"', '"+birthdate+"');";
			
			stmt.executeUpdate(query);
			conn.close();
			
		}catch(Exception e) {
			System.out.println("Error " + e);
		}
	return true;
}
public ArrayList <User> allUsers() {
	
	ArrayList<User> usuarios = new ArrayList<>();
	
    try {
    	
        Class.forName(DRIVER);
        
        Connection conn = DriverManager.getConnection(URL, USER, PASS);

        Statement stmt = conn.createStatement();
        String query="";
        ResultSet rs;
        
    	query = "select id, username, password, name, nif, email, address, birthdate, role "
				+ "from user";
    	rs = stmt.executeQuery(query);
		
		while(rs.next()) {
			
			User aux = new User(rs.getInt("id"), rs.getString("username"), 
					rs.getString("password"), rs.getString("name"),rs.getString("nif"),
					rs.getString("email"), rs.getString("address"), rs.getString("birthdate"), rs.getString("role"));
			usuarios.add(aux);
		}
	
	    stmt.close();
	    rs.close();
	    conn.close();
	
	} catch (Exception e) {
	    e.printStackTrace();
		}
	return usuarios;
	}
public boolean updateUserInformation(String change) {
	
	String[] changeData = change.split(",");
    try {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        Statement stmt = conn.createStatement();

        String query = "UPDATE user SET " + changeData[0] + " = '" + changeData[1] + "' WHERE id = " + changeData[2];

        int rowsAffected = stmt.executeUpdate(query);

        stmt.close();
        conn.close();

        return rowsAffected > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
public boolean deleteUser(String id) {
	
	try {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL, USER, PASS);

        Statement stmt = conn.createStatement();

        String query = "DELETE FROM user WHERE id = '" + id + "';";

        int rowsAffected = stmt.executeUpdate(query);

        stmt.close();
        conn.close();

        return rowsAffected > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
	}
}
