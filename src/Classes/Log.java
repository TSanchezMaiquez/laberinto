package classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    
  

    public static void write(String message, String username) {
        try {
            File file = new File(Config.FILE_PATH + "logDelSistema.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
           FileWriter writer = new FileWriter(file, true);
            LocalDateTime now = LocalDateTime.now();
            String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(formattedDate + " - " + message +  " - " + username + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
