import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Program {
    public static void main(String[] args) throws Exception {
        Path dbPath = Paths.get("db/mountains_db.tsv");
        System.out.println(dbPath.toAbsolutePath().toString());
        BufferedReader br = Files.newBufferedReader(dbPath, java.nio.charset.StandardCharsets.UTF_8);

        String header = br.readLine();
        Path cleanPath = Paths.get("db/mountains_db_clean.tsv");
        BufferedWriter cbw = Files.newBufferedWriter(cleanPath, java.nio.charset.StandardCharsets.UTF_8);
        cbw.write(header + "\n");

        Path errPath = Paths.get("db/mountains_db_err.tsv");
        BufferedWriter ebw = Files.newBufferedWriter(errPath, java.nio.charset.StandardCharsets.UTF_8);
        ebw.write(header + "\n");
        
        String line;
        while((line = br.readLine()) != null) {
            String[] data = line.split("\\t");
            try{
                isValid(data);
                cbw.write(line + "\n");
            } catch (RuntimeException e){
                ebw.write(line + "\t" + e.getMessage() +"\n");
            }
        }
        
        cbw.close();
        br.close();
        ebw.close();
    }
    public static void isValid(String[] data) {
        if(data.length != 6){
            throw new RuntimeException("Invalid number of fields");
        }
        if(data[0].isEmpty() || data[1].isEmpty() || data[2].isEmpty() || data[3].isEmpty() || data[4].isEmpty() || data[5].isEmpty()){
            throw new RuntimeException("Empty fields");
        }
        
        try {
            double lat = Double.parseDouble(data[3]);
            double lon = Double.parseDouble(data[4]);
            if(lat <= -90 || lat >= 90){
                throw new RuntimeException("Latitude out of range");
            }
            if(lon <= -180 || lon >= 180){
                throw new RuntimeException("Longitude out of range");
            }
        } catch (NumberFormatException e){
            throw new RuntimeException("Invalid latitude or longitude");
        }
        
        
    }
}
