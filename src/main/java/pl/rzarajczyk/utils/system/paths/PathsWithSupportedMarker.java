package pl.rzarajczyk.utils.system.paths;

interface PathsWithSupportedMarker extends Paths {

    /**
     * Indicated whether the current platform is supported by this class (which means
     * the results of all methods will be reasonable)
     * @return true is the current platform is supported by this class
     */
    boolean isSupported();
    
}
