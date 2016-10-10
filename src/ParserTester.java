import java.io.*;

/**
 * Created by ayushka on 10/10/16.
 */
public class ParserTester {
    public static void main(String[] args) {
        Console console = System.console();
        if(console==null){
            System.err.println("No console");
            System.exit(1);
        }

        String filename = args[0];
        Parser parser = new Parser(filename);
    }
}
