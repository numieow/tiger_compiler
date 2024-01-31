package asm;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Tools {

    public static void writeToFile(String filename, String content) {
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            System.out.println("code assembleur ecrit dans " + filename);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
