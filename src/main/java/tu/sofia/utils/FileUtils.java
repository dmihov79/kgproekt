package tu.sofia.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {
    public static String loadAsString(String path){
        StringBuilder result = new StringBuilder();
        try{
            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Damyan\\Desktop\\tusofia\\prs kursova\\kursova\\KgProekt\\src\\main\\resources" + path));
            for(String line : lines){
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Couldnt find file at: " + path);
        }
        return result.toString();
    }
}
