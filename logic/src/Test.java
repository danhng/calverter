import java.util.Arrays;

/**
 * @author Danh Thanh Nguyen <d.t.nguyen@newcastle.ac.uk>
 *         date created: 14/03/2016
 */
public class Test {
    public static void main(String[] args) {

        String number = ".5";
        String[] parts = number.split("\\.");

        long p = -4;
        System.out.println(NumberHelper.toFormat(Number.OCT_MODE,
                "1111111111111111111111111111111111111111101111111111101110111011", Number.BIN_MODE,
                Number.DO_GROUPING));
        System.out.println(NumberHelper.isValidFormat("-2.", Number.DEC_MODE));
    }
}
