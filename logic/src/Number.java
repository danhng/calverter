import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Danh Thanh Nguyen <d.t.nguyen@newcastle.ac.uk>
 * date created: 14/03/2016
 */
public class Number {

    public static final int BIN_MODE = 0;
    public static final int OCT_MODE = 1;
    public static final int DEC_MODE = 2;
    public static final int HEX_MODE = 3;

    public static final String[] FORMATS = {"BINARY", "OCTAL", "DECIMAL", "HEXADECIMAL"};

    public static final int MAX_BITS = Long.BYTES * 8;

    public static final String[] SUFFICES = {"0B", "0", "", "0X"};

    // digits of different formats
    public static final Character[][] DIGITS = {
            {'0', '1'},
            {'0', '1', '2', '3', '4', '5', '6', '7'},
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'}};

    // conventional groups of digits in formats for improved readability
    public static final int[] GROUPS = {4, 3, 3, 4};

    // conventional group delimiters for number formats;
    public static final char[] DELIMETERS = {' ', ' ', ',', ' '};

    // String rep used when some mode is not supported for some required input value.
    public static String NAN = "N/A";

    //Formatting options by combining options of grouping and stuffing
    // no formatting options
    public static final int NO_FORMAT = 0;

    // add the leading 'default' characters (ex. zeroes for binary string)
    public static final int DO_STUFFING = 1;

    // group digits by placing delimiters between them
    public static final int DO_GROUPING = 2;

    private String userInput; // original user input
    private int inputMode; // mode the the input
    private double fvalue; // the float value
    private long  ivalue; // the int value
    //private boolean isFloatMode;
    private ArrayList<String> reps; // string reps of the number

    // constructor
    public Number(int inputMode, String rep) {
        this.inputMode = inputMode;
        this.userInput = rep;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Re-update string reps based on the user input
     */
    private void refresh() {

    }

    public double getFvalue() {
        return fvalue;
    }

    public void setFvalue(double fvalue) {
        this.fvalue = fvalue;
    }

    public long getIvalue() {
        return ivalue;
    }

    public void setIvalue(long ivalue) {
        this.ivalue = ivalue;
    }

    public boolean isFloatMode() {
        return NumberHelper.isFloat(userInput);
    }

    public ArrayList<String> getReps() {
        return reps;
    }

    public void setReps(ArrayList<String> reps) {
        this.reps = reps;
    }

    public int getInputMode() {
        return inputMode;
    }

    public void setInputMode(int inputMode) {
        this.inputMode = inputMode;
    }

    @Override
    public String toString() {
        return "Number{" +
                "userInput='" + userInput + '\'' +
                ", inputMode=" + inputMode +
                ", fvalue=" + fvalue +
                ", ivalue=" + ivalue +
                ", reps=" + Arrays.toString(reps.toArray()) +
                '}';
    }
}
