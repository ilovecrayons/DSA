import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Program {
    public static void main(String[] args) throws Exception {
        Mountain shortestMountain = null;
        Mountain tallestMountain = null;
        int sizeOfOriginalDB = 0;
        int sizeOfCleanDB = 0;
        int sizeOfErrorDB = 0;
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
            sizeOfOriginalDB++;
            try{
                // height format: 1234 m
                Mountain m = new Mountain(line);
                cbw.write(line + "\n");
                if(shortestMountain == null){
                    shortestMountain = m;
                }
                if(tallestMountain == null){
                    tallestMountain = m;
                }
                if(m.getHeight() < shortestMountain.getHeight()){
                    shortestMountain = m;
                }
                if(m.getHeight() > tallestMountain.getHeight()){
                    tallestMountain = m;
                }
                sizeOfCleanDB++;
            } catch (RuntimeException e){
                ebw.write(line + "\t" + e.getMessage() +"\n");
                sizeOfErrorDB++;
            }
        }
        
        cbw.close();
        br.close();
        ebw.close();

        System.out.println("Original DB size: " + sizeOfOriginalDB);
        System.out.println("Clean DB size: " + sizeOfCleanDB);
        System.out.println("Error DB size: " + sizeOfErrorDB);
        System.out.println("Shortest mountain: " + shortestMountain.getRawData());
        System.out.println("Tallest mountain: " + tallestMountain.getRawData());
    }
    
}
