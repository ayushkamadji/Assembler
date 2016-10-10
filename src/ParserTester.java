import java.io.*;
import java.net.URL;

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
        Parser parser;
        try {
            parser = new Parser(filename);
            console.printf("%-15s%-10s%-10s%-10s%-10s\n", "ComType","Symbol","Dest","Comp","Jump");
            while (parser.hasMoreCommands()) {
                parser.advance();
                console.printf("%-15s%-10s%-10s%-10s%-10s\n",
                        parser.commandType(),
                        parser.symbol(),
                        parser.dest(),
                        parser.comp(),
                        parser.jump());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
