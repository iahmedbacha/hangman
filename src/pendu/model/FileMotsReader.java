package pendu.model;

import java.io.FileInputStream;
import java.util.Scanner;

public class FileMotsReader implements IMotsReader {
    private static final String filePath="./mots.poo";
    private Scanner buffer;

    public FileMotsReader() {
        try {
            buffer = new Scanner(new FileInputStream(filePath));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public String next() {
        return buffer.next();
    }

    @Override
    public boolean hasNext() {
        return buffer.hasNext();
    }
}
