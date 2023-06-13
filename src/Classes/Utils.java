package classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Utils {

	
	public static boolean validateUsername(String nombre) {
		
		String regex="^[a-zA-Z0-9_]{3,20}$";
		
		return nombre.matches(regex);
		
	}
	
	public static boolean validatePassword(String password) {
		
		String regex="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
		
		return password.matches(regex);
		
	}
	
	public static boolean validareName(String name) {
		
		String regex="^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+(\\s[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+){1,}$";
		return name.matches(regex);
	}
	
	public static boolean validateNif(String nif) {
		
		String regex="^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$";
		return nif.matches(regex);
		
	}
	public static boolean validateEmail(String email) {
		
		String regex="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		return email.matches(regex);
	}
	public static boolean validateDate(String date) {
		
		String regex="^(0[1-9]|[1-2]\\d|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$";
		return date.matches(regex);
	}

		public static String encryptMd5(String password) {
		    try {
		        MessageDigest md = MessageDigest.getInstance("MD5");
		        md.update(password.getBytes());
		        byte[] digest = md.digest();
		        StringBuilder sb = new StringBuilder();
		        for (byte b : digest) {
		            sb.append(String.format("%02x", b & 0xff));
		        }
		        return sb.toString();
		    } catch (NoSuchAlgorithmException e) {
		        // Manejo de excepción en caso de que no se pueda obtener el algoritmo MD5
		        e.printStackTrace();
		        return null;
		    }
		}
		public static String formatDateSQL(String birthdate) {
			String [] date2 = birthdate.split("/");
			String dateSQL ="";
			for (int i = date2.length -1; i >= 0; i--) {
				dateSQL+=date2[i];
				 if (i > 0) {
			            dateSQL += "-";
			        }
			}
			return dateSQL;
		}
		public static String formatDateEU(String birthdate) {
			String [] date2 =birthdate.split("-");
			String dateEU="";
			for (int i = date2.length -1; i >=0; i--) {
				dateEU+=date2[i];
				if(i>0) {
					dateEU+="/";
				}
			}
			return dateEU;
		}
		public static int getAge (String birthdate) {
			
		    LocalDate birthDate = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		    LocalDate currentDate = LocalDate.now();
		    Period period = Period.between(birthDate, currentDate);
		    return period.getYears();
		}
}
