package pl.rzarajczyk.utils.system.os;

public class OperatingSystem {
    
    /**
     * For JUnit tests.
     * @param value system property value to be set
     */
    static void setSystemProperty(String value) {
        System.setProperty("os.name", value);
    }
    
    /**
     * Returns type of current platform's operating system.
     * <br>
     * If the operating system cannot be recognized, returned value is {@link OperatingSystemType#UNKNOWN}
     * @return type of current platform's operating system.
     */
    public static OperatingSystemType determine() {
        if ( isWindows() )
            return OperatingSystemType.WINDOWS;
        if ( isMac() )
            return OperatingSystemType.MAC;
        if ( isUnix() )
            return OperatingSystemType.UNIX;
        return OperatingSystemType.UNKNOWN;
    }

    /**
     * Checks if the current platform is MS Windows
     * @return true is the current platform is MS Windows
     */
    public static boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win");
    }

    /**
     * Checks if the current platform is MacOS
     * @return true is the current platform is MacOS
     */
    public static boolean isMac() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("mac");
    }

    /**
     * Checks if the current platform is Unix
     * @return true is the current platform is Unix
     */
    public static boolean isUnix() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("nix") || os.contains("nux");
    }
    
}
