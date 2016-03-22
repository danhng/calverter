/**
 * @author Danh Thanh Nguyen <d.t.nguyen@newcastle.ac.uk>
 *         date created: 14/03/2016
 */
public class Debug {
//
//    emerg	Emergencies - system is unusable.	"Child cannot open lock file. Exiting"
//    alert	Action must be taken immediately.	"getpwuid: couldn't determine user name from uid"
//    crit	Critical Conditions.	"socket: Failed to get a socket, exiting child"
//    error	Error conditions.	"Premature end of script headers"
//    warn	Warning conditions.	"child process 1234 did not exit, sending another SIGHUP"
//    notice	Normal but significant condition.	"httpd: caught SIGBUS, attempting to dump core in ..."
//    info	Informational.	"Server seems busy, (you may need to increase StartServers, or Min/MaxSpareServers)..."
//    debug	Debug-level messages	"Opening config file ..."
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int NOTICE = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int CRIT = 6;
    public static final int ALERT = 7;
    public static final int EMERG = 8;


    private static int DEBUG_LEVEL = ERROR;

    public static int getDebugLevel() {
        return DEBUG_LEVEL;
    }

    /**
     * Set a new debug level.
     * The new debug level must be one of the 8 levels specified {INFO, DEBUG, NOTICE, WARN, ERROR, CRIT, ALERT, EMERG}
     * @param newLevel the new debug level
     * @return the old debug level
     */
    public static int setDebugLevel(int newLevel) {
        int old = DEBUG_LEVEL;
        if (newLevel >= DEBUG && newLevel <= EMERG)
            DEBUG_LEVEL = newLevel;
        else
            warn("New Debug level %d is not valid. \n", newLevel);
        return old;
    }

    public static void debug(String format, Object... args) {
        if (DEBUG >= DEBUG_LEVEL)
            System.out.printf("[    DEBUG    ] " + format, args);
    }

    public static void error(boolean exit, String format, Object... args) {
        if (ERROR >= DEBUG_LEVEL)
            System.out.printf("[    ERROR    ] " + format, args);
        if (exit) {
            emerg("System is aborting. Cause: " + format, args);
            System.exit(1);
        }
    }

    public static void emerg(String format, Object[] args) {
        System.out.printf("[    EMERG    ] " + format, args);
    }

    public static void warn(String format, Object... args) {
        if (WARN >= DEBUG_LEVEL)
            System.out.printf("[    WARN     ] " + format, args);
    }
}
