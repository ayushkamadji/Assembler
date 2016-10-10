import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by A00739487 on 10/10/2016.
 */
public class Test {
    public static void main(String[] args) {
        Matcher matcher = Pattern.compile("\\S+").matcher("This is a match");
        String pattern = matcher.pattern().toString();
        System.out.println(matcher.group());
    }
}
