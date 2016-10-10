import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

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
    private final Pattern commentPattern = Pattern.compile("\\S+\\/\\/");
    private String currentLine;
    private String nextLine;
    private boolean EOF; // End of file indicator
    private boolean EOC; // End of code/commands indicator

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
            return EOC;
        }

        // Private
            // Initialize lines
            private void initLines(){
                try {
                    nextLine = null;
                    currentLine = reader.readLine();
                    if(currentLine != null) {
                        nextLine = reader.readLine();
                        EOF = false;
                    } else {
                        EOF = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Is there a next line available?
            private boolean hasNextLine() {
                return(nextLine!=null);
            }

            // Does the current line have commands or just white space/comments?
            private boolean hasCommands() {
                Matcher matcher = Pattern.compile("\\S").matcher(currentLine);
                return false;
            }

            // Parse current line
}
