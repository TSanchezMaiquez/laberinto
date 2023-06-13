package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

	private final String IP = "localhost";  // 127.0.0.1
	private final String PORT = "3306";
	private final String DB = "maze"; 
	private final String USER = "root"; 
	private final String PASS = "1234";
	private final String URL = "jdbc:mysql://" +IP+ ":"+PORT+"/"+DB;
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	
	
public User login (String Uname, String password ) {
	
	User user = null;

    try {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL, USER, PASS);

        Statement stmt = conn.createStatement();

        String query = "SELECT * FROM user WHERE username='" + Uname + "' AND password='" + password + "'";
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
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
        stmt.close();
        rs.close();
        conn.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}

public boolean checkUser(String data) {
	
	String [] CheckData= data.split(",");
	 try {
	        Class.forName(DRIVER);
	        Connection conn = DriverManager.getConnection(URL, USER, PASS);

	        Statement stmt = conn.createStatement();
	        
	        
	        
	        String query="";
	        if(CheckData[0].equals("Uname")) {
	        	query = "SELECT * FROM user WHERE username='" + CheckData[1] + "';";
	        }
	        
	       
	        if(CheckData[0].equals("Unif")) {
	        	query = "SELECT * FROM user WHERE nif='" + CheckData[1] + "';";
	        }
	        
	        if(CheckData[0].equals("Uemail")) {
	        	query = "SELECT * FROM user WHERE email='" + CheckData[1] + "';";
	        }
	        
	        
	        
	        ResultSet rs = stmt.executeQuery(query);

	        boolean hasResult = rs.next(); // Comprobar si hay al menos un resultado
	        

	        stmt.close();
	        rs.close();
	        conn.close();
	        
	        return hasResult;

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	
	
	return false;
	
	
}
public boolean signup(int id, String username, String password, String name, String nif, String email, String address, String birthdate) {
	
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
	
}
