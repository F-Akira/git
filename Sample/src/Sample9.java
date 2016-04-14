
import java.io.*;

public class Sample9 {
    public static void main(String[] args) {
        String filename = "test9.txt";
        
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            pw.println("Enjoy");
            pw.println("Java programming");
            
        } catch (IOException e) {
            System.out.println("Cannot write: " + filename);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + filename);
        } catch (IOException e) {
            System.out.println("Cannot read: " + filename);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) { /* empty */ }
            }
        }
    }
}
