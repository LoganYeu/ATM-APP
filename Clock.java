/**
 * The Clock class holds a single method for getting the current date and time and returning it for later use in the Atm class
 *
 * @author (Logan Yeubanks)
 * @version (Version 1)
 */

//imports I found that format and then give the local date and time.
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Clock {

    /**
     * formats and then returns the date and time.
     *
     * @return dtf.format(now)
     */
    public static String dateAndTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
