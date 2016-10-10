import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser class as specified in Nand2Tetris.org course chapter 6 - Assembler
 * This class will be part of a main program called "Assembler" also as described
 * Author : Ayushka Partohap
 * E-mail : partohap.ayushka@gmail.com
 */

public class Parser {
    // Fields
    private BufferedReader reader;
    private final Pattern commentPattern = Pattern.compile(".*\\/\\/");
    private String currentLine;

    // Constructor
        // Construct by buffered reader
        public Parser(BufferedReader br){
            reader = br;
        }

        // Construct by file
        public Parser(File f){
            try {
                reader = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Construct by file path
        public Parser(String fp){
            try {
                reader = new BufferedReader(new FileReader(new File(fp)));
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }

    // Methods
        // Public
        public boolean hasNextCommand(){
        return true;
        }

        // Get current line
        private void readCurrentLine(){
            try {
                currentLine = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
