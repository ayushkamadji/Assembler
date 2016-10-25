import java.io.*;
import java.net.URL;

/**
 * Created by ayushka on 10/10/16.
 */
public class CodeTester{
    public static void main(String[] args) {
        Console console = System.console();
        if(console==null){
            System.err.println("No console");
            System.exit(1);
        }


        String filename = args[0];
        Parser parser;
        Code code = new Code();
        String desthex;
        try {
            parser = new Parser(filename);
            console.printf("%-15s%-10s%-10s%-10s%-10s%-10s\n", "ComType","Symbol","Dest","Comp","Jump","DHX");
            while (parser.hasMoreCommands()) {
                parser.advance();
                desthex = "null";
                if(parser.dest() != null)
                    desthex = Integer.toString(code.dest(parser.dest()));
                console.printf("%-15s%-10s%-10s%-10s%-10s%-10s\n",
                        parser.commandType(),
                        parser.symbol(),
                        parser.dest(),
                        parser.comp(),
                        parser.jump(),
                        desthex);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
