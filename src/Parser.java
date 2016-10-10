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
    private String currentLine;
    private String nextLine;
    private String currentCommand;
    private String nextCommand;
    private CommandType comType;
    private String destM;
    private String compM;
    private String jumpM;
    private String symbolM;
    private boolean EOF; // End of file indicator
    private boolean EOC; // End of code/commands indicator

    // Constructor
        // Construct by buffered reader
        public Parser(BufferedReader br){
            reader = br;
            EOF = false;
            nextLine = null;
            readNextLine();
            findNextCommand();
        }

        // Construct by file
        public Parser(File f) throws FileNotFoundException {
            this(new BufferedReader(new FileReader(f)));
        }

        // Construct by file path
        public Parser(String fp) throws FileNotFoundException {
            this(new File(fp));
        }

    // Methods
        // Public (as specified)
        public boolean hasMoreCommands(){
            EOC = (nextCommand != null);
            return EOC;
        }
        public void advance() {
            if(hasMoreCommands()) {
                currentCommand = nextCommand;
                processCommand(currentCommand);
                readNextLine();
                findNextCommand();
            }
        }
        public CommandType commandType() {
            return comType;
        }
        public String dest() {
            return destM;
        }
        public String comp(){
            return compM;
        }
        public String jump(){
            return jumpM;
        }
        public String symbol(){
            return symbolM;
        }

    // Private
            // Reads the next line into memory
            private void readNextLine(){
                try {
                    nextLine = reader.readLine();
                    if(nextLine == null) {
                        EOF = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Finds the next command
            private void findNextCommand() {
                while(!hasCommands(nextLine)&&!EOF){
                    readNextLine();
                }
                nextCommand = null;
                if (hasCommands(nextLine))
                    nextCommand = lineToCommand(nextLine);
            }

            // Does the current line have commands or just white space/comments?
            private boolean hasCommands(String s) {
                if(s!=null) {
                    Matcher matcherCM = Pattern.compile("^\\s*\\/\\/").matcher(s);
                    Matcher matcherWS = Pattern.compile("\\S").matcher(s);
                    return (matcherWS.find() && (!matcherCM.find()));
                }
                return false;
            }

            // Does the current line with commands come with inline comments?
            private boolean withComments(String s){
                if(hasCommands(s)){
                    Matcher matcherCM = Pattern.compile("\\/\\/").matcher(s);
                    return matcherCM.find();
                }
                return false;
            }

            // Convert line to command
            private String lineToCommand(String line){
                if (hasCommands(line)) {
                    String out = "";
                    Matcher matcherCOM;
                    if (withComments(line)) {
                        matcherCOM = Pattern.compile("\\S(?=.*\\/\\/)").matcher(line);
                    } else {
                        matcherCOM = Pattern.compile("\\S").matcher(line);
                    }
                    while(matcherCOM.find()) out += matcherCOM.group();
                    return out;
                }
                return null;
            }

            // Parse the command
            private void processCommand(String command) {
                destM = null;
                compM = null;
                jumpM = null;
                symbolM = null;

                Matcher matcher = Pattern.compile("\\S").matcher(command);
                // Store command type
                matcher.find();
                if (matcher.group().equals("@")) comType=CommandType.A_COMMAND;
                if (matcher.group().equals("(")) comType=CommandType.L_COMMAND;
                if (matcher.group().equals("!") ||
                    matcher.group().equals("0") ||
                    matcher.group().equals("1") ||
                    matcher.group().equals("-") ||
                    matcher.group().equals("A") ||
                    matcher.group().equals("D") ||
                    matcher.group().equals("M") ) comType=CommandType.C_COMMAND;

                switch(comType){
                    case C_COMMAND: {
                        if (Pattern.compile(".+=").matcher(command).find()) {
                            matcher = Pattern.compile("\\S+(?==)").matcher(command);
                            matcher.find();
                            destM = matcher.group();
                        }
                        if (Pattern.compile(";").matcher(command).find()) {
                            matcher = Pattern.compile("(?<=;)\\S+").matcher(command);
                            matcher.find();
                            jumpM = matcher.group();
                        }
                        matcher = Pattern.compile("\\S+").matcher(command);
                        if (destM != null)
                            matcher = Pattern.compile("(?<==)\\S+").matcher(command);
                        if (jumpM != null)
                            matcher = Pattern.compile("\\S+(?=;)").matcher(command);
                        if ((destM != null) && (jumpM != null))
                            matcher = Pattern.compile("(?<==)\\S+(?=;)").matcher(command);
                        matcher.find();
                        compM = matcher.group();
                        break;
                    }
                    case A_COMMAND: {
                        matcher = Pattern.compile("(?<=@)\\S+").matcher(command);
                        matcher.find();
                        symbolM = matcher.group();
                        break;
                    }
                    case L_COMMAND: {
                        matcher = Pattern.compile("(?<=\\()\\S+(?=\\))").matcher(command);
                        matcher.find();
                        symbolM = matcher.group();
                        break;
                    }
                }
            }
}
